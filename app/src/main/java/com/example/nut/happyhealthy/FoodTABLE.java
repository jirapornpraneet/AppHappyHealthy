package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nut on 30/10/2559.
 */
public class FoodTABLE {
    //ตัวแปร
    private MyDatabase myDatabase;

    public static final String Food = "Food";
    public static final String Food_Id = "Food_Id";
    public static final String Food_Name = "Food_Name";
    public static final String Food_Calories = "Food_Calories";
    public static final String Food_Amount = "Food_Amount";
    public static final String Food_Unit = "Food_Unit";
    public static final String Food_Netweight = "Food_Netweight";
    public static final String Food_NetUnit = "Food_NetUnit";
    public static final String Food_Protein = "Food_Protein";
    public static final String Food_Fat = "Food_Fat";
    public static final String Food_Carbohydrate = "Food_Carbohydrate";
    public static final String Food_Sugars = "Food_Sugars";
    public static final String Food_Sodium = "Food_Sodium";
    public static final String Food_Detail = "Food_Detail";


    public FoodTABLE(Context context) {
        myDatabase = new MyDatabase(context);
    }//Constructor

    //Add New Value
    public long addNewValueToSQLite(String str_food_name, double dou_food_cal, int int_food_amount, String str_food_unit,
                                    double dou_food_netweight, String str_net_unit, double dou_protein, double dou_fat, double dou_carbohydrate, double dou_sugar, double dou_sodium, String str_food_detail) {

        SQLiteDatabase db = myDatabase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Food_Name, str_food_name);
        contentValues.put(Food_Calories, dou_food_cal);
        contentValues.put(Food_Amount, int_food_amount);
        contentValues.put(Food_Unit, str_food_unit);
        contentValues.put(Food_Netweight, dou_food_netweight);
        contentValues.put(Food_NetUnit, str_net_unit);
        contentValues.put(Food_Protein, dou_protein);
        contentValues.put(Food_Fat, dou_fat);
        contentValues.put(Food_Carbohydrate, dou_carbohydrate);
        contentValues.put(Food_Sugars, dou_sugar);
        contentValues.put(Food_Sodium, dou_sodium);
        contentValues.put(Food_Detail, str_food_detail);

        long food_id = db.insert(Food, null, contentValues);
        db.close();
        return food_id;
    }//Add New Value

    //เอใส่เพิ่มlistview
    public ArrayList<HashMap<String, String>> getFoodList() {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Food ;

        ArrayList<HashMap<String, String>> foodList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> food = new HashMap<String, String>();
                food.put("food_id", cursor.getString(cursor.getColumnIndex(Food_Id)));
                food.put("food_name", cursor.getString(cursor.getColumnIndex(Food_Name)));
                food.put("food_calories", cursor.getString(cursor.getColumnIndex(Food_Calories)));
                food.put("food_amount", cursor.getString(cursor.getColumnIndex(Food_Amount)));
                food.put("food_unit", cursor.getString(cursor.getColumnIndex(Food_Unit)));
                food.put("food_netweight", cursor.getString(cursor.getColumnIndex(Food_Netweight)));
                food.put("food_netunit", cursor.getString(cursor.getColumnIndex(Food_NetUnit)));
                food.put("food_protein", cursor.getString(cursor.getColumnIndex(Food_Protein)));
                food.put("food_fat", cursor.getString(cursor.getColumnIndex(Food_Fat)));
                food.put("food_carbohydrate", cursor.getString(cursor.getColumnIndex(Food_Carbohydrate)));
                food.put("food_sugars", cursor.getString(cursor.getColumnIndex(Food_Sugars)));
                food.put("food_sodium", cursor.getString(cursor.getColumnIndex(Food_Sodium)));
                food.put("food_detail", cursor.getString(cursor.getColumnIndex(Food_Detail)));
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return foodList;
    }



    //ตัวfoodDetail
    public HashMap<String, String> selectDetailByFoodId(int foodId) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        HashMap<String, String> food = new HashMap<String, String>();
        String query = "SELECT * FROM " + Food + " WHERE " + Food_Id + " = " + foodId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                food.put("food_id", cursor.getString(cursor.getColumnIndex(Food_Id)));
                food.put("food_name", cursor.getString(cursor.getColumnIndex(Food_Name)));
                food.put("food_calories", cursor.getString(cursor.getColumnIndex(Food_Calories)));
                food.put("food_amount", cursor.getString(cursor.getColumnIndex(Food_Amount)));
                food.put("food_unit", cursor.getString(cursor.getColumnIndex(Food_Unit)));
                food.put("food_netweight", cursor.getString(cursor.getColumnIndex(Food_Netweight)));
                food.put("food_netunit", cursor.getString(cursor.getColumnIndex(Food_NetUnit)));
                food.put("food_protein", cursor.getString(cursor.getColumnIndex(Food_Protein)));
                food.put("food_fat", cursor.getString(cursor.getColumnIndex(Food_Fat)));
                food.put("food_carbohydrate", cursor.getString(cursor.getColumnIndex(Food_Carbohydrate)));
                food.put("food_sugars", cursor.getString(cursor.getColumnIndex(Food_Sugars)));
                food.put("food_sodium", cursor.getString(cursor.getColumnIndex(Food_Sodium)));
                food.put("food_detail", cursor.getString(cursor.getColumnIndex(Food_Detail)));
            } while (cursor.moveToNext());
        }

        return food;
    }
}//MainClass




