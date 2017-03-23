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

    public static final String History_User_Id = "History_User_Id";
    public static final String History_User_DateTime = "History_User_DateTime";
    public static final String History_User_Weight = "History_User_Weight";
    public static final String History_User_BMI = "History_User_BMI";
    public static final String History_User_BMR = "History_User_BMR";
    public static final String History_User_Height = "History_User_Height";
    public static final String User_Id = "User_Id";


    public User_HistoryTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor


    public void insertUserHistory(String strDateTime, double douWeight, double douBmr, double douBmi, int intHeight, int userId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(History_User_DateTime, strDateTime);
        contentValues.put(History_User_Weight, douWeight);
        contentValues.put(History_User_BMI, douBmi);
        contentValues.put(History_User_BMR, douBmr);
        contentValues.put(History_User_Height, intHeight);
        contentValues.put(User_Id, userId);
        writeSQLite.insert(User_History, null, contentValues);
//        writeSQLite.update(USER,contentValues,null,null);
//        return user_id;
    }//Add New Value


    //Add New Value
    public void updateUserHistory(String strDateTime, double douWeight, double douBmr, double douBmi, int intHeight) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(History_User_DateTime, strDateTime);
        contentValues.put(History_User_Weight, douWeight);
        contentValues.put(History_User_BMI, douBmi);
        contentValues.put(History_User_BMR, douBmr);
        contentValues.put(History_User_Height, intHeight);
//        long user_id = writeSQLite.insert(USER, null, contentValues);

//        return user_id;
        writeSQLite.update(User_History, contentValues, null, null);
    }//Add New Value



    /**public Cursor checkUserHistoryTABLE(String dateSearch) {
        readSQLite = myDatabase.getReadableDatabase();
        Cursor objCursor = readSQLite.rawQuery("SELECT * FROM " + User_History + " WHERE LIKE("
                + History_User_DateTime + ", '" + dateSearch + "');", null);

        return objCursor;
    }**/
}//MianClass
