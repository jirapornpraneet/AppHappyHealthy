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
    public static final String User_Gender = "User_Gender";
    public static final String User_Age = "User_Age";
    //ถ้าเปลี่ยนมาใช้เป็นก้อนให้เป็นชื่อเหมือนกัน


    public UserTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();

    }//Constructor




    public int addNewInsertToSQLite(String strName, String strGender, String strAge) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(User_Name, strName);
    contentValues.put(User_Gender, strGender);
    contentValues.put(User_Age, strAge);


    int idUser = (int) writeSQLite.insert(USER, null, contentValues);

    return idUser;
//        writeSQLite.update(USER,contentValues,null,null);
//        return user_id;
}//Add New Value



    public void addNewValueToSQLite(String strName, String strGender, String strAge) {
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

        return objCursor.getCount();
    }


    public int DeleteUserTABLE() {
        readSQLite = myDatabase.getReadableDatabase();
        Cursor objCursor = readSQLite.rawQuery("Delete FROM userTABLE", null);

        return objCursor.getCount();
    }

    public void deleteUser(int User_Id) {

        SQLiteDatabase db = myDatabase.getWritableDatabase();

        db.delete(UserTABLE.USER, UserTABLE.User_Id + "=?", new String[]{String.valueOf(User_Id)});
        db.close();
    }


}//MainClass