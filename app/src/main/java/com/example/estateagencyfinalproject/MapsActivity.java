package com.example.estateagencyfinalproject;

import android.content.Intent;
import android.location.Location;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


//This class managed the google map page by marking the position of the current property

public class MapsActivity  extends FragmentActivity implements OnMapReadyCallback  {

    GoogleMap map;
    double longitude;
    double latitude;
    boolean isSold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //Take longitude and latitude from MainAdapter

        Intent intent = getIntent();

        if(intent.hasExtra("longitude")){

            longitude = intent.getDoubleExtra("longitude", 1);

        }
        if(intent.hasExtra("latitude")) {

            latitude = intent.getDoubleExtra("latitude", 1);

        }

        if(intent.hasExtra("isSold")){

           isSold = intent.getBooleanExtra("isSold", true);

        }
        setContentView(R.layout.googlemap);
        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        double longitude2;
        double latitude2;

        LatLng property = new LatLng(latitude, longitude);
        if(isSold){ //If property sold -> red mark

            map.addMarker(new MarkerOptions().position(property));
        }
        else{ //If property not sold -> green mark
            map.addMarker(new MarkerOptions().position(property).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        }

        map.moveCamera(CameraUpdateFactory.newLatLng(property));

    }
}
