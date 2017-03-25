package com.jiraporn.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Nut on 29/10/2559.
 */
public class UserTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String USER = "userTABLE";
    public static final String User_Id = "User_Id";
    public static final String User_Name = "User_Name";
    public static final String User_Gender = "User_Gender";
    public static final String User_Age = "User_Age";
    //ถ้าเปลี่ยนมาใช้เป็นก้อนให้เป็นชื่อเหมือนกัน


    public UserTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor


    public void insertUserTable(String strName, String strGender, String strAge) {
        writeSQLite = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_Name, strName);
        contentValues.put(User_Gender, strGender);
        contentValues.put(User_Age, strAge);

        writeSQLite.insert(USER, null, contentValues);

//        writeSQLite.update(USER,contentValues,null,null);
//        return user_id;
    }//Add New Value


    public void updateUserTable(String strName, String strGender, String strAge) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_Name, strName);
        contentValues.put(User_Gender, strGender);
        contentValues.put(User_Age, strAge);

//        long user_id = writeSQLite.insert(USER, null, contentValues);


        writeSQLite.update(USER, contentValues, null, null);
//        return user_id;
    }//Add New Value

    public int checkUserTABLE() {
        readSQLite = myDatabase.getReadableDatabase();
        Cursor objCursor = readSQLite.rawQuery("SELECT * FROM userTABLE", null);

        objCursor.moveToFirst();

        return objCursor.getCount();
    }


    public Cursor getUser() {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "SELECT *,MAX(" + User_Id + ") FROM " + USER;

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public HashMap<String, String> getDataUser() {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "SELECT *,MAX(" + User_Id + ") FROM " + USER;
        HashMap<String, String> dataHis = new HashMap<>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        dataHis.put(UserTABLE.User_Id, cursor.getString(cursor.getColumnIndex(UserTABLE.User_Id)));
        dataHis.put(UserTABLE.User_Name, cursor.getString(cursor.getColumnIndex(UserTABLE.User_Name)));
        dataHis.put(UserTABLE.User_Gender, cursor.getString(cursor.getColumnIndex(UserTABLE.User_Gender)));
        dataHis.put(UserTABLE.User_Age, cursor.getString(cursor.getColumnIndex(UserTABLE.User_Age)));

        cursor.close();

        return dataHis;
    }


    public void deleteUser() {
        SQLiteDatabase db = myDatabase.getWritableDatabase();
//        db.delete(User_HistoryTABLE.User_History, User_HistoryTABLE.History_User_Id + "=?", new String[]{String.valueOf(History_User_id)});
        db.delete(UserTABLE.USER, null, null);
        db.close();
    }


}//MainClass