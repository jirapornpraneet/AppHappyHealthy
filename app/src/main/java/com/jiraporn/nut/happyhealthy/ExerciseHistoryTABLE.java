package com.jiraporn.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Nut on 30/10/2559.
 */
public class ExerciseHistoryTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String Exercise_History= "Exercise_History";

    public static final String History_Exercise_Date  = "History_Exercise_Date";
    public static final String Exercise_Id = "Exercise_Id";
    public static final String Exercise_Duration = "Exercise_TotalDuration";


    int ExeId;
    String HisExeDate;
    Double ExeDuration;

    public long addExeHis(ExerciseHistoryTABLE dataExe) {

        SQLiteDatabase db = myDatabase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(History_Exercise_Date, dataExe.HisExeDate);
        contentValues.put(Exercise_Id, dataExe.ExeId);
        contentValues.put(Exercise_Duration, dataExe.ExeDuration);

        long exercise_id = db.insert(Exercise_History, null, contentValues);
        Log.d("Id Exe", String.valueOf(exercise_id));
        db.close();
        return exercise_id;
    }//Add New Value

    public ExerciseHistoryTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();


    }//Constructor
}//MainClass
