package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayKidney extends AppCompatActivity {

    //Explicit
    private TextView TV_K_Date,TV_K_Time,TV_K_CostGFR,TV_K_LevelCostGFR;
    private String  str_K_Date,str_K_Time,intCostGFR,tv_K_LevelCostGFR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_kidney);


        // Bind Widget
        bindWidget();

        // Show View
        showView();

    }//OnCreate

    private void showView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + KidneyTABLE.Kidney, null);

        if (cursor.moveToFirst()) {
            do {
                str_K_Date = cursor.getString(cursor.getColumnIndex(KidneyTABLE.Kidney_Date));
                str_K_Time = cursor.getString(cursor.getColumnIndex(KidneyTABLE.Kidney_Time));
                intCostGFR = cursor.getString(cursor.getColumnIndex(KidneyTABLE.Kidney_CostGFR));
                tv_K_LevelCostGFR = findMyLevelCostGFR(intCostGFR);
            } while (cursor.moveToNext());
        }

        cursor.close();
        TV_K_Date.setText(str_K_Date);
        TV_K_Time.setText(str_K_Time);
        TV_K_CostGFR.setText(intCostGFR);
        TV_K_LevelCostGFR.setText(tv_K_LevelCostGFR);


    } // Show View

    private String findMyLevelCostGFR(String intCostGFR) {
        String[] resultStrings = getResources().getStringArray(R.array.my_kidney);
        String myResult = null;
        Integer IntCostGFR = Integer.parseInt(intCostGFR);

        if (IntCostGFR > 90) {
            myResult = resultStrings[0];
        } else if (IntCostGFR > 60 ) {
            myResult = resultStrings[1];
       } else if (IntCostGFR > 30) {
            myResult = resultStrings[2];
        } else if (IntCostGFR > 15) {
            myResult = resultStrings[3];
        } else {
            myResult = resultStrings[4];
        }
                return myResult;
    }//findMyLevelDiseaseafter

    private void bindWidget() {

        TV_K_Date = (TextView) findViewById(R.id.tv_K_Date);
        TV_K_Time = (TextView) findViewById(R.id.tv_K_Time);
        TV_K_CostGFR = (TextView) findViewById(R.id.tv_K_CostGFR);
        TV_K_LevelCostGFR = (TextView) findViewById(R.id.tv_K_LevelCostGFR);

    }//bindWidget
}//MainClass