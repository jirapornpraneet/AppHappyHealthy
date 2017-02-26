package com.jiraporn.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by Nut on 30/10/2559.
 */
public class ExerciseTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;



    public static final String Exercise = "Exercise";
    public static final String Exercise_Id = "Exercise_Id";
    public static final String Exercise_Name = "Exercise_Name";
    public static final String Exercise_Calories = "Exercise_Calories";
    public static final String Exercise_Duration = "Exercise_Duration";
    public static final String Exercise_Disease = "Exercise_Disease";
    public static final String Exercise_Detail = "Exercise_Detail";
    public static final String Exercise_Description = "Exercise_Description";


    public ExerciseTABLE(Context context) {
        myDatabase = new MyDatabase(context);


    }//Constructor

    //Add New Value
    public long addNewValueToSQLite(String str_exe_name, double dou_exe_cal, double dou_duration,  String str_exe_detail ) {

        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Exercise_Name, str_exe_name);
        contentValues.put(Exercise_Calories, dou_exe_cal);
        contentValues.put(Exercise_Duration, dou_duration);
        contentValues.put(Exercise_Detail,str_exe_detail);


        long exercise_id = db.insert(Exercise, null, contentValues);
        return exercise_id;
    }//Add New Value


    //เอใส่เพิ่มlistview
    public ArrayList<HashMap<String, String>> getExeList () {

        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Exercise ;

        ArrayList<HashMap<String, String>> exeList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> exe = new HashMap<String, String>();
                exe.put("exercise_id", cursor.getString(cursor.getColumnIndex(Exercise_Id)));
                exe.put("exercise_name", cursor.getString(cursor.getColumnIndex(Exercise_Name)));
                exe.put("exercise_calories", cursor.getString(cursor.getColumnIndex(Exercise_Calories)));
                exe.put("exercise_duration", cursor.getString(cursor.getColumnIndex(Exercise_Duration)));
                exe.put("exercise_disease", cursor.getString(cursor.getColumnIndex(Exercise_Disease)));
                exe.put("exercise_detail", cursor.getString(cursor.getColumnIndex(Exercise_Detail)));
                exe.put("exercise_description", cursor.getString(cursor.getColumnIndex(Exercise_Description)));
                exeList.add(exe);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return exeList;
    }

    //ตัวExeDetail
    public HashMap<String, String> selectDetailByExeId(int ExeId) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        HashMap<String, String> exe = new HashMap<String, String>();
        String query = "SELECT * FROM " + Exercise + " WHERE " + Exercise_Id + " = " + ExeId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                exe.put("exercise_id", cursor.getString(cursor.getColumnIndex(Exercise_Id)));
                exe.put("exercise_name", cursor.getString(cursor.getColumnIndex(Exercise_Name)));
                exe.put("exercise_calories", cursor.getString(cursor.getColumnIndex(Exercise_Calories)));
                exe.put("exercise_duration", cursor.getString(cursor.getColumnIndex(Exercise_Duration)));
                exe.put("exercise_disease", cursor.getString(cursor.getColumnIndex(Exercise_Disease)));
                exe.put("exercise_detail", cursor.getString(cursor.getColumnIndex(Exercise_Detail)));
                exe.put("exercise_description", cursor.getString(cursor.getColumnIndex(Exercise_Description)));

            } while (cursor.moveToNext());
        }

        return exe;
    }

}//MainClass
