package pl.nataliana.foreignersinbydgoszcz.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import pl.nataliana.foreignersinbydgoszcz.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLngBounds.Builder mBounds = new LatLngBounds.Builder();
    private static final String LAT = "latitute";
    private static final String LONG = "longitude";
    private static final String ZOOM = "zoomLevel";
    private final double latitudeBydg = 53.12199;
    private final double longitudeBydg = 18;
    private final LatLng bydgoszcz = new LatLng(latitudeBydg, longitudeBydg);
    private float zoomLevelBydg = 15.0f;
    private double latitude, longitude;
    private float zoomLevel;

    @Override
    protected void onCreate(Bundle outState) {
        super.onCreate(outState);
        setContentView(R.layout.activity_maps);

        // Set up Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Save map state
        if (outState != null) {
            latitude = outState.getDouble(LAT);
            longitude = outState.getDouble(LONG);
            zoomLevel = outState.getFloat(ZOOM);

        } else {
            latitude = latitudeBydg;
            longitude = longitudeBydg;
            zoomLevel = zoomLevelBydg;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(LAT, mMap.getCameraPosition().target.latitude);
        outState.putDouble(LONG, mMap.getCameraPosition().target.longitude);
        outState.putFloat(ZOOM, mMap.getCameraPosition().zoom);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Bydgoszcz and move the camera
        mMap.addMarker(new MarkerOptions().position(bydgoszcz).title("Marker in Bydgoszcz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bydgoszcz,zoomLevelBydg));
        centerMap(null);
    }

    public void centerMap(View view) {
        // Move the map back to Bydgoszcz
        mMap.addMarker(new MarkerOptions().position(bydgoszcz).title("Marker in Bydgoszcz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bydgoszcz,zoomLevelBydg ));
    }
}


