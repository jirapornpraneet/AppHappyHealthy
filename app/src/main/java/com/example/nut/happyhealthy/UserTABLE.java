package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Nut on 29/10/2559.
 */
public class UserTABLE {
    //ตัวแปร
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String USER = "userTABLE";
    public static final String UserId = "User_Id";
    public static final String UserName = "User_Name";
    public static final String UserSex = "User_Sex";
    public static final String UserAge = "User_Age";
    public static final String UserHeight = "User_Height";
    public static final String UserWeight = "User_Weight";
    public static final String UserBMR = "User_BMR";
    public static final String UserBMI = "User_BMI";


    public UserTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor


    //Add New Value
    //Add New Value
    public long addNewValueToSQLite(String strName, String strSex, String strAge, int intHeight, double douWeight, double douBmr, double douBmi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserName, strName);
        contentValues.put(UserSex, strSex);
        contentValues.put(UserAge, strAge);
        contentValues.put(UserHeight, intHeight);
        contentValues.put(UserWeight, douWeight);
        contentValues.put(UserBMR, douBmr);
        contentValues.put(UserBMI, douBmi);
        long user_id = writeSQLite.insert(USER, null, contentValues);
        return user_id;
    }//Add New Value


}//MainClass