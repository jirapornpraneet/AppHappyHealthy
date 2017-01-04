package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class History_Exe extends AppCompatActivity {

    ListView listViewExeHis;
    ListAdapter adapterExeHis;
    FoodHistoryTABLE foodHistoryTABLE;
    ArrayList<HashMap<String, String>> exeHisList;
    //String Foodname, FoodUnit, FoodNetUnit,FoodDetail;
    int HisExeId;
    Intent intent;
    String choose_D;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__exe);

        intent = getIntent();
        choose_D = intent.getStringExtra("choose_Date");

        listViewExeHis = (ListView) findViewById(R.id.listViewExeHis);
        foodHistoryTABLE = new FoodHistoryTABLE(this);


        listViewExeHis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HisExeId = Integer.parseInt(exeHisList.get(i).get(FoodHistoryTABLE.History_Exercise_Id));

            }
        });

        exeHisList = foodHistoryTABLE.getExeHisList(choose_D);
        adapterExeHis = new SimpleAdapter(History_Exe.this, exeHisList, R.layout.history_exe, new String[]{FoodHistoryTABLE.Exercise_Name,FoodHistoryTABLE.Exercise_Calories,FoodHistoryTABLE.Exercise_Disease}, new int[]{R.id.his_exercise_name, R.id.his_exercise_calories,R.id.his_exercise_disease});
        listViewExeHis.setAdapter(adapterExeHis);
    }//OnCreate

    public void ClickBackHisExeHome(View view) {
        startActivity(new Intent(History_Exe.this,MainActivity.class));
    }//ClickBackHome

}//MainClass
