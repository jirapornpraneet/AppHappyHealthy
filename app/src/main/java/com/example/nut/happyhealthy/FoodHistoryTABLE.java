package com.example.nut.happyhealthy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nut on 30/10/2559.
 */
public class FoodHistoryTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String Food_History = "Food_History";
    public static final String History_Food_Id = "History_Food_Id";
    public static final String History_Food_Date  = "History_Food_Date";
    public static final String Food_Amount  = "Food_Amount";
    public static final String User_Id = "User_Id";
    public static final String Food_Id = "Food_Id";


    public static final String SUM_EX_Cal = "exc";
    public static final String SUM_Food_Cal = "fcal";
    public static final String SUM_pro = "fpro";
    public static final String SUM_fat = "ffat";
    public static final String SUM_car = "fcar";
    public static final String SUM_sugar = "fsug";
    public static final String SUM_sodium = "fsod";


    public FoodHistoryTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();


    }//Constructor

    //ตัวfoodDetail
    public HashMap<String, String> selectDetailByFoodHistory(int foodId) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        HashMap<String, String> foodHistory = new HashMap<String, String>();
        String query = "SELECT * FROM " + Food_History + " WHERE " + Food_Id + " = " + foodId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                foodHistory.put("History_Food_Id", cursor.getString(cursor.getColumnIndex(History_Food_Id)));
                foodHistory.put("History_Food_Date", cursor.getString(cursor.getColumnIndex(History_Food_Date)));
                foodHistory.put("Food_Amount", cursor.getString(cursor.getColumnIndex(Food_Amount)));
            } while (cursor.moveToNext());
        }

        return foodHistory;
    }

    //ตัวfoodDetail
    public HashMap<String, String> selectSUM(String datehis) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        HashMap<String, String> foodHistory = new HashMap<String, String>();
        String query = "select * from " +
                "(select sum(e.exercise_calories*eh.exercise_duration) 'exc' " +
                "from Exercise_History eh,Exercise e " +
                "where e.exercise_id = eh.exercise_id and History_exercise_date like '"+datehis+"' ) ex," +
                "(select sum(f.food_calories*fh.food_amount) 'fcal',sum(f.Food_protein) 'fpro',sum(f.Food_fat) 'ffat',sum(f.Food_Carbohydrate) 'fcar',sum(f.Food_sugars) 'fsug',sum(f.Food_sodium) 'fsod' " +
                "from Food_History fh,Food f " +
                "where fh.food_id = f.food_id and History_food_date like '"+datehis+"') fd";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                foodHistory.put(SUM_EX_Cal, cursor.getString(cursor.getColumnIndex(SUM_EX_Cal)));
                foodHistory.put(SUM_Food_Cal, cursor.getString(cursor.getColumnIndex(SUM_Food_Cal)));
                foodHistory.put(SUM_pro, cursor.getString(cursor.getColumnIndex(SUM_pro)));
                foodHistory.put(SUM_fat, cursor.getString(cursor.getColumnIndex(SUM_fat)));
                foodHistory.put(SUM_car, cursor.getString(cursor.getColumnIndex(SUM_car)));
                foodHistory.put(SUM_sugar, cursor.getString(cursor.getColumnIndex(SUM_sugar)));
                foodHistory.put(SUM_sodium, cursor.getString(cursor.getColumnIndex(SUM_sodium)));
            } while (cursor.moveToNext());
        }

        return foodHistory;
    }

}//MainClass
