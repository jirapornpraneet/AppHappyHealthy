package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }//Oncreate

    public void ClickSetBackHome(View view) {startActivity(new Intent(Setting.this,MainActivity.class));}//ClickBackHome

}//MainClass
