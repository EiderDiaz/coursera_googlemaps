package com.example.eider.coursera_googlemaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String lugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (getIntent().getExtras().getString("playa") != null){
         lugar  = getIntent().getExtras().getString("playa");
        }
        if (getIntent().getExtras().getString("cerro") != null){
            lugar  = getIntent().getExtras().getString("cerro");
        }
        if (getIntent().getExtras().getString("road") != null){
            lugar  = getIntent().getExtras().getString("road");
        }
        if (getIntent().getExtras().getString("parque") != null){
            lugar  = getIntent().getExtras().getString("parque");
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near place, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng place = new  LatLng(25.78969044, -109.00141239);
        // Add a marker in place and move the camera
        switch (lugar){
            case "playa": //si
                place = new  LatLng(25.58270461, -109.06780243);
            break;
            case "cerro": //si
                place = new  LatLng(25.8074639, -108.97506237);
                break;
            case "road": //si
                place = new  LatLng(25.80738662, -108.98201466);
                break;
            case "parque": //si
                place = new   LatLng(25.78969044, -109.00141239);
                break;

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        mMap.addMarker(new MarkerOptions().position(place).title("Marker in place"));
    }
}
