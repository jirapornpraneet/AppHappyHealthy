package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nut on 30/10/2559.
 */
public class KidneyTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String Kidney = "Kidney";
    public static final String K_Id = "K_Id";
    public static final String K_Date = "K_Date";
    public static final String K_Time = "K_Time";
    public static final String K_CostGFR  = "K_CostGFR";
    public static final String K_LevelCostGFR = "K_LevelCostGFR";


    public KidneyTABLE (Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor


    //Add New Value
    public long addNewValueToSQLite(String str_K_Date,  String str_K_Time, int intCostGFR,String str_L_cost) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(K_Date, str_K_Date);
        contentValues.put(K_Time, str_K_Time);
        contentValues.put(K_CostGFR, intCostGFR);
        contentValues.put(K_LevelCostGFR,str_L_cost);
        long kidney_id = writeSQLite.insert(Kidney, null, contentValues);
        return kidney_id;
    }//Add New Value

    //เอใส่เพิ่มlistview
    public ArrayList<HashMap<String, String>> getKidneyList() {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Kidney;

        ArrayList<HashMap<String, String>> kidneyList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> kidb = new HashMap<String, String>();
                kidb.put("id", cursor.getString(cursor.getColumnIndex(K_Id)));
                kidb.put("dateKidney", cursor.getString(cursor.getColumnIndex(K_Date)));
                kidb.put("timeKidney", cursor.getString(cursor.getColumnIndex(K_Time)));
                kidb.put("cos_gfr", cursor.getString(cursor.getColumnIndex(K_CostGFR)));
                kidb.put("cos_level", cursor.getString(cursor.getColumnIndex(K_LevelCostGFR)));
                kidneyList.add(kidb);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return kidneyList;
    }




}//MainClass