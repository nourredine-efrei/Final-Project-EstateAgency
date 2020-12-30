package com.example.estateagencyfinalproject;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    //First page with the 3 agencies


    private ImageView icone1;
    private ImageView icone2;
    private ImageView icone3;
    private int quel_agence; // 1= agence 1  ||  2 =agence 2 ||  3=agence 3


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icone1 = (ImageView) findViewById(R.id.icone1);
        icone2 = (ImageView) findViewById(R.id.icone2);
        icone3 = (ImageView) findViewById(R.id.icone3);



        icone1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


                quel_agence = 1;


                Intent intent = new Intent(MainActivity.this, ActivityTest.class);
                intent.putExtra("agency_id", quel_agence);
                startActivity(intent);


            }



        });

        icone2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


                quel_agence = 2;


                Intent intent = new Intent(MainActivity.this, ActivityTest.class);
                intent.putExtra("agency_id", quel_agence);
                startActivity(intent);


            }



        });

        icone3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


                quel_agence = 3;
                // startActivity(new Intent(MainActivity.this, SecondActivity.class).putExtra("agency_id", quel_agence));

                Intent intent = new Intent(MainActivity.this, ActivityTest.class);
                intent.putExtra("agency_id", quel_agence);
                startActivity(intent);


            }



        });


        ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}