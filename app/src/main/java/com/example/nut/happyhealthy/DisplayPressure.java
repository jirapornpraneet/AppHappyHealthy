package com.example.nut.happyhealthy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayPressure extends AppCompatActivity {

    //Explicit
    private TextView TV_P_Date,TV_P_Time,TV_P_CostPressureDown,TV_P_CostPressureTop,TV_P_LevelDown,TV_P_LevelTop;
    private String  str_P_Date,str_P_Time,intCostPressureDown,intCostPressureTop,tv_P_LevelDown,tv_P_LevelTop ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pressure);

        // Bind Widget
        bindWidget();

        // Show View
        showView();


    }//Oncreate



    private void showView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + PressureTABLE.Pressure, null);

        if (cursor.moveToFirst()) {
            do {
                str_P_Date = cursor.getString(cursor.getColumnIndex(PressureTABLE.Pressure_Date));
                str_P_Time = cursor.getString(cursor.getColumnIndex(PressureTABLE.Pressure_Time));
                intCostPressureDown = cursor.getString(cursor.getColumnIndex(PressureTABLE.Pressure_CostPressureDown));
                intCostPressureTop = cursor.getString(cursor.getColumnIndex(PressureTABLE.Pressure_CostPressureTop));
                tv_P_LevelDown = findMyLevelPressureDown(intCostPressureDown);
                tv_P_LevelTop = findMyLevelPressureTop(intCostPressureTop);
            } while (cursor.moveToNext());
        }

        cursor.close();
        TV_P_Date.setText(str_P_Date);
        TV_P_Time.setText(str_P_Time);
        TV_P_CostPressureDown.setText(intCostPressureDown);
        TV_P_CostPressureTop.setText(intCostPressureTop);
        TV_P_LevelDown.setText(tv_P_LevelDown);
        TV_P_LevelTop.setText(tv_P_LevelTop);


    } // Show View



    private String findMyLevelPressureDown(String intCostPressureDown) {
        String[] resultStrings = getResources().getStringArray(R.array.my_pressure);
        String myResult = null;
        Integer IntCostPressureDown = Integer.parseInt(intCostPressureDown);

        if (IntCostPressureDown < 60) {
            myResult = resultStrings[0];
        } else if (IntCostPressureDown < 88) {
            myResult = resultStrings[1];
        } else if (IntCostPressureDown < 90) {
            myResult = resultStrings[2];
        } else if (IntCostPressureDown < 100) {
            myResult = resultStrings[3];
        } else if (IntCostPressureDown < 110) {
            myResult = resultStrings[4];
        } else {
            myResult = resultStrings[5];
        }

        return myResult;

    }//findMyLevelPressureDown

    private String findMyLevelPressureTop(String intCostPressureTop) {
        String[] resultStrings = getResources().getStringArray(R.array.my_pressure);
        String myResult = null;
        Integer IntCostPressureTop = Integer.parseInt(intCostPressureTop);

        if (IntCostPressureTop < 100) {
            myResult = resultStrings[0];
        } else if (IntCostPressureTop < 130) {
            myResult = resultStrings[1];
        } else if (IntCostPressureTop < 140) {
            myResult = resultStrings[2];
        } else if (IntCostPressureTop < 160) {
            myResult = resultStrings[3];
        } else if (IntCostPressureTop < 180) {
            myResult = resultStrings[4];
        } else {
            myResult = resultStrings[5];
        }

        return myResult;
    }//findMyLevelPressureTop


    private void bindWidget() {

        TV_P_Date = (TextView) findViewById(R.id.tv_P_Date);
        TV_P_Time = (TextView) findViewById(R.id.tv_P_Time);
        TV_P_CostPressureDown = (TextView) findViewById(R.id.tv_P_CostPressureDown);
        TV_P_CostPressureTop = (TextView) findViewById(R.id.tv_P_CostPressureTop);
        TV_P_LevelDown = (TextView) findViewById(R.id.tv_P_LevelDown);
        TV_P_LevelTop = (TextView) findViewById(R.id.tv_P_LevelTop);

    }//bindWidget
}//MainClass