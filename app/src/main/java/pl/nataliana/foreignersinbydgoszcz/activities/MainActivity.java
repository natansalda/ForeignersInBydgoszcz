package pl.nataliana.foreignersinbydgoszcz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        startActivity(c);
    }

    // Opens TasksActivity
    public void seeTasks(View view) {
        Intent c = new Intent(MainActivity.this, TasksActivity.class);
        startActivity(c);
    }

    // Opens FormalitiesActivity
    public void seeFormalities(View view) {
        Intent c = new Intent(MainActivity.this, FormalitiesActivity.class);
        startActivity(c);
    }
}
