package pl.nataliana.foreignersinbydgoszcz.activities;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import pl.nataliana.foreignersinbydgoszcz.R;

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Bydgoszcz and move the camera
//        LatLng bydgoszcz = new LatLng(53, 18);
//        mMap.addMarker(new MarkerOptions().position(bydgoszcz).title("Marker in Bydgoszcz"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(bydgoszcz));
//    }


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLngBounds.Builder mBounds = new LatLngBounds.Builder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Set up Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Bydgoszcz and move the camera
        LatLng bydgoszcz = new LatLng(53.12199, 18);
        mMap.addMarker(new MarkerOptions().position(bydgoszcz).title("Marker in Bydgoszcz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bydgoszcz));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
    }

    public void centerMap(View view) {
        // Move the map back to Bydgoszcz
        LatLng bydgoszcz = new LatLng(53.12199, 18);
        mMap.addMarker(new MarkerOptions().position(bydgoszcz).title("Marker in Bydgoszcz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bydgoszcz));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
    }
}


