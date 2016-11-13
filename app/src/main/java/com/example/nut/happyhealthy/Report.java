package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }//OnCreate
    public void ClickReportBackHome(View view) {startActivity(new Intent(Report.this,MainActivity.class));}//ClickBackHome

}//MainClass
