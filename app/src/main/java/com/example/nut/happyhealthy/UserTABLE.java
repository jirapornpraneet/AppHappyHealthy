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

    public static final String USER = "USER";
    public static final String User_Id = "User_Id";
    public static final String User_Name = "User_Name";
    public static final String User_Sex = "User_Sex";
    public static final String User_Age = "User_Age";
    public static final String User_Height = "User_Height";
    public static final String User_Weight = "User_Weight";
    public static final String User_BMR = "User_BMR";
    public static final String User_BMI = "User_BMI";


    public UserTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor


    //Add New Value
    //Add New Value
    public long addNewValueToSQLite(String strName, String strSex,int intAge, int intHeight, double douWeight,double douBmr, double douBmi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_Name, strName);
        contentValues.put(User_Sex, strSex);
        contentValues.put(User_Age,intAge);
        contentValues.put(User_Height,intHeight);
        contentValues.put(User_Weight, douWeight);
        contentValues.put(User_BMR, douBmr);
        contentValues.put(User_BMI,douBmi);

        return writeSQLite.insert(USER, null, contentValues);
    }//Add New Value


}//MainClass
