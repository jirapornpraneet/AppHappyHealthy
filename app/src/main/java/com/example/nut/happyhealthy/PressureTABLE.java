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
    public static final String Pressure_CostPressureDown = "P_CostPressureDown";
    public static final String Pressure_CostPressureTop = "P_CostPressureTop";


    public PressureTABLE (Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor


    //Add New Value
    //Add New Value
    public long addNewValueToSQLite(String str_P_Date,  String str_P_Time, int intCostPressureDown,int intCostPressureTop) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Pressure_Date, str_P_Date);
        contentValues.put(Pressure_Time, str_P_Time);
        contentValues.put(Pressure_CostPressureDown, intCostPressureDown);
        contentValues.put(Pressure_CostPressureTop, intCostPressureTop);
        long pressure_id = writeSQLite.insert(Pressure, null, contentValues);
        return pressure_id;
    }//Add New Value
}//MainClass
