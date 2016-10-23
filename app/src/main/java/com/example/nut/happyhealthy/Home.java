package com.example.nut.happyhealthy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }//Oncreate

    public void ClickFood(View view) {
        startActivity(new Intent(Home.this,Food.class));
    }

    public void ClickExe(View view) {
        startActivity(new Intent(Home.this,Exercise.class));
    }


}//MainClass
