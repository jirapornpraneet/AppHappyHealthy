package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

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

    //เอใส่เพิ่มlistview
    public ArrayList<HashMap<String, String>> getPreList() {
        SQLiteDatabase db = objMyOpenHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Pressure;

        ArrayList<HashMap<String, String>> preList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> pre = new HashMap<String, String>();
                pre.put("id", cursor.getString(cursor.getColumnIndex(Pressure_ID)));
                pre.put("datePre", cursor.getString(cursor.getColumnIndex(Pressure_Date)));
                pre.put("timePre", cursor.getString(cursor.getColumnIndex(Pressure_Time)));
                pre.put("cos_down", cursor.getString(cursor.getColumnIndex(Pressure_CostPressureDown)));
                pre.put("cos_top", cursor.getString(cursor.getColumnIndex(Pressure_CostPressureTop)));
                preList.add(pre);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return preList;
    }


}//MainClass
