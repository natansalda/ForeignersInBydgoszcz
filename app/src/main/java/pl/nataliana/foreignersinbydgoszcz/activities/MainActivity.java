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

    public void seePlaces(View view) {
        Intent c = new Intent(MainActivity.this, PlacesActivity.class);
        startActivity(c);
    }
}
