package com.example.nut.happyhealthy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IntroHealthy extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_healthy);
    }//OnCreate

    public void ClickIntroBackHome(View view) {startActivity(new Intent(IntroHealthy.this,MainActivity.class));}//ClickBackHome
}//MainClass
