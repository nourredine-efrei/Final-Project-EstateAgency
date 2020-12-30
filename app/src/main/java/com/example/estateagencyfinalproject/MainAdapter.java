package com.example.estateagencyfinalproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.gms.maps.MapView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;



    public MainAdapter(Activity context, List<MainData> dataList){

        this.context=context;
        this.dataList=dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_test,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        //Initialize main data

        final MainData data = dataList.get(position);
        //Initialize database

        database = RoomDB.getInstance(context);


        if(data.isSold())

        {
            holder.sold.setText("Sold");
            holder.sold.setTextColor(Color.RED);

        }
        else{
            holder.sold.setText("Not Sold");
            holder.sold.setTextColor(Color.GREEN);

        }
        //Set text on text view
        holder.address.setText("Address : " + data.getAddress());
        holder.type.setText("Type : " + data.getType());

        holder.surface.setText("Surface : " + String.valueOf(data.getSurface()) + " m²");
        holder.price.setText("Price : " + String.valueOf(data.getPrice()) + " €");



        /* ACTION WHEN CLICKING ON EDIT BUTTON */



        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainData d = dataList.get(holder.getAdapterPosition());
                //get id
                final int ID = d.getId();
                String saddress= d.getAddress();
                String stype = d.getType();
               int ssurface = d.getSurface();
               int snumber_of_room = d.getNumber_of_room();
               String sdescription = d.getDescription();
               int sprice = d.getPrice();
               final boolean ssold = d.isSold();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);

                //Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //Initialize height

                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //Set layout
                dialog.getWindow().setLayout(width, height);
                //Show dialog
                dialog.show();

                //init and assign variable
                final EditText Type = dialog.findViewById(R.id.edit_type);
                final EditText Address = dialog.findViewById(R.id.edit_address);
                final EditText Price = dialog.findViewById(R.id.edit_price);
                final EditText Surface = dialog.findViewById(R.id.edit_surface);
                final EditText Number_of_room = dialog.findViewById(R.id.edit_number_of_room);
                final EditText Description = dialog.findViewById(R.id.edit_description);

                final Switch Sold = dialog.findViewById(R.id.edit_simpleSwitch);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                final boolean flag = true;
                //Set text on edit text



                Address.setText((saddress));
               Type.setText((stype));
                Surface.setText((String.valueOf(ssurface)));
                Number_of_room.setText((String.valueOf(snumber_of_room)));
                Description.setText((sdescription));
                Price.setText((String.valueOf(sprice)));

                if (ssold){
                    Sold.setChecked(true);

                }



                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Dismiss dialog

                        dialog.dismiss();
                        //Get updated text from edit text

                        String address = Address.getText().toString().trim();
                        String type = Type.getText().toString().trim();
                        String description = Description.getText().toString().trim();
                        int price = Integer.parseInt(Price.getText().toString());
                        int surface = Integer.parseInt(Surface.getText().toString());
                        int number_of_room = Integer.parseInt(Number_of_room.getText().toString());
                        //Update text in database
                        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                       database.mainDao().update_address(ID,address);
                        database.mainDao().update_type(ID,type);
                        database.mainDao().update_description(ID,description);
                        database.mainDao().update_price(ID,price);
                        database.mainDao().update_surface(ID,surface);
                        database.mainDao().update_number_of_room(ID,number_of_room);
                        database.mainDao().update_date_update(ID,currentDate);
                        //Notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });
            }
        });


        /* ACTION WHEN CLICKING ON DELETE BUTTON */


        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //Delete text from database
                database.mainDao().delete(d);
                //Notify when data is deleted
                int position= holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }
            /*-----------------------------------------------------*/
        });


        /* ACTION WHEN CLICKING ON DETAIL BUTTON */



        holder.btDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                MainData d = dataList.get(holder.getAdapterPosition());
                //get id
                final int ID = d.getId();
                String saddress= d.getAddress();
                String stype = d.getType();
                int ssurface = d.getSurface();
                int snumber_of_room = d.getNumber_of_room();
                String sdescription = d.getDescription();
                final int sprice = d.getPrice();
                final boolean ssold = d.isSold();
                int sagency = d.getAgency_id();
                String sdate_creation = d.getDate_creation();
                String sdate_update = d.getDate_update();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.detail);

                //Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //Initialize height

                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //Set layout
                dialog.getWindow().setLayout(width, height);
                //Show dialog
                dialog.show();

                //init and assign variable
                final TextView Type = dialog.findViewById(R.id.type_detail);
                final TextView Address = dialog.findViewById(R.id.address_detail);
                final TextView Price = dialog.findViewById(R.id.price_detail);
                final TextView Surface = dialog.findViewById(R.id.surface_detail);
                final TextView Number_of_room = dialog.findViewById(R.id.number_of_room_detail);
                final TextView Description = dialog.findViewById(R.id.description_detail);
                final TextView Agency = dialog.findViewById(R.id.agency_detail);
                final TextView DateCreation = dialog.findViewById(R.id.date_of_creation);
                final TextView DateUpdate = dialog.findViewById(R.id.date_of_update);
                final TextView Longitude = dialog.findViewById(R.id.longitude_detail);
                final TextView Latitude = dialog.findViewById(R.id.latitude_detail);
                final Button GooglemapButton = dialog.findViewById(R.id.googlemap_button);
                final Button Currency = dialog.findViewById(R.id.currency);



                final TextView Switch = dialog.findViewById(R.id.sold_detail);
                Address.setText((saddress));
                Type.setText((stype));
                Surface.setText((String.valueOf(ssurface)) + " m²");
                Number_of_room.setText((String.valueOf(snumber_of_room)) + " Room(s)");
                Description.setText((sdescription));
                Price.setText((String.valueOf(sprice)) + " €");
                DateCreation.setText(sdate_creation);
                DateUpdate.setText(sdate_update);

                //Sold or not
                if(ssold){

                    Switch.setText("Sold");
                }
                else {

                    Switch.setText("Not Sold");
                }


                //Agency id
                if (sagency == 1) {

                    Agency.setText("Park Agency");
                }
                if (sagency == 2) {

                    Agency.setText("The Mohan Group");
                }
                if (sagency == 3) {

                    Agency.setText("The Agency");
                }


                /* GET LONGITUDE AND LATITUDE FROM GEOCODER */

                /*-------------------------------------------------------*/
               Geocoder geocoder = new Geocoder(context);
                List<android.location.Address> Alladdress = null;
                try {
                    Alladdress = geocoder.getFromLocationName(saddress, 1);
                    if (Alladdress == null){

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(Alladdress !=null) {

                    if (Alladdress.size() == 0){

                        Longitude.setText("Unknown");
                        Latitude.setText("Unknown");

                    }
                    else{


                        System.out.println(Alladdress.get(0));
                        Address location = Alladdress.get(0);

                        location.getLatitude();
                        location.getLongitude();

                        Longitude.setText(String.valueOf(location.getLongitude()));
                        Latitude.setText(String.valueOf(location.getLatitude()));

                    }

                }

                /*-------------------------------------------------------*/

                final List<android.location.Address> finalAlladdress = Alladdress;




                /* Retrofit implementation */


                /*------------------------------------------------------*/
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.exchangeratesapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                /* ---------------------------------------------------*/


                /* ACTION WHEN CLICKING ON BUTTON CONVERT EUR TO USD */
                Currency.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(final View view) {

                        final Retrofitapi retrofitapi = retrofit.create(Retrofitapi.class);
                        Call<currency> call = retrofitapi.getCurrency();
                        System.out.println(call);
                        call.enqueue(new Callback<currency>() {
                            @Override
                            public void onResponse(Call<currency> call, Response<currency> response) {

                                if(!response.isSuccessful()){

                                    Price.setText("Code :" + response.code());
                                }

                                currency currencies = response.body();




                                    String content = "";
                               double rate = response.body().getRates().getUSD();
                               double usd_price = rate * sprice;



                                    Price.append("     =   "    + String.valueOf(usd_price) + " $");
                                view.setOnClickListener(null);



                            }

                            @Override
                            public void onFailure(Call<currency> call, Throwable t) {

                                Price.setText(t.getMessage());

                            }
                        });


                        /*--------------------------------------------------*/





                    }
                });

                /* ACTION WHEN CLICKING ON GOOGLE MAP BUTTON */
                GooglemapButton.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {




                        Intent a = new Intent(context,MapsActivity.class);
                        double lat = 0;
                        double longitude = 0;
                        if(finalAlladdress !=null) {

                            if(finalAlladdress.size() ==0){


                            }
                            else{
                                Address locationto = finalAlladdress.get(0);
                                lat=   locationto.getLatitude();
                                longitude =  locationto.getLongitude();

                            }
                            a.putExtra("latitude",lat);
                            a.putExtra("longitude", longitude);
                            a.putExtra("isSold", ssold);
                            context.startActivity(a);
                            dialog.dismiss();

                            }




                    }
                });


            }
        });

    }









    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable


        ImageView btEdit, btDelete, btDetail;
        public TextView type;
        public TextView address;
        public TextView surface;
        public TextView price;
        public TextView sold;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable

           // textView = itemView.findViewById(R.id.text_view)
            btDetail = itemView.findViewById(R.id.bt_detail);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
            type = itemView.findViewById(R.id.type);
            address = itemView.findViewById(R.id.address);
            surface = itemView.findViewById(R.id.surface);
            price = itemView.findViewById(R.id.price);
            sold = itemView.findViewById(R.id.sold);


        }
    }
}
