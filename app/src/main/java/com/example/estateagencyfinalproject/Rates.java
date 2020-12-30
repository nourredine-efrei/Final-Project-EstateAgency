package com.example.estateagencyfinalproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//This class is used for Retrofit
public class Rates {

    @SerializedName("USD")
    @Expose
    private Double USD;

    public Double getUSD() {
        return USD;
    }

    public void setUSD(Double USD) {
        this.USD = USD;
    }

}