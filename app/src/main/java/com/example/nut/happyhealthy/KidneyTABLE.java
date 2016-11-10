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

    //เอใส่เพิ่มlistview
    public ArrayList<HashMap<String, String>> getKidneyList() {
        SQLiteDatabase db = objMyOpenHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Kidney;

        ArrayList<HashMap<String, String>> kidneyList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> kidb = new HashMap<String, String>();
                kidb.put("id", cursor.getString(cursor.getColumnIndex(Kidney_ID)));
                kidb.put("dateKidney", cursor.getString(cursor.getColumnIndex(Kidney_Date)));
                kidb.put("timeKidney", cursor.getString(cursor.getColumnIndex(Kidney_Time)));
                kidb.put("cos_gfr", cursor.getString(cursor.getColumnIndex(Kidney_CostGFR)));
                kidneyList.add(kidb);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return kidneyList;
    }



}//MainClass