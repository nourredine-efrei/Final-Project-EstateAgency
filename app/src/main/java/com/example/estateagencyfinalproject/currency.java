package com.example.estateagencyfinalproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/* This class is used for Retrofit (EURO TO USD)*/


public class currency {

    @SerializedName("rates")
    @Expose
    private Rates rates;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
