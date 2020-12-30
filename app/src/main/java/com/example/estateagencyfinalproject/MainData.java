package com.example.estateagencyfinalproject;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


//Database used for properties

@Entity
public class MainData implements Serializable {
    public MainData(String type, int price, int surface, int number_of_room, String description, String address, boolean sold, int agency_id, String date_creation, String date_update) {
        this.type = type;
        this.price = price;
        this.surface = surface;
        this.number_of_room = number_of_room;
        this.description = description;
        this.address = address;
        this.sold = sold;
        this.agency_id = agency_id;
        this.date_creation = date_creation;
        this.date_update = date_update;
    }


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="type")
    private String type;
    @ColumnInfo(name="price")
    private int price;
    @ColumnInfo(name="surface")
    private int surface;
    @ColumnInfo(name="number_of_room")
    private int number_of_room;
    @ColumnInfo(name="description")
    private String description;
    @ColumnInfo(name="address")
    private String address;
    @ColumnInfo(name="sold")
    private boolean sold;
    @ColumnInfo(name="agency_id")
    private int agency_id;


    @ColumnInfo(name="date_creation")
    private String date_creation;

    @ColumnInfo(name="date_update")
    private String date_update;

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getDate_update() {
        return date_update;
    }

    public void setDate_update(String date_update) {
        this.date_update = date_update;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public void setNumber_of_room(int number_of_room) {
        this.number_of_room = number_of_room;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getSurface() {
        return surface;
    }

    public int getNumber_of_room() {
        return number_of_room;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public boolean isSold() {
        return sold;
    }

    public int getAgency_id() {
        return agency_id;
    }
}

