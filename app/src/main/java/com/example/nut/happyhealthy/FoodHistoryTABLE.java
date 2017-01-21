package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public static final String History_Food_Date = "History_Food_Date";
    public static final String Food_Amount = "Food_Amount";
    public static final String User_Id = "User_Id";

    public static final String Food = "Food";
    public static final String Food_Id = "Food_Id";
    public static final String Food_Unit = "Food_Unit";
    public static final String Food_Name = "Food_Name";
    public static final String Food_Detail = "Food_Detail";
    public static final String Food_Calories = "Food_Calories";
    public static final String Food_Protein = "Food_Protein";
    public static final String Food_Fat = "Food_Fat";
    public static final String Food_Carbohydrate = "Food_Carbohydrate";
    public static final String Food_Sugars = "Food_Sugars";
    public static final String Food_Sodium = "Food_Sodium";




    public static final String Exercise_History = "Exercise_History";
    public static final String History_Exercise_Id = "History_Exercise_Id";
    public static final String History_Exercise_Date = "History_Exercise_Date";
    public static final String Exercise_TotalDuration = "Exercise_TotalDuration";

    public static final String Exercise = "Exercise";
    public static final String Exercise_Id = "Exercise_Id";
    public static final String Exercise_Name = "Exercise_Name";
    public static final String Exercise_Calories = "Exercise_Calories";
    public static final String Exercise_Duration = "Exercise_Duration";
    public static final String Exercise_Disease = "Exercise_Disease";
    public static final String Exercise_Detail = "Exercise_Detail";
    public static final String Exercise_Description = "Exercise_Description";


    public static final String SUM_EX_Cal = "exc";
    public static final String SUM_Food_Cal = "fcal";
    public static final String SUM_pro = "fpro";
    public static final String SUM_fat = "ffat";
    public static final String SUM_car = "fcar";
    public static final String SUM_sugar = "fsug";
    public static final String SUM_sodium = "fsod";
    public static final String Total_Cal = "TotalCal";

    String HisDate;
    int FoodId;
    Double FoodAmount;

    public FoodHistoryTABLE(Context context) {
        myDatabase = new MyDatabase(context);
        writeSQLite = myDatabase.getWritableDatabase();
        readSQLite = myDatabase.getReadableDatabase();
    }//Constructor

    public long addFoodHis(FoodHistoryTABLE data) {

        SQLiteDatabase db = myDatabase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(History_Food_Date, data.HisDate);
        contentValues.put(Food_Id, data.FoodId);
        contentValues.put(Food_Amount, data.FoodAmount);

        long food_id = db.insert(Food_History, null, contentValues);
        db.close();
        return food_id;
    }//Add New Value

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
        HashMap<String, String> foodHistory = new HashMap<>();
        String query = "select * from " +
                "(select sum(e." + Exercise_Calories + "*eh." + Exercise_TotalDuration + ") 'exc' " +
                "from " + Exercise_History + " eh," + Exercise + " e " +
                "where e." + Exercise_Id + " = eh." + Exercise_Id + " and " + History_Exercise_Date + " like '" + datehis + "' ) eh," +
                "(select sum(f." + Food_Calories + "*fh." + Food_Amount + ") 'fcal',sum(f." + Food_Protein + ") 'fpro',sum(f." + Food_Fat + ") 'ffat',sum(f." + Food_Carbohydrate + ") 'fcar',sum(f." + Food_Sugars + ") 'fsug',sum(f." + Food_Sodium + ") 'fsod' " +
                "from " + Food_History + " fh," + Food + " f " +
                "where fh." + Food_Id + " = f." + Food_Id + " and " + History_Food_Date + " like '" + datehis + "') fd";

        Log.d("selectSum", query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                foodHistory.put(Total_Cal, String.valueOf((cursor.getDouble(cursor.getColumnIndex(SUM_Food_Cal))
                        - cursor.getDouble(cursor.getColumnIndex(SUM_EX_Cal)))));
//                foodHistory.put(UserTABLE.User_BMR,cursor.getString(cursor.getColumnIndex(UserTABLE.User_BMR)));
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

    //แสดงประวัติอาหาร
    public ArrayList<HashMap<String, String>> getFoodHisList(String chooseDate) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "select * from " +
                "(select * from " + Food_History + " where " + History_Food_Date + " LIKE '" + chooseDate + "') fh, " +
                "" + Food + " f " +
                "where fh." + Food_Id + " = f." + Food_Id + "";

        ArrayList<HashMap<String, String>> foodHisList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> foodHis = new HashMap<String, String>();
                foodHis.put(History_Food_Id, cursor.getString(cursor.getColumnIndex(History_Food_Id)));
                foodHis.put(History_Food_Date, cursor.getString(cursor.getColumnIndex(History_Food_Date)));
                foodHis.put(Food_Amount, cursor.getString(cursor.getColumnIndex(Food_Amount)));
                foodHis.put(Food_Name, cursor.getString(cursor.getColumnIndex(Food_Name)));
                foodHis.put(Food_Unit, cursor.getString(cursor.getColumnIndex(Food_Unit)));
                foodHis.put(Food_Calories, cursor.getString(cursor.getColumnIndex(Food_Calories)));
                foodHis.put(Food_Detail ,cursor.getString(cursor.getColumnIndex(Food_Detail)));
                foodHisList.add(foodHis);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return foodHisList;


    }

    //แสดงประวัติออกกำลังกาย
    public ArrayList<HashMap<String, String>> getExeHisList(String chooseDate) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "select * from " +
                "(select * from " + Exercise_History + " where " + History_Exercise_Date + " LIKE '" + chooseDate + "') eh, " +
                "" + Exercise + " e " +
                "where eh." + Exercise_Id + " = e." + Exercise_Id + "";

        ArrayList<HashMap<String, String>> exeHisList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> exeHis = new HashMap<String, String>();
                exeHis.put(History_Exercise_Id, cursor.getString(cursor.getColumnIndex(History_Exercise_Id)));
                exeHis.put(History_Exercise_Date, cursor.getString(cursor.getColumnIndex(History_Exercise_Date)));
                exeHis.put(Exercise_Name, cursor.getString(cursor.getColumnIndex(Exercise_Name)));
                exeHis.put(Exercise_Calories, cursor.getString(cursor.getColumnIndex(Exercise_Calories)));
                exeHis.put(Exercise_Duration, cursor.getString(cursor.getColumnIndex(Exercise_Duration)));
                exeHis.put(Exercise_Disease, cursor.getString(cursor.getColumnIndex(Exercise_Disease)));
                exeHisList.add(exeHis);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return exeHisList;


    }

    public void delete(int HisFoodId) {

        SQLiteDatabase db = myDatabase.getWritableDatabase();

        db.delete(FoodHistoryTABLE.Food_History, FoodHistoryTABLE.History_Food_Id + "=?", new String[]{String.valueOf(HisFoodId)});
        db.close();
    }

}//MainClass
