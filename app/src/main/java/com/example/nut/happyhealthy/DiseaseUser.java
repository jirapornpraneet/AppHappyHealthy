package com.example.nut.happyhealthy;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class DiseaseUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_user);
    }//Oncreate

    public void ClickDiabetes(View view) {
        startActivity(new Intent(DiseaseUser.this,Diabetes.class));
    }//ClickDiabetes

    public void ClickKidney(View view) {
        startActivity(new Intent(DiseaseUser.this,Kidney.class));
    }//ClickKidney

    public void ClickPressure(View view) {
        startActivity(new Intent(DiseaseUser.this,Pressure.class));
    }//ClickPressure

}//Maina Class
