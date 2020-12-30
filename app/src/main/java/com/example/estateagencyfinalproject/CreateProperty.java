package com.example.estateagencyfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateProperty extends AppCompatActivity {


    EditText type;
   EditText price;
    EditText surface;
   EditText number_of_room;
    EditText description;
    EditText address;
    Button button_save;
    Boolean switch_value = false;
    int agency_id_property;
    RoomDB database;
    private static final String TAG= "CreateProperty";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_property);




        //Obtenir ID de l'agence choisi

        Intent intent = getIntent();

        if(intent.hasExtra("agency_id")){

            agency_id_property = intent.getIntExtra("agency_id", 1);

        }



        //Fin obtention ID agence

        type = findViewById(R.id.type);
        price = findViewById(R.id.price);
        surface= findViewById(R.id.surface);
        number_of_room = findViewById(R.id.number_of_room);
        description = findViewById(R.id.description);
        address = findViewById(R.id.address);

        button_save=findViewById(R.id.button_save);





        Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);



// check current state of a Switch (true or false).






        database = RoomDB.getInstance(this);


        simpleSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!switch_value){
                    switch_value = true;

                }
                else{
                    switch_value = false;

                }

                Log.d(TAG, String.valueOf(switch_value));
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                //Save to database

                int price_int = Integer.parseInt(price.getText().toString());
                int surface_int = Integer.parseInt(surface.getText().toString());
                int number_of_room_int = Integer.parseInt(number_of_room.getText().toString());

               MainData mainData = new MainData(type.getText().toString(),  price_int, surface_int, number_of_room_int, description.getText().toString(), address.getText().toString(), switch_value, agency_id_property, currentDate, currentDate);
              database.mainDao().insertAll(mainData);


              startActivity(new Intent(CreateProperty.this, ActivityTest.class )
                .putExtra("agency_id_property", agency_id_property));
            }
        });

    }

}




