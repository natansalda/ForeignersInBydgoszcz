package pl.nataliana.foreignersinbydgoszcz.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.adapters.FormalAdapter;
import pl.nataliana.foreignersinbydgoszcz.data.FormalData;
import pl.nataliana.foreignersinbydgoszcz.model.Formal;

public class FormalitiesActivity extends AppCompatActivity {

    private static FormalAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Formal> listOfOffices;
    public static View.OnClickListener myOnClickListener;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formalities);

        // Creates an instance for MyOnClickListener
        myOnClickListener = new FormalitiesActivity.MyOnClickListener(this);

        // Sets up the RecyclerView
        recyclerView = findViewById(R.id.formal_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listOfOffices = new ArrayList<Formal>();
        for (int i = 0; i < FormalData.nameArray.length; i++) {
            listOfOffices.add(new Formal(
                    FormalData.id_[i],
                    FormalData.nameArray[i],
                    FormalData.descriptionArray[i],
                    FormalData.addressArray[i]
            ));
        }

        // Binds adapter to the view
        adapter = new FormalAdapter(listOfOffices);
        recyclerView.setAdapter(adapter);

        mProgressDialog = new ProgressDialog(FormalitiesActivity.this);
        mProgressDialog.setMessage(getString(R.string.file_is_downloading));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);
    }

    private static class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        // In this version of the app onClick is not used - planning to add this later
        @Override
        public void onClick(View v) {
            return;
        }
    }

    // Create menu items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formal, menu);
        return true;
    }

    // Choose menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.download:
                downloadFile();
                return true;
            case R.id.info:
                showDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormalitiesActivity.this);
        builder.setMessage(R.string.download_text);
        builder.setCancelable(true);

        builder.setPositiveButton(
                R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void downloadFile() {
        final DownloadTask downloadTask = new DownloadTask(FormalitiesActivity.this);
        downloadTask.execute(getString(R.string.input_file_path));

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                downloadTask.cancel(true);
            }
        });
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(getString(R.string.output_file_path));

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null)
                Toast.makeText(context,getString(R.string.download_error)+result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, R.string.file_downloaded, Toast.LENGTH_SHORT).show();
        }
    }
}
