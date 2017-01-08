package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayKidney extends AppCompatActivity {

    SQLiteDatabase db;
    MyDatabase myDatabase;

    //Explicit
    private TextView TV_K_Date,TV_K_CostGFR,TV_K_LevelCostGFR;
    private String  str_K_Date,intCostGFR,tv_K_LevelCostGFR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kidney);

        myDatabase = new MyDatabase(this);


        // Bind Widget
        bindWidget();

        // Show View
        showView();

    }//OnCreate

    private void showView() {
        db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + KidneyTABLE.Kidney, null);

        if (cursor.moveToFirst()) {
            do {
                str_K_Date = cursor.getString(cursor.getColumnIndex(KidneyTABLE.K_DateTime));
                intCostGFR = cursor.getString(cursor.getColumnIndex(KidneyTABLE.K_CostGFR));
                tv_K_LevelCostGFR = findMyLevelCostGFR(intCostGFR);
            } while (cursor.moveToNext());
        }

        cursor.close();
        TV_K_Date.setText(str_K_Date);
        TV_K_CostGFR.setText(intCostGFR);
        TV_K_LevelCostGFR.setText(tv_K_LevelCostGFR);


    } // Show View

    private String findMyLevelCostGFR(String intCostGFR) {
        String[] resultStrings = getResources().getStringArray(R.array.my_kidney);
        String myResult = null;
        Integer IntCostGFR = Integer.parseInt(intCostGFR);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prokid);

        if (IntCostGFR > 90) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid1));
        } else if (IntCostGFR > 60 ) {
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid2));
        } else if (IntCostGFR > 30) {
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid3));
        } else if (IntCostGFR > 15) {
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid4));
        } else {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid5));
        }
        return myResult;
    }//findMyLevelDiseaseafter

    private void bindWidget() {

        TV_K_Date = (TextView) findViewById(R.id.tv_K_Date);
        TV_K_CostGFR = (TextView) findViewById(R.id.tv_K_CostGFR);
        TV_K_LevelCostGFR = (TextView) findViewById(R.id.tv_K_LevelCostGFR);

    }//bindWidget

    public void ClickBackHomeDisKidney(View view) {
        startActivity(new Intent(DisplayKidney.this,Kidney.class));
    }//ClickAddKidney

    public void ClickHistoryKidney(View view) {
        startActivity(new Intent(DisplayKidney.this,History_Kidney.class));
    }//ClickHistoryKidney


}//MainClass