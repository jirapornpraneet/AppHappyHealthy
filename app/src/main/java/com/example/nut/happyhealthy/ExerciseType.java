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
    String Exename;
    double ExeCalories, ExeDuration;
    int ExeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_type);



        listViewExeType = (ListView) findViewById(R.id.listViewExeType);

        exerciseTABLE = new ExerciseTABLE(this);


       listViewExeType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               ExeId = Integer.parseInt(exeList.get(i).get("exercise_id"));
//               Exename = exeList.get(i).get("exercise_name").toString();
//               ExeCalories = Double.parseDouble(exeList.get(i).get("exercise_calories"));
//               ExeDuration = Double.parseDouble(exeList.get(i).get("exercise_duration"));
               //ExeDuration = Double.parseDouble(exeList.get(i).get("exercise_duration"));


               //ส่งค่าไปอีกหน้าหนึ่ง putExtra
               Intent intent2;
               intent2 = new Intent(getApplicationContext(), Exercise_Detail.class);
               intent2.putExtra("exercise_id", ExeId);
               intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);


           }
       });

        exeList = exerciseTABLE.getExeList();
        adapterExeType = new SimpleAdapter(ExerciseType.this,exeList,R.layout.exercise_type,new String[]{"exercise_name" ,"exercise_calories","exercise_disease" }, new int[]{R.id.exercise_name, R.id.exercise_calories,R.id.exercise_disease});
        listViewExeType.setAdapter(adapterExeType);

    }//OnCreate

    public void ClickBackExeHome(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }//ClickBackHome



}//MainClass
