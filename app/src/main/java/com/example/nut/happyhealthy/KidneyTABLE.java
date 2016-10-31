package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nut on 30/10/2559.
 */
public class KidneyTABLE {
    //ตัวแปร
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String Kidney = "Kidney";
    public static final String Kidney_ID = "K_Id";
    public static final String Kidney_Date = "K_Date";
    public static final String Kidney_Time= "K_Time";
    public static final String Kidney_CostGFR = "K_CostGFR";


    public KidneyTABLE (Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor


    //Add New Value
    //Add New Value
    public long addNewValueToSQLite(String str_K_Date,  String str_K_Time, int intCostGFR) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Kidney_Date, str_K_Date);
        contentValues.put(Kidney_Time, str_K_Time);
        contentValues.put(Kidney_CostGFR, intCostGFR);
        long kidney_id = writeSQLite.insert(Kidney, null, contentValues);
        return kidney_id;
    }//Add New Value
}//MainClass
