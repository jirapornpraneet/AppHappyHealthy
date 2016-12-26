package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nut on 30/10/2559.
 */
public class DiabetesTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    private String strLastDate;

    public static final String Diabetes = "Diabetes";
    public static final String D_Id = "D_Id";
    public static final String D_DateTime = "D_DateTime";
    public static final String D_CostSugarBefore = "D_CostSugarBefore";
    public static final String D_CostSugarAfter = "D_CostSugarAfter";
    public static final String D_LevelCostBefore = "D_LevelCostBefore";
    public static final String D_LevelCostAfter = "D_LevelCostAfter";
    public static final String User_Id = "User_Id";

    public static final String Kidney = "Kidney";
    public static final String K_Id = "K_Id";
    public static final String K_DateTime = "K_DateTime";
    public static final String K_CostGFR = "K_CostGFR";
    public static final String K_LevelCostGFR = "K_LevelCostGFR";

    public static final String Pressure = "Pressure";
    public static final String P_Id = "P_Id";
    public static final String P_DateTime = "P_DateTime";
    public static final String P_CostPressureDown = "P_CostPressureDown";
    public static final String P_CostPressureTop = "P_CostPressureTop";
    public static final String P_Cost_Level_Down = "P_Cost_Level_Down";
    public static final String P_Cost_Level_Top = "P_Cost_Level_Top";
    public static final String P_HeartRate = "P_HeartRate";
    public static final String P_HeartRate_Level = "P_HeartRate_Level";


    public DiabetesTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor


    //Add New Value
    public long addNewValueToSQLite(String str_D_Date, int intCostSugarBefore, int intCostSugarAfter, String str_L_before, String str_L_after) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(D_DateTime, str_D_Date);
        contentValues.put(D_CostSugarBefore, intCostSugarBefore);
        contentValues.put(D_CostSugarAfter, intCostSugarAfter);
        contentValues.put(D_LevelCostBefore, str_L_before);
        contentValues.put(D_LevelCostAfter, str_L_after);
        long diabetes_id = writeSQLite.insert(Diabetes, null, contentValues);
        return diabetes_id;
    }//Add New Value

    //เอใส่เพิ่มlistview
    public ArrayList<HashMap<String, String>> getDiabetesList() {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Diabetes;

        ArrayList<HashMap<String, String>> diabList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> diab = new HashMap<String, String>();
                diab.put(D_Id, cursor.getString(cursor.getColumnIndex(D_Id)));
                diab.put(D_DateTime, cursor.getString(cursor.getColumnIndex(D_DateTime)));
                diab.put(D_CostSugarBefore, cursor.getString(cursor.getColumnIndex(D_CostSugarBefore)));
                diab.put(D_CostSugarAfter, cursor.getString(cursor.getColumnIndex(D_CostSugarAfter)));
                diab.put(D_LevelCostBefore, cursor.getString(cursor.getColumnIndex(D_LevelCostBefore)));
                diab.put(D_LevelCostAfter, cursor.getString(cursor.getColumnIndex(D_LevelCostAfter)));
                diabList.add(diab);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return diabList;
    }

    public void delete(int D_id) {

        SQLiteDatabase db = myDatabase.getWritableDatabase();

        db.delete(DiabetesTABLE.Diabetes, DiabetesTABLE.D_Id + "=?", new String[]{String.valueOf(D_id)});
        db.close();
    }


    public HashMap<String, String> selectDetailByDiabetes(String datedisease) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        HashMap<String, String> disease = new HashMap<>();
        String query = "select * from " +
                "(select MAX(" + D_Id + "),* from " + Diabetes + " where " + D_DateTime + " LIKE '" + datedisease + "')," +
                "(select MAX(" + K_Id + "),* from " + Kidney + " where " + K_DateTime + " LIKE '" + datedisease + "')," +
                "(select MAX(" + P_Id + "),* from " + Pressure + " where " + P_DateTime + " LIKE '" + datedisease + "' )";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                disease.put(D_Id, cursor.getString(cursor.getColumnIndex(D_Id)));
                disease.put(D_DateTime, cursor.getString(cursor.getColumnIndex(D_DateTime)));
                disease.put(D_CostSugarBefore, cursor.getString(cursor.getColumnIndex(D_CostSugarBefore)));
                disease.put(D_CostSugarAfter, cursor.getString(cursor.getColumnIndex(D_CostSugarAfter)));
                disease.put(D_LevelCostBefore, cursor.getString(cursor.getColumnIndex(D_LevelCostBefore)));
                disease.put(D_LevelCostAfter, cursor.getString(cursor.getColumnIndex(D_LevelCostAfter)));
                disease.put(K_Id, cursor.getString(cursor.getColumnIndex(K_Id)));
                disease.put(K_DateTime, cursor.getString(cursor.getColumnIndex(K_DateTime)));
                disease.put(K_CostGFR, cursor.getString(cursor.getColumnIndex(K_CostGFR)));
                disease.put(K_LevelCostGFR, cursor.getString(cursor.getColumnIndex(K_LevelCostGFR)));
                disease.put(P_Id, cursor.getString(cursor.getColumnIndex(P_Id)));
                disease.put(P_DateTime, cursor.getString(cursor.getColumnIndex(P_DateTime)));
                disease.put(P_CostPressureDown, cursor.getString(cursor.getColumnIndex(P_CostPressureDown)));
                disease.put(P_CostPressureTop, cursor.getString(cursor.getColumnIndex(P_CostPressureTop)));
                disease.put(P_Cost_Level_Down, cursor.getString(cursor.getColumnIndex(P_Cost_Level_Down)));
                disease.put(P_Cost_Level_Top, cursor.getString(cursor.getColumnIndex(P_Cost_Level_Top)));
                disease.put(P_HeartRate, cursor.getString(cursor.getColumnIndex(P_HeartRate)));
                disease.put(P_HeartRate_Level, cursor.getString(cursor.getColumnIndex(P_HeartRate_Level)));
            } while (cursor.moveToNext());
        }

        Log.d("Show Data 1",query);
        Log.d("Show Data",disease.toString());

        return disease;
    }


}//MainClass