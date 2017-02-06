package com.jiraporn.nut.happyhealthy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




    }//Oncreate

    public void ClickFood(View view) {
        startActivity(new Intent(getApplicationContext(),Food_Type_1.class));
    }//ClickFood

    public void ClickExe(View view) {startActivity(new Intent(getApplicationContext(),ExerciseType.class));}//Clickexe

    public void ClickDiabetes(View view) {
        startActivity(new Intent(getApplicationContext(),Diabetes.class));
    }//ClickDiabetes


    public void ClickKidney(View view) {
        startActivity(new Intent(getApplicationContext(),Kidney.class));
    }//ClickKidney

    public void ClickPressure(View view) {
        startActivity(new Intent(getApplicationContext(),Pressure.class));
    }//ClickPressure






}//MainClass