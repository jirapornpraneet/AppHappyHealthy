package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExerciseType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_type);
    }//OnCreate

    public void ClickExeBackHome(View view) {startActivity(new Intent(ExerciseType.this,MainActivity.class));}//ClickBackHome

}//MainClass
