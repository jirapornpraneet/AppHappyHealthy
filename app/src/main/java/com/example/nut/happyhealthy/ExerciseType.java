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

public class ExerciseType extends AppCompatActivity {

    ListView listViewExeType;
    ListAdapter adapterExeType;
    ExerciseTABLE exerciseTABLE;
    ArrayList<HashMap<String, String>> exeList;
    String Exename, ExeDuration;
    double ExeCalories;
    int ExeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_type);

        Intent intent = getIntent();
        //กำหนดว่าจะไปหน้าไหนของประเภทอาหาร
        int type = intent.getIntExtra("type_exe", 0);



        listViewExeType = (ListView) findViewById(R.id.listViewExeType);

        exerciseTABLE = new ExerciseTABLE(this);


       listViewExeType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               ExeId = Integer.parseInt(exeList.get(i).get("exercise_id"));
               Exename = exeList.get(i).get("exercise_name").toString();
               ExeCalories = Double.parseDouble(exeList.get(i).get("exercise_calories"));
               ExeDuration = exeList.get(i).get("exercise_duration").toString();

               //ส่งค่าไปอีกหน้าหนึ่ง putExtra
               Intent intent2;
               intent2 = new Intent(ExerciseType.this, Exercise_Detail.class);
               intent2.putExtra("exercise_id", ExeId);
               startActivity(intent2);



           }
       });

        exeList = exerciseTABLE.getExeList(type);
        adapterExeType = new SimpleAdapter(ExerciseType.this,exeList,R.layout.exercise_type,new String[]{"exercise_name" ,"exercise_calories" ,"exercise_duration"}, new int[]{R.id.exercise_name, R.id.exercise_calories,R.id.exercise_duration});
       listViewExeType.setAdapter(adapterExeType);

    }//OnCreate



}//MainClass
