package com.jiraporn.nut.happyhealthy;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayDisease extends AppCompatActivity {


    SQLiteDatabase db;
    MyDatabase myDatabase;
    DiabetesTABLE diabetesTABLE;
    //**Explicit
    private TextView TV_D_Date, TV_D_CostSugar, TV_D_Level;
    private String str_D_Date, intCostSugar, tv_D_Level, str_status, str_people;


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
                intCostSugar = String.valueOf(cursor.getInt(cursor.getColumnIndex(DiabetesTABLE.D_CostSugar)));
                str_people = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.D_People));
                str_status = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.D_Status));
                findMyLevelDiseaseBefore(intCostSugar, str_people, str_status);
            } while (cursor.moveToNext());
        }

        cursor.close();

        TV_D_Date.setText(str_D_Date);
        if (intCostSugar != null) {
            TV_D_CostSugar.setText(intCostSugar);
        }
        TV_D_Level.setText(tv_D_Level);


    } // Show View

    private void findMyLevelDiseaseBefore(String intCostSugar, String statSugar, String foodTime) {
//        String[] resultStrings = getResources().getStringArray(R.array.my_disease);
        String[] sugar = getResources().getStringArray(R.array.my_people);
        String[] time = getResources().getStringArray(R.array.my_status);
        String myResult;
        Integer IntCostSugar = Integer.parseInt(intCostSugar);

        Resources res = getResources();
        Resources res2 = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prodia);
        ImageView imageView2 = (ImageView) findViewById(R.id.level1);

        if (statSugar.equals(sugar[0])) {
            if (foodTime.equals(time[0])) {
                if (IntCostSugar >= 126) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.lol));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelnormal));
                } else if ((IntCostSugar >=70) & (IntCostSugar < 126)){
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelnormal1));
                    imageView.setImageDrawable(res.getDrawable(R.drawable.lol));
                } else {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.lol));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelnormal2));
                }
            } else {
                if (IntCostSugar >= 200) {
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelnormal));
                    imageView.setImageDrawable(res.getDrawable(R.drawable.lol));
                } else if ((IntCostSugar >=70) & (IntCostSugar < 200)){
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelnormal1));
                    imageView.setImageDrawable(res.getDrawable(R.drawable.lol));
                } else {
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelnormal2));
                    imageView.setImageDrawable(res.getDrawable(R.drawable.lol));
                }
            }
        } else {
            if (foodTime.equals(time[0])) {
                if (IntCostSugar >= 130) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi1));
                } else if ((IntCostSugar >= 100) & (IntCostSugar < 130)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia3));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi5));
                } else if ((IntCostSugar >= 90) & (IntCostSugar < 100)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia2));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi4));
                } else if ((IntCostSugar >= 70) & (IntCostSugar < 90)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia1));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi3));
                } else {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi2));
                }
            } else {
                if (IntCostSugar >= 180) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi1));
                } else if ((IntCostSugar >= 150) & (IntCostSugar < 180)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia3));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi5));
                } else if ((IntCostSugar >= 110) & (IntCostSugar < 150)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia2));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi4));
                } else if ((IntCostSugar >= 70) & (IntCostSugar < 110)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia1));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi3));
                } else {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
                    imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi2));
                }
            }
        }
    }//findMyLevelDiseasebefore






    private void bindWidget() {

        TV_D_Date = (TextView) findViewById(R.id.tv_D_Date);
        TV_D_Level = (TextView) findViewById(R.id.tv_D_Level);
        TV_D_CostSugar = (TextView) findViewById(R.id.tv_D_CostSugar);


    }//bindWidget


    public void ClickBackHomeDisDiabetes(View view) {
        startActivity(new Intent(getApplicationContext(), Diabetes.class));
    }//ClickAddDiabetes


    public void ClickHistoryDiabetes(View view) {
        startActivity(new Intent(getApplicationContext(), History_Diabetes.class));
    }//ClickHistoryDiabetes


}//MainClass