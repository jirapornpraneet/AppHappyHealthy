package com.example.nut.happyhealthy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Exercise extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        //คลิกเลือกว่ากำหนดจะไปประเภทอาหารไหน
        ImageView imgB_1 = (ImageView) findViewById(R.id.exe1);
        ImageView imgB_2 = (ImageView) findViewById(R.id.exe2);
        ImageView imgB_3 = (ImageView) findViewById(R.id.exe3);
        ImageView imgB_4 = (ImageView) findViewById(R.id.exe4);
        ImageView imgB_5 = (ImageView) findViewById(R.id.exe5);
        ImageView imgB_6 = (ImageView) findViewById(R.id.exe6);



        imgB_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exercise.this,ExerciseType.class);
                intent.putExtra("type_exe",1);
                startActivity(intent);
            }
        });//imgB_1

        imgB_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exercise.this,ExerciseType.class);
                intent.putExtra("type_exe",2);
                startActivity(intent);
            }
        });//imgB_2

        imgB_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exercise.this,ExerciseType.class);
                intent.putExtra("type_exe",3);
                startActivity(intent);

            }
        });//imgB_3

        imgB_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exercise.this,ExerciseType.class);
                intent.putExtra("type_exe",4);
                startActivity(intent);

            }
        });//imgB_4

        imgB_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exercise.this,ExerciseType.class);
                intent.putExtra("type_exe",5);
                startActivity(intent);

            }
        });//imgB_5

        imgB_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exercise.this,ExerciseType.class);
                intent.putExtra("type_exe",6);
                startActivity(intent);

            }
        });//imgB_6


    }

    public void ClickExeBackHome(View view) {startActivity(new Intent(Exercise.this,MainActivity.class));}//ClickBackHome
}//MainClass
