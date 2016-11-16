package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Food_Type_1 extends AppCompatActivity {

    ListView listViewFood1;
    ListAdapter adapterFood1;
    FoodTABLE foodTABLE;
    ArrayList<HashMap<String, String>> foodList;
    String Foodname, FoodUnit, FoodNetUnit;
    double FoodCalories, FoodNetweight, FoodProtein, FoodFat, FoodCarbohydrate, FoodSugars, FoodSodium;
    int FoodAmount;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__type_1);

        Intent intent = getIntent();
        //กำหนดว่าจะไปหน้าไหนของประเภทอาหาร
        int type = intent.getIntExtra("type_food", 0);

        listViewFood1 = (ListView) findViewById(R.id.listViewFood1);

        foodTABLE = new FoodTABLE(this);

        listViewFood1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Foodname = foodList.get(i).get("food_name").toString();
                FoodCalories = Double.parseDouble(foodList.get(i).get("food_calories"));
                FoodAmount = Integer.parseInt(foodList.get(i).get("food_amount"));
                FoodUnit = foodList.get(i).get("food_unit").toString();
                FoodNetweight = Double.parseDouble(foodList.get(i).get("food_netweight"));
                FoodNetUnit = foodList.get(i).get("food_netunit").toString();
                FoodProtein = Double.parseDouble(foodList.get(i).get("food_protein"));
                FoodFat = Double.parseDouble(foodList.get(i).get("food_fat"));
                FoodCarbohydrate = Double.parseDouble(foodList.get(i).get("food_carbohydrate"));
                FoodSugars = Double.parseDouble(foodList.get(i).get("food_sugars"));
                FoodSodium = Double.parseDouble(foodList.get(i).get("food_sodium"));

            }
        });

        foodList = foodTABLE.getFoodList(type);
        adapterFood1 = new SimpleAdapter(Food_Type_1.this, foodList, R.layout.food_1, new String[]{"food_name", "food_calories"}, new int[]{R.id.food_name, R.id.food_calories});
        //new String[]{TB_5_PARTS.Part_Name, "countKilo", "countDate"}, new int[]{R.id.part_name, R.id.textView10, R.id.textView12});
        listViewFood1.setAdapter(adapterFood1); //เป็นตัวที่เอาออกมาโชว์หน้าในlist

    }//onCreate
}//MainClass

