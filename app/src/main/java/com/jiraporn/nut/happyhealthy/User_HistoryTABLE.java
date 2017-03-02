package com.jiraporn.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nut on 2/3/2560.
 */

public class User_HistoryTABLE {

    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String User_History = "User_History";

    public static final String History_User_DateTime  = "History_User_DateTime";
    public static final String History_User_Weight = "History_User_Weight";
    public static final String History_User_BMI = "History_User_BMI";
    public static final String History_User_BMR = "History_User_BMR";


    public User_HistoryTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor



    public void addNewInsertToSQLite(String strDateTime, double douWeight, double douBmr, double douBmi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(History_User_DateTime, strDateTime);
        contentValues.put(History_User_Weight, douWeight);
        contentValues.put(History_User_BMI, douBmi);
        contentValues.put(History_User_BMR, douBmr);
        writeSQLite.insert(User_History, null, contentValues);
//        writeSQLite.update(USER,contentValues,null,null);
//        return user_id;
    }//Add New Value


    //Add New Value
    public void addNewValueToSQLite(String strDateTime, double douWeight, double douBmr, double douBmi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(History_User_DateTime, strDateTime);
        contentValues.put(History_User_Weight, douWeight);
        contentValues.put(History_User_BMI, douBmi);
        contentValues.put(History_User_BMR, douBmr);
//        long user_id = writeSQLite.insert(USER, null, contentValues);
        writeSQLite.update(User_History, contentValues, null, null);
//        return user_id;
    }//Add New Value

    public int checkUserHistoryTABLE(){
        readSQLite = myDatabase.getReadableDatabase();
        Cursor objCursor = readSQLite.rawQuery("SELECT * FROM User_History", null);
        return objCursor.getCount();
    }
}//MianClass
