package com.jiraporn.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Add_Exe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__exe);
    }

    public void ClickAddExeBack(View view) {
        startActivity(new Intent(Add_Exe.this, MainActivity.class));
    }//ClickHistoryDiabetes
}
