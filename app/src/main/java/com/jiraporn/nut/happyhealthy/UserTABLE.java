package com.jiraporn.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


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
    public static final String User_Sex = "User_Sex";
    public static final String User_Age = "User_Age";
    public static final String User_Act = "User_Act";
    //ถ้าเปลี่ยนมาใช้เป็นก้อนให้เป็นชื่อเหมือนกัน


    public UserTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor

    public int addNewInsertToSQLite(String strName, String strSex, String strAge,String strMyACT) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_Name, strName);
        contentValues.put(User_Sex, strSex);
        contentValues.put(User_Age, strAge);
        contentValues.put(User_Act, strMyACT);


        int idUser = (int) writeSQLite.insert(USER, null, contentValues);

        return idUser;
//        writeSQLite.update(USER,contentValues,null,null);
//        return user_id;
    }//Add New Value


    //Add New Value
    public void addNewValueToSQLite(String strName, String strSex, String strAge,String strMyACT) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_Name, strName);
        contentValues.put(User_Sex, strSex);
        contentValues.put(User_Age, strAge);
        contentValues.put(User_Act, strMyACT);

//        long user_id = writeSQLite.insert(USER, null, contentValues);
        writeSQLite.update(USER, contentValues, null, null);
//        return user_id;
    }//Add New Value

    public int checkUserTABLE() {
        readSQLite = myDatabase.getReadableDatabase();
        Cursor objCursor = readSQLite.rawQuery("SELECT * FROM userTABLE", null);

        return objCursor.getCount();
    }

}//MainClass