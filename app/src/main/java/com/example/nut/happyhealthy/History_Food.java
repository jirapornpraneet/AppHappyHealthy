package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class History_Food extends AppCompatActivity {

    ListView listViewFoodHis;
    ListAdapter adapterFoodHis;
    FoodHistoryTABLE foodHistoryTABLE;
    ArrayList<HashMap<String, String>> foodHisList;
    //String Foodname, FoodUnit, FoodNetUnit,FoodDetail;
    int HisFoodId;
    Intent intent;
    String choose_D;
    SimpleDateFormat dfm,dfm_insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__food);


        intent = getIntent();
        choose_D = intent.getStringExtra("choose_Date");

        listViewFoodHis = (ListView) findViewById(R.id.listViewFoodHis);
        foodHistoryTABLE = new FoodHistoryTABLE(this);


        listViewFoodHis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HisFoodId = Integer.parseInt(foodHisList.get(i).get(FoodHistoryTABLE.History_Food_Id));

            }
        });

        foodHisList = foodHistoryTABLE.getFoodHisList(choose_D);
        adapterFoodHis = new SimpleAdapter(History_Food.this, foodHisList, R.layout.history_food, new String[]{FoodHistoryTABLE.Food_Name,FoodHistoryTABLE.Food_Calories,FoodHistoryTABLE.Food_Unit,FoodHistoryTABLE.Food_Detail}, new int[]{R.id.his_food_name, R.id.his_food_calories,R.id.his_food_unit,R.id.his_food_detail});
        listViewFoodHis.setAdapter(adapterFoodHis);
    }//OnCreate

    public void ClickBackHisFoodHome(View view) {
        startActivity(new Intent(History_Food.this,MainActivity.class));
    }//ClickBackHome

}//MainClass
