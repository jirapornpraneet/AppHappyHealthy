package com.example.nut.happyhealthy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nut on 30/10/2559.
 */
public class FoodTypeTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;


    public FoodTypeTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();


    }//Constructor
}//MainClass
