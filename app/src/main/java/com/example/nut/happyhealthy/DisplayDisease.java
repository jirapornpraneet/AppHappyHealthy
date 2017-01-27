package com.example.nut.happyhealthy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DisplayDisease extends AppCompatActivity {


    SQLiteDatabase db;
    MyDatabase myDatabase;
    DiabetesTABLE diabetesTABLE;
    //**Explicit
    private TextView TV_D_Date,TV_D_CostSugarBefore,TV_D_CostSugarAfter,TV_D_LevelBefore,TV_D_LevelAfter;
    private String  str_D_Date,intCostSugarBefore,intCostSugarAfter,tv_D_LevelBefore,tv_D_LevelAfter ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_disease);

        myDatabase = new MyDatabase(this);

        // Bind Widget
        bindWidget();

        // Show View
        showView();

        diabetesTABLE = new DiabetesTABLE(this);







    }//OnCreate


    private void showView() {
        db = myDatabase.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DiabetesTABLE.Diabetes, null);

        if (cursor.moveToFirst()) {
            do {
                str_D_Date = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.D_DateTime));
                intCostSugarBefore = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.D_CostSugarBefore));
                intCostSugarAfter = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.D_CostSugarAfter));
                tv_D_LevelBefore = findMyLevelDiseaseBefore(intCostSugarBefore);
                tv_D_LevelAfter = findMyLevelDiseaseAfter(intCostSugarAfter);
            } while (cursor.moveToNext());
        }

        cursor.close();
        TV_D_Date.setText(str_D_Date);
        TV_D_CostSugarBefore.setText(intCostSugarBefore);
        TV_D_CostSugarAfter.setText(intCostSugarAfter);
        TV_D_LevelBefore.setText(tv_D_LevelBefore);
        TV_D_LevelAfter.setText(tv_D_LevelAfter);


    } // Show View

    private String findMyLevelDiseaseBefore(String intCostSugarBefore) {
        String[] resultStrings = getResources().getStringArray(R.array.my_disease);
        String myResult = null;
        Integer IntCostSugarBefore = Integer.parseInt(intCostSugarBefore);

        Resources res = getResources();
        Resources res2 = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prodia);
        ImageView imageView2 = (ImageView) findViewById(R.id.level1);

        if (IntCostSugarBefore >=300 ) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi1));
        } else if (IntCostSugarBefore >= 200) {
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia3));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi5));
        } else if (IntCostSugarBefore >= 100 ) {
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia2));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi4));
        } else if (IntCostSugarBefore <= 70) {
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi2));
        } else {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia1));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi3));
        }


        return myResult;
    }//findMyLevelDiseasebefore

    private String findMyLevelDiseaseAfter(String intCostSugarAfter) {
        String[] resultStrings = getResources().getStringArray(R.array.my_disease);
        String myResult = null;
        Integer IntCostSugarAfter = Integer.parseInt(intCostSugarAfter);

        Resources res = getResources();
        Resources res2 = getResources();
        ImageView imageView = (ImageView) findViewById(R.id.prodiaa);
        ImageView imageView2 = (ImageView) findViewById(R.id.level2);


        if (IntCostSugarAfter >= 300 ) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi1));
        } else if (IntCostSugarAfter >= 200) {
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia3));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi5));
        } else if (IntCostSugarAfter >= 110 ) {
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia2));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi4));
        } else if (IntCostSugarAfter <= 70) {
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi2));
        } else {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia1));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi3));
        }

        return myResult;


    }//findMyLevelDiseaseafter

    private void bindWidget() {

        TV_D_Date = (TextView) findViewById(R.id.tv_D_Date);
        TV_D_CostSugarBefore = (TextView) findViewById(R.id.tv_D_CostSugarBefore);
        TV_D_CostSugarAfter = (TextView) findViewById(R.id.tv_D_CostSugarAfter);
        TV_D_LevelBefore = (TextView) findViewById(R.id.tv_D_LevelBefore);
        TV_D_LevelAfter = (TextView) findViewById(R.id.tv_D_LevelAfter);

    }//bindWidget


    public void ClickBackHomeDisDiabetes(View view) {
        startActivity(new Intent(getApplicationContext(),Diabetes.class));
    }//ClickAddDiabetes


    public void ClickHistoryDiabetes(View view) {
        startActivity(new Intent(getApplicationContext(),History_Diabetes.class));
    }//ClickHistoryDiabetes




}//MainClass