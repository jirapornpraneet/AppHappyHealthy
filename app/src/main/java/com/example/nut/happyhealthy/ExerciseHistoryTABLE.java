package com.example.nut.happyhealthy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nut on 30/10/2559.
 */
public class ExerciseHistoryTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String Exercise_History= "Exercise_History";
    public static final String History_Exercise_Id = "History_Exercise_Id";
    public static final String History_Exercise_Date  = "History_Exercise_Date";
    public static final String Exercise_TotalDuration  = "Exercise_TotalDuration";
    public static final String User_Id = "User_Id";
    public static final String Exercise_Id = "Exercise_Id";





    public ExerciseHistoryTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();


    }//Constructor
}//MainClass
