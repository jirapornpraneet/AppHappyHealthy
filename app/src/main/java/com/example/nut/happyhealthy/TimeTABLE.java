package com.example.nut.happyhealthy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Nut on 29/10/2559.
 */
public class TimeTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;


    public TimeTABLE(Context context) {
        myDatabase   = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();


    }//Constructor
}//MainClass
