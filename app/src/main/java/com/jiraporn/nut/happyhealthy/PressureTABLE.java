package com.jiraporn.nut.happyhealthy;

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
    public static final String P_Id = "P_Id";
    public static final String P_DateTime = "P_DateTime";
    public static final String P_CostPressureDown = "P_CostPressureDown";
    public static final String P_CostPressureTop = "P_CostPressureTop";
    public static final String P_Cost_Level_Down = "P_Cost_Level_Down";
    public static final String P_Cost_Level_Top = "P_Cost_Level_Top";
    public static final String P_HeartRate = "P_HeartRate";
    public static final String P_HeartRate_Level = "P_HeartRate_Level";
    public static final String User_Id = "User_Id";



    public PressureTABLE (Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor


    //Add New Value
    public long addNewValueToSQLite(String str_P_Date,   int intCostPressureDown,int intCostPressureTop,String str_LP_cost_down,String str_LP_cost_top,int intHeart,String str_heart) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_DateTime, str_P_Date);
        contentValues.put(P_CostPressureDown, intCostPressureDown);
        contentValues.put(P_CostPressureTop, intCostPressureTop);
        contentValues.put(P_Cost_Level_Down, str_LP_cost_down);
        contentValues.put(P_Cost_Level_Top,str_LP_cost_top);
        contentValues.put(P_HeartRate, intHeart);
        contentValues.put(P_HeartRate_Level,str_heart);
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
                pre.put(P_Id, cursor.getString(cursor.getColumnIndex(P_Id)));
                pre.put(P_DateTime, cursor.getString(cursor.getColumnIndex(P_DateTime)));
                pre.put(P_CostPressureDown, cursor.getString(cursor.getColumnIndex(P_CostPressureDown)));
                pre.put(P_CostPressureTop, cursor.getString(cursor.getColumnIndex(P_CostPressureTop)));
                pre.put(P_Cost_Level_Down, cursor.getString(cursor.getColumnIndex(P_Cost_Level_Down)));
                pre.put(P_Cost_Level_Top, cursor.getString(cursor.getColumnIndex(P_Cost_Level_Top)));
                pre.put(P_HeartRate, cursor.getString(cursor.getColumnIndex(P_HeartRate)));
                pre.put(P_HeartRate_Level, cursor.getString(cursor.getColumnIndex(P_HeartRate_Level)));
                preList.add(pre);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return preList;
    }

    public void delete(int P_id) {

        SQLiteDatabase db = myDatabase.getWritableDatabase();

        db.delete(PressureTABLE.Pressure, PressureTABLE.P_Id + "=?", new String[]{String.valueOf(P_id)});
        db.close();
    }


}//MainClass
