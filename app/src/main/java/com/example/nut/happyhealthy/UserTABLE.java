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
    public static final String User_BirthDay = "User_BirthDay";
    public static final String User_Height = "User_Height";
    public static final String User_Weight = "User_Weight";
    public static final String User_BMR = "User_BMR";
    public static final String User_BMI = "User_BMI";
    public static final String User_Disease = "User_Disease";

    public UserTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor


    //Add New Value
    public long addNewValueToSQLite(String name, String sex, String birthday, int height, double weight, int bmr, double bmi,String disease) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_Name, name);
        contentValues.put(User_Sex, sex);
        contentValues.put(User_BirthDay,birthday);
        contentValues.put(User_Height,height);
        contentValues.put(User_Weight, weight);
        contentValues.put(User_BMR, bmr);
        contentValues.put(User_BMI,bmi);
        contentValues.put(User_Disease,  disease);


        return writeSQLite.insert(USER, null, contentValues);
    }

}//MainClass
