package com.jiraporn.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Develop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_develop);
    }


    public void ClickDevelop(View view) {
        startActivity(new Intent(Develop.this, MainActivity.class));
    }//ClickHistoryDiabetes
}
