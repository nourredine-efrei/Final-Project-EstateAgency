package com.example.estateagencyfinalproject;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {


    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    @Insert
    void insertAll(MainData... mainData);

    @Delete
    void delete(MainData mainData);

    @Delete
    void reset(List<MainData> mainData);


    @Query("UPDATE maindata SET address = :sText WHERE ID = :sID")
    void update_address(int sID, String sText);

    @Query("UPDATE maindata SET price = :sText WHERE ID = :sID")
    void update_price(int sID, int sText);

    @Query("UPDATE maindata SET type = :sText WHERE ID = :sID")
    void update_type(int sID, String sText);

    @Query("UPDATE maindata SET description = :sText WHERE ID = :sID")
    void update_description(int sID, String sText);

    @Query("UPDATE maindata SET number_of_room = :sText WHERE ID = :sID")
    void update_number_of_room(int sID, int sText);

    @Query("UPDATE maindata SET surface = :sText WHERE ID = :sID")
    void update_surface(int sID, int sText);


    @Query("UPDATE maindata SET date_update = :sText WHERE ID = :sID")
    void update_date_update(int sID, String sText);




    @Query("SELECT * FROM maindata")
    List<MainData> getAll();

    @Query("SELECT * FROM maindata WHERE     agency_id=1 ")
    List<MainData> getProperties_1();

    @Query("SELECT * FROM maindata WHERE     agency_id=2 ")
    List<MainData> getProperties_2();

    @Query("SELECT * FROM maindata WHERE     agency_id=3 ")
    List<MainData> getProperties_3();
}
