package com.example.nut.happyhealthy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DisplayDisease extends AppCompatActivity {


    //**Explicit
    private TextView TV_D_Date,TV_D_Time,TV_D_CostSugarBefore,TV_D_CostSugarAfter,TV_D_LevelBefore,TV_D_LevelAfter;
    private String  str_D_Date,str_D_Time,intCostSugarBefore,intCostSugarAfter,tv_D_LevelBefore,tv_D_LevelAfter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_disease);

        // Bind Widget
        bindWidget();

        // Show View
        showView();

    }//OnCreate


    private void showView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DiabetesTABLE.Diabetes, null);

        if (cursor.moveToFirst()) {
            do {
                str_D_Date = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.Diabetes_Date));
                str_D_Time = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.Diabetes_Time));
                intCostSugarBefore = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.Diabetes_CostSugarBefore));
                intCostSugarAfter = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.Diabetes_CostSugarAfter));
                tv_D_LevelBefore = findMyLevelDiseaseBefore(intCostSugarBefore);
                tv_D_LevelAfter = findMyLevelDiseaseAfter(intCostSugarAfter);
            } while (cursor.moveToNext());
        }

        cursor.close();
        TV_D_Date.setText(str_D_Date);
        TV_D_Time.setText(str_D_Time);
        TV_D_CostSugarBefore.setText(intCostSugarBefore);
        TV_D_CostSugarAfter.setText(intCostSugarAfter);
        TV_D_LevelBefore.setText(tv_D_LevelBefore);
        TV_D_LevelAfter.setText(tv_D_LevelAfter);


    } // Show View

    private String findMyLevelDiseaseBefore(String intCostSugarBefore) {
        String[] resultStrings = getResources().getStringArray(R.array.my_disease);
        String myResult = null;
        Integer IntCostSugarBefore = Integer.parseInt(intCostSugarBefore);

        if (IntCostSugarBefore < 69) {
            myResult = resultStrings[0];
        } else if (IntCostSugarBefore < 100) {
            myResult = resultStrings[1];
        } else if (IntCostSugarBefore < 126) {
            myResult = resultStrings[2];
        } else {
            myResult = resultStrings[3];
        }

        return myResult;
    }//findMyLevelDiseasebefore

    private String findMyLevelDiseaseAfter(String intCostSugarAfter) {
        String[] resultStrings = getResources().getStringArray(R.array.my_disease);
        String myResult = null;
        Integer IntCostSugarAfter = Integer.parseInt(intCostSugarAfter);

        if (IntCostSugarAfter < 139) {
            myResult = resultStrings[0];
        } else if (IntCostSugarAfter < 199) {
            myResult = resultStrings[1];
        } else if (IntCostSugarAfter < 200) {
            myResult = resultStrings[2];
        } else {
            myResult = resultStrings[3];
        }

        return myResult;
    }//findMyLevelDiseaseafter

    private void bindWidget() {

        TV_D_Date = (TextView) findViewById(R.id.tv_D_Date);
        TV_D_Time = (TextView) findViewById(R.id.tv_D_Time);
        TV_D_CostSugarBefore = (TextView) findViewById(R.id.tv_D_CostSugarBefore);
        TV_D_CostSugarAfter = (TextView) findViewById(R.id.tv_D_CostSugarAfter);
        TV_D_LevelBefore = (TextView) findViewById(R.id.tv_D_LevelBefore);
        TV_D_LevelAfter = (TextView) findViewById(R.id.tv_D_LevelAfter);

    }//bindWidget


    public void ClickHistoryDiabetes(View view) {
        startActivity(new Intent(DisplayDisease.this,History_Diabetes.class));
    }//ClickHistoryDiabetes




}//MainClass