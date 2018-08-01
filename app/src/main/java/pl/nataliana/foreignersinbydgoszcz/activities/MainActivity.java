package pl.nataliana.foreignersinbydgoszcz.activities;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import pl.nataliana.foreignersinbydgoszcz.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Opens PlacesActivity
    public void seePlaces(View view) {
        Intent c = new Intent(MainActivity.this, PlacesActivity.class);
        Bundle bundle = ActivityOptions
                .makeSceneTransitionAnimation(this)
                .toBundle();
        startActivity(c, bundle);
    }

    // Opens TasksActivity
    public void seeTasks(View view) {
        Intent c = new Intent(MainActivity.this, TasksActivity.class);
        Bundle bundle = ActivityOptions
                .makeSceneTransitionAnimation(this)
                .toBundle();
        startActivity(c, bundle);
    }

    // Opens FormalitiesActivity
    public void seeFormalities(View view) {
        Intent c = new Intent(MainActivity.this, FormalitiesActivity.class);
        Bundle bundle = ActivityOptions
                .makeSceneTransitionAnimation(this)
                .toBundle();
        startActivity(c, bundle);
    }

    // Create menu items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Choose menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                showDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.about_text);
        builder.setTitle(R.string.about_title);
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
}
