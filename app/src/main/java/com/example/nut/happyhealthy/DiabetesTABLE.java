package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nut on 30/10/2559.
 */
public class DiabetesTABLE {
    //ตัวแปร
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    private String strLastDate;

    public static final String Diabetes = "Diabetes";
    public static final String Diabetes_ID = "D_Id";
    public static final String Diabetes_Date = "D_Date";
    public static final String Diabetes_Time= "D_Time";
    public static final String Diabetes_CostSugarBefore = "D_CostSugarBefore";
    public static final String Diabetes_CostSugarAfter = "D_CostSugarAfter";


    public DiabetesTABLE (Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor

    public Cursor readAllData() {

        Cursor objCursor = readSQLite.query(Diabetes, new String[]{Diabetes_ID, Diabetes_Date, Diabetes_Time,Diabetes_CostSugarBefore,Diabetes_CostSugarAfter}, null, null, null, null, null);

        if (objCursor != null) {
            objCursor.moveToFirst();
        }

        return objCursor;
    }   // readAllData

    public boolean checkCursor() {

        Cursor objCursor = readSQLite.query(Diabetes, new String[]{Diabetes_ID, Diabetes_Date, Diabetes_Time,Diabetes_CostSugarBefore,Diabetes_CostSugarAfter}, null, null, null, null, null);

        if (objCursor != null) {
            objCursor.moveToLast();
        }

        return objCursor.isBeforeFirst();
    }   // checkCursor


    public String lastUpdata() {

        Cursor objCursor = readSQLite.query(Diabetes, new String[] {Diabetes_ID, Diabetes_Date, Diabetes_Time}, null, null, null, null, null);

        if (objCursor != null) {
            objCursor.moveToLast();
            strLastDate = objCursor.getString(objCursor.getColumnIndex( Diabetes_Date));
        }

        return strLastDate;
    }   // lastUpdate


    //Add New Value
    //Add New Value
    public long addNewValueToSQLite(String str_D_Date,  String str_D_Time, int intCostSugarBefore,int intCostSugarAfter) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Diabetes_Date, str_D_Date);
        contentValues.put(Diabetes_Time, str_D_Time);
        contentValues.put(Diabetes_CostSugarBefore, intCostSugarBefore);
        contentValues.put(Diabetes_CostSugarAfter, intCostSugarAfter);
        long diabetes_id = writeSQLite.insert(Diabetes, null, contentValues);
        return diabetes_id;
    }//Add New Value


}//MainClass