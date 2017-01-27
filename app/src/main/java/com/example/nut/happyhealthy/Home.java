package com.example.nut.happyhealthy;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

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