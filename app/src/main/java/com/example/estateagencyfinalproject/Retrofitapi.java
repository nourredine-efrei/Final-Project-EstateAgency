package com.example.estateagencyfinalproject;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

//Retrofit Request for USD EURO Converter
public interface Retrofitapi {

    @GET("latest?symbols=USD")
    Call<currency> getCurrency();
}
