package com.example.eider.coursera_googlemaps;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button button ;
    ImageView playa,cerro,road,parque ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playa = (ImageView)findViewById(R.id.playa);
        cerro = (ImageView)findViewById(R.id.cerro);
        road = (ImageView)findViewById(R.id.road);
        parque = (ImageView)findViewById(R.id.parque);

    playa.setOnClickListener(clickListener);
    cerro.setOnClickListener(clickListener);
    road.setOnClickListener(clickListener);
    parque.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);

            switch (v.getId()){
                case R.id.playa: //si
                    intent.putExtra("playa","playa");
                    break;
                case R.id.cerro: //si
                    intent.putExtra("cerro","cerro");
                    break;
                case R.id.road: //si
                    intent.putExtra("road","road");
                    break;
                case R.id.parque: //si
                    intent.putExtra("parque","parque");
                    break;

            }
            startActivity(intent);


        }
    };


}
