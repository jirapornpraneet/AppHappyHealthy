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


    public DiabetesTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor

    /**
     * public Cursor readAllData() {
     * <p>
     * Cursor objCursor = readSQLite.query(Diabetes, new String[]{Diabetes_ID, Diabetes_Date, Diabetes_Time, Diabetes_CostSugarBefore, Diabetes_CostSugarAfter}, null, null, null, null, null);
     * <p>
     * if (objCursor != null) {
     * objCursor.moveToFirst();
     * }
     * <p>
     * return objCursor;
     * }   // readAllData
     * <p>
     * public boolean checkCursor() {
     * <p>
     * Cursor objCursor = readSQLite.query(Diabetes, new String[]{Diabetes_ID, Diabetes_Date, Diabetes_Time, Diabetes_CostSugarBefore, Diabetes_CostSugarAfter}, null, null, null, null, null);
     * <p>
     * if (objCursor != null) {
     * objCursor.moveToLast();
     * }
     * <p>
     * return objCursor.isBeforeFirst();
     * }   // checkCursor
     * <p>
     * <p>
     * public String lastUpdata() {
     * <p>
     * Cursor objCursor = readSQLite.query(Diabetes, new String[]{Diabetes_ID, Diabetes_Date, Diabetes_Time}, null, null, null, null, null);
     * <p>
     * if (objCursor != null) {
     * objCursor.moveToLast();
     * strLastDate = objCursor.getString(objCursor.getColumnIndex(Diabetes_Date));
     * }
     * <p>
     * return strLastDate;
     * }   // lastUpdate
     **/


    //Add New Value
    public long addNewValueToSQLite(String str_D_Date, int intCostSugarBefore, int intCostSugarAfter,String str_L_before,String str_L_after) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(D_DateTime, str_D_Date);
        contentValues.put(D_CostSugarBefore, intCostSugarBefore);
        contentValues.put(D_CostSugarAfter, intCostSugarAfter);
        contentValues.put(D_LevelCostBefore,str_L_before);
        contentValues.put(D_LevelCostAfter,str_L_after);
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




}//MainClass