package pl.nataliana.foreignersinbydgoszcz.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pl.nataliana.foreignersinbydgoszcz.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Bydgoszcz and move the camera
        LatLng bydgoszcz = new LatLng(53, 18);
        mMap.addMarker(new MarkerOptions().position(bydgoszcz).title("Marker in Bydgoszcz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bydgoszcz));
    }

    public void centerMap(View view) {
        // Move the map back to Bydgoszcz
        LatLng bydgoszcz = new LatLng(53, 18);
        mMap.addMarker(new MarkerOptions().position(bydgoszcz).title("Marker in Bydgoszcz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bydgoszcz));
    }
}


