package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nut on 30/10/2559.
 */
public class PressureTABLE {
    //ตัวแปร
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String Pressure = "Pressure";
    public static final String Pressure_ID = "P_Id";
    public static final String Pressure_Date = "P_Date";
    public static final String Pressure_Time = "P_Time";
    public static final String Pressure_CostPressureLow = "P_CostPressureLow";
    public static final String Pressure_CostPressureHigh = "P_CostPressureHigh";


    public PressureTABLE (Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor


    //Add New Value
    //Add New Value
    public long addNewValueToSQLite(String str_P_Date,  String str_P_Time, int intCostPressureLow,int CostPressureHigh) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Pressure_Date, str_P_Date);
        contentValues.put(Pressure_Time, str_P_Time);
        contentValues.put(Pressure_CostPressureLow, intCostPressureLow);
        contentValues.put(Pressure_CostPressureHigh, CostPressureHigh);
        long kidney_id = writeSQLite.insert(Pressure, null, contentValues);
        return kidney_id;
    }//Add New Value
}//MainClass
