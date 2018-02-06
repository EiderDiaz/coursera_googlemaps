package com.example.eider.coursera_googlemaps;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eider.coursera_googlemaps.RestApi.EndPoints;
import com.example.eider.coursera_googlemaps.RestApi.RestAPIAdapter;
import com.example.eider.coursera_googlemaps.RestApi.UsuarioResponce;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String lugar;
    int resourseDeImagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lanzarNotificacion();

        if (getIntent().getExtras().getString("playa") != null){
         lugar  = getIntent().getExtras().getString("playa");
            resourseDeImagen=  R.drawable.beach;
        }
        if (getIntent().getExtras().getString("cerro") != null){
            lugar  = getIntent().getExtras().getString("cerro");
            resourseDeImagen=  R.drawable.mountains;

        }
        if (getIntent().getExtras().getString("road") != null){
            lugar  = getIntent().getExtras().getString("road");
            resourseDeImagen=  R.drawable.road;

        }
        if (getIntent().getExtras().getString("parque") != null){
            lugar  = getIntent().getExtras().getString("parque");
            resourseDeImagen=  R.drawable.valley;

        }
    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

    View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
    ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
    Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
            Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
    Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
}
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
        mMap.addMarker(new MarkerOptions()
                .position(place).title("Marker in place")
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(resourseDeImagen))));
    }


    // TODO: 02/02/2018 lanzar notificacion
    public void lanzarNotificacion(){
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token) {
        Log.d("FIREBASE_TOKEN",token);
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        EndPoints endPoints = restAPIAdapter.establecerConexionRestAPI();
        Call<UsuarioResponce> usuarioResponceCall = endPoints.registarTokenId(token);

        usuarioResponceCall.enqueue(new Callback<UsuarioResponce>() {
            @Override
            public void onResponse(Call<UsuarioResponce> call, Response<UsuarioResponce> response) {
                UsuarioResponce usuarioResponce = response.body();
                Toast.makeText(MapsActivity.this, "id :"+usuarioResponce.getId()+"\ntoken: "+usuarioResponce.getToken(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UsuarioResponce> call, Throwable t) {

            }
        });

    }
}
