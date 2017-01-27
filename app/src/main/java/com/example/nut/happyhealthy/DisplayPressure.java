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
import android.widget.TextView;

public class DisplayPressure extends AppCompatActivity {

    SQLiteDatabase db;
    MyDatabase myDatabase;



    //Explicit
    private TextView TV_P_Date,TV_P_CostPressureDown,TV_P_CostPressureTop,TV_P_LevelDown,TV_P_LevelTop,TV_P_Heart,TV_P_level_heart;
    private String  str_P_Date,intCostPressureDown,intCostPressureTop,tv_P_LevelDown,tv_P_LevelTop,intCostHeart,tv_level_heart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pressure);

        myDatabase = new MyDatabase(this);

        // Bind Widget
        bindWidget();

        // Show View
        showView();


    }//Oncreate



    private void showView() {
        db = myDatabase.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + PressureTABLE.Pressure, null);

        if (cursor.moveToFirst()) {
            do {
                str_P_Date = cursor.getString(cursor.getColumnIndex(PressureTABLE.P_DateTime));
                intCostPressureDown = cursor.getString(cursor.getColumnIndex(PressureTABLE.P_CostPressureDown));
                intCostPressureTop = cursor.getString(cursor.getColumnIndex(PressureTABLE.P_CostPressureTop));
                intCostHeart = cursor.getString(cursor.getColumnIndex(PressureTABLE.P_HeartRate));
                //tv_P_LevelDown = findMyLevelPressureTop();
                tv_P_LevelTop = findMyLevelPressureTop();
                tv_level_heart = findMyLevelHeart(intCostHeart);
            } while (cursor.moveToNext());
        }

        cursor.close();
        TV_P_Date.setText(str_P_Date);
        TV_P_CostPressureDown.setText(intCostPressureDown);
        TV_P_CostPressureTop.setText(intCostPressureTop);
        TV_P_Heart.setText(intCostHeart);
//        TV_P_LevelDown.setText(tv_P_LevelDown);
        TV_P_LevelTop.setText(tv_P_LevelTop);
        TV_P_level_heart.setText(tv_level_heart);


    } // Show View


    private String findMyLevelPressureTop() {
        String[] resultStrings = getResources().getStringArray(R.array.my_pressure);
        String myResult = null;
        Integer IntCostPressureTop = Integer.parseInt(intCostPressureTop);
        Integer IntCostPressureDown = Integer.parseInt(intCostPressureDown);
        int CostTop,CostDown;
        Resources res = getResources();
        Resources res2 = getResources();

        int[] getDraw = {R.drawable.pre6,R.drawable.pre5,R.drawable.pre4,R.drawable.pre3,R.drawable.pre2,R.drawable.pre1};
        int[] getDraw2 = {R.drawable.textlevelpre1,R.drawable.textlevelpre2,R.drawable.textlevelpre3,R.drawable.textlevelpre4,R.drawable.textlevelpre5,R.drawable.textlevelpre6};


        ImageView imageView = (ImageView) findViewById(R.id.propretop);
        ImageView imageView2 = (ImageView) findViewById(R.id.leveltop);

        if (IntCostPressureTop >= 180 ) {
            CostTop = 0;
        } else if (IntCostPressureTop >= 160) {
            CostTop = 1;
        } else if (IntCostPressureTop >= 140) {
            CostTop = 2;
        } else if (IntCostPressureTop >= 130 ) {
            CostTop = 3;
        } else if (IntCostPressureTop >= 120 ) {
            CostTop = 4;
        } else if (IntCostPressureTop >= 90 ) {
            CostTop = 5;
        } else {
            CostTop = 0;
        }


        if (IntCostPressureDown >= 110) {
            CostDown = 0;
        } else if (IntCostPressureDown >= 100) {
            CostDown = 1;
        } else if (IntCostPressureDown >= 90 ) {
            CostDown = 2;
        } else if (IntCostPressureDown >= 85 ) {
            CostDown = 3;
        } else if (IntCostPressureDown >= 80 ) {
            CostDown = 4;
        } else if (IntCostPressureDown >= 60  ) {
            CostDown = 5;
        } else {
            CostDown = 0;
        }


        if (CostTop > CostDown ) {
            imageView.setImageDrawable(res.getDrawable(getDraw[CostDown]));
            imageView2.setImageDrawable(res.getDrawable(getDraw2[CostDown]));
            myResult = resultStrings[CostDown];
        } else {
            imageView.setImageDrawable(res.getDrawable(getDraw[CostTop]));
            imageView2.setImageDrawable(res.getDrawable(getDraw2[CostTop]));
            myResult = resultStrings[CostTop];
        }
        return myResult;

    }//findMyLevelPressureTop


    private String findMyLevelHeart(String intCostHeart) {
        String[] resultStrings = getResources().getStringArray(R.array.my_heartrate);
        String myResult = null;
        Integer IntHeart = Integer.parseInt(intCostHeart);

        Resources res = getResources();
        Resources res2 = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.proheart);
        ImageView imageView2 = (ImageView) findViewById(R.id.levelheart);

        if (IntHeart >=41) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.proheart1));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelheart1));
        } else if (IntHeart < 60) {
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.proheart1));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelheart1));
        } else if (IntHeart < 70) {
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.proheart2));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelheart2));
        } else if (IntHeart < 85) {
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.proheart3));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelheart3));
        } else if (IntHeart < 101 ) {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.proheart4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelheart4));
        } else {
            myResult = resultStrings[5];
            imageView.setImageDrawable(res.getDrawable(R.drawable.proheart5));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelheart5));
        }

        return myResult;

    }//findMyLevelHeart


    private void bindWidget() {

        TV_P_Date = (TextView) findViewById(R.id.tv_P_Date);
        TV_P_CostPressureDown = (TextView) findViewById(R.id.tv_P_CostPressureDown);
        TV_P_CostPressureTop = (TextView) findViewById(R.id.tv_P_CostPressureTop);
        TV_P_Heart = (TextView) findViewById(R.id.tv_P_Heart);
        //TV_P_LevelDown = (TextView) findViewById(R.id.tv_P_LevelDown);
        TV_P_LevelTop = (TextView) findViewById(R.id.tv_P_LevelTop);
        TV_P_level_heart = (TextView) findViewById(R.id.tv_P_level_heart);

    }//bindWidget

    public void ClickBackHomeDisPre(View view) {
        startActivity(new Intent(getApplicationContext(),Pressure.class));
    }//ClickAddKidney

    public void ClickHistoryPre(View view) {
        startActivity(new Intent(getApplicationContext(),History_Pressure.class));
    }//ClickHistoryKidney


}//MainClass