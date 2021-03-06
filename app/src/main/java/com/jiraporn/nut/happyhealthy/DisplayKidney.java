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


        Resources res2 = getResources();
        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prokid);
        ImageView imageView2 = (ImageView) findViewById(R.id.levelkid);

        if (IntCostGFR >= 90) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid1));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelkid1));
        } else if ((IntCostGFR >=60 ) & (IntCostGFR < 90)) {
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid2));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelkid2));
        } else if ((IntCostGFR >= 30) & (IntCostGFR <60)){
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid3));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelkid3));
        } else if ((IntCostGFR >=15) & (IntCostGFR <30)) {
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelkid4));
        } else {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.prokid5));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textlevelkid5));
        }
        return myResult;
    }//findMyLevelDiseaseafter

    private void bindWidget() {

        TV_K_Date = (TextView) findViewById(R.id.tv_K_Date);
        TV_K_CostGFR = (TextView) findViewById(R.id.tv_K_CostGFR);
        TV_K_LevelCostGFR = (TextView) findViewById(R.id.tv_K_LevelCostGFR);

    }//bindWidget

    public void ClickBackHomeDisKidney(View view) {
        startActivity(new Intent(getApplicationContext(),Kidney.class));
    }//ClickAddKidney

    public void ClickHistoryKidney(View view) {
        startActivity(new Intent(getApplicationContext(),History_Kidney.class));
    }//ClickHistoryKidney


}//MainClass