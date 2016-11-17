package com.example.nut.happyhealthy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nut on 30/10/2559.
 */
public class ExerciseTypeTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String ExerciseType_Id = "ExerciseType_Id";
    public static final String ExerciseType_Name = "ExerciseType_Name";


    public ExerciseTypeTABLE(Context context) {
         myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();


    }//Constructor
}//MainClass
