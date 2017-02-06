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
    private String str_D_Date, intCostSugar, tv_D_Level, str_status;


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
                tv_D_Level = findMyLevelDiseaseBefore(intCostSugar);
                str_status = cursor.getString(cursor.getColumnIndex(DiabetesTABLE.D_Status));
            } while (cursor.moveToNext());
        }

        cursor.close();


        TV_D_Date.setText(str_D_Date);
        if (intCostSugar != null) {
            TV_D_CostSugar.setText(intCostSugar);
        }
        TV_D_Level.setText(tv_D_Level);


    } // Show View

    private String findMyLevelDiseaseBefore(String intCostSugar) {
        String[] resultStrings = getResources().getStringArray(R.array.my_disease);
        String myResult = null;
        Integer IntCostSugar = Integer.parseInt(intCostSugar);

        Resources res = getResources();
        Resources res2 = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prodia);
        ImageView imageView2 = (ImageView) findViewById(R.id.level1);

        if (IntCostSugar >= 300) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi1));
        } else if ((IntCostSugar >= 200) & (IntCostSugar < 300) ){
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia3));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi5));
        } else if ((IntCostSugar >= 110) & (IntCostSugar < 200)) {
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia2));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi4));
        } else if ((IntCostSugar >= 70) & (IntCostSugar < 100)){
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia1));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi3));
        } else {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi2));
        }


        return myResult;
    }//findMyLevelDiseasebefore

    /**private String findMyLevelDiseaseAfter(String intCostSugarAfter) {
        String[] resultStrings = getResources().getStringArray(R.array.my_disease);
        String myResult = null;
        Integer IntCostSugarAfter = Integer.parseInt(intCostSugarAfter);

        Resources res = getResources();
        Resources res2 = getResources();
        ImageView imageView = (ImageView) findViewById(R.id.prodia);
        ImageView imageView2 = (ImageView) findViewById(R.id.level1);

        if (IntCostSugarAfter >= 300) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi1));

        } else if ((IntCostSugarAfter >= 200) & (IntCostSugarAfter < 300 )) {
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia3));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi5));
        } else if ((IntCostSugarAfter >= 110) & (IntCostSugarAfter < 200)) {
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia2));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi4));
        } else if ((IntCostSugarAfter >= 70) & (IntCostSugarAfter < 110)){
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia1));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi3));
        } else {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.dia4));
            imageView2.setImageDrawable(res2.getDrawable(R.drawable.textleveldi2));

        }

        return myResult;

    }//findMyLevelDiseaseafter**/

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