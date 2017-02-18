package com.jiraporn.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Add_Food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__food);
    }

    public void ClickAddFoodBack(View view) {
        startActivity(new Intent(Add_Food.this, MainActivity.class));
    }//ClickHistoryDiabetes
}
