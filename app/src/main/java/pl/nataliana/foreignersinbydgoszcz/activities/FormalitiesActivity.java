package pl.nataliana.foreignersinbydgoszcz.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.adapters.FormalAdapter;
import pl.nataliana.foreignersinbydgoszcz.data.FormalData;
import pl.nataliana.foreignersinbydgoszcz.model.Formal;
import pl.nataliana.foreignersinbydgoszcz.utils.NetworkUtils;

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
                checkConnection();
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

    public void checkConnection() {
        new NetworkAsyncTask().execute();
    }


    public class NetworkAsyncTask extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {

            boolean isConnected = NetworkUtils.isNetworkAvailable(getApplicationContext());
            String result;
            if (isConnected) {
                result = getString(R.string.yes_net);
            } else {
                result = getString(R.string.no_net);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(FormalitiesActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }
}

