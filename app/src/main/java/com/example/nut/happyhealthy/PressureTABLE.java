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
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String Pressure = "Pressure";
    public static final String P_ID = "P_Id";
    public static final String P_Date = "P_Date";
    public static final String P_Time = "P_Time";
    public static final String P_CostPressureDown = "P_CostPressureDown";
    public static final String P_CostPressureTop = "P_CostPressureTop";
    public static final String P_Cost_Level_Down = "P_Cost_Level_Down";
    public static final String P_Cost_Level_Top = "P_Cost_Level_Top";



    public PressureTABLE (Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor


    //Add New Value
    //Add New Value
    public long addNewValueToSQLite(String str_P_Date,  String str_P_Time, int intCostPressureDown,int intCostPressureTop,String str_LP_cost_down,String str_LP_cost_top) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_Date, str_P_Date);
        contentValues.put(P_Time, str_P_Time);
        contentValues.put(P_CostPressureDown, intCostPressureDown);
        contentValues.put(P_CostPressureTop, intCostPressureTop);
        contentValues.put(P_Cost_Level_Down, str_LP_cost_down);
        contentValues.put(P_Cost_Level_Top,str_LP_cost_top);
        long pressure_id = writeSQLite.insert(Pressure, null, contentValues);
        return pressure_id;
    }//Add New Value

    //เอใส่เพิ่มlistview
    public ArrayList<HashMap<String, String>> getPreList() {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Pressure;

        ArrayList<HashMap<String, String>> preList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> pre = new HashMap<String, String>();
                pre.put("id", cursor.getString(cursor.getColumnIndex(P_ID)));
                pre.put("datePre", cursor.getString(cursor.getColumnIndex(P_Date)));
                pre.put("timePre", cursor.getString(cursor.getColumnIndex(P_Time)));
                pre.put("cos_down", cursor.getString(cursor.getColumnIndex(P_CostPressureDown)));
                pre.put("cos_top", cursor.getString(cursor.getColumnIndex(P_CostPressureTop)));
                pre.put("cos_level_down", cursor.getString(cursor.getColumnIndex(P_Cost_Level_Down)));
                pre.put("cos_level_top", cursor.getString(cursor.getColumnIndex(P_Cost_Level_Top)));
                preList.add(pre);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return preList;
    }


}//MainClass
