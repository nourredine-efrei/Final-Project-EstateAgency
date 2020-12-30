package com.example.estateagencyfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActivityTest extends AppCompatActivity {

    private static final String TAG= "MainActivity";
    private int agency_id;
    EditText editText;
    RecyclerView recyclerView;

    FloatingActionButton fab;

    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;
    List<MainData> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        //Variable

        editText = findViewById(R.id.edit_address);

        recyclerView = findViewById(R.id.recycler_test);


        //Initialize database

        database = RoomDB.getInstance(this);
        //Store database value in data list
        dataList = database.mainDao().getAll();






        //Obtenir ID de l'agence choisi

        Intent intent = getIntent();

        if(intent.hasExtra("agency_id")){

            agency_id = intent.getIntExtra("agency_id", 1);

        }
        if(intent.hasExtra("agency_id_property")){

            agency_id = intent.getIntExtra("agency_id_property", 1);

        }

        /* Request change according to the agency

      Properties created by the choosed agency will be display, the others will not
         */

        if(agency_id==1){

            dataList = database.mainDao().getProperties_1();
        }
        if(agency_id==2){

           dataList = database.mainDao().getProperties_2();
        }
        if(agency_id==3){

            dataList = database.mainDao().getProperties_3();
        }



        //Initialize adapter
        adapter = new MainAdapter(ActivityTest.this, dataList);

        //Set adapter

        recyclerView.setAdapter(adapter);
        //Initialize linear layout manager

        linearLayoutManager = new LinearLayoutManager(this);
        //Set layout manager

        recyclerView.setLayoutManager(linearLayoutManager);

        fab= findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick: pressed!");
                    startActivity(new Intent(ActivityTest.this, CreateProperty.class).putExtra("agency_id", agency_id));
            }
        });



    }
}
