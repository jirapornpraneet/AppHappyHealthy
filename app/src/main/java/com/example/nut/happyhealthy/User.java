package com.example.nut.happyhealthy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class User extends AppCompatActivity {

    DatePickerDialog mDatePicker;
    Calendar mCalendar;
    Calendar c;
    SimpleDateFormat dfm, dfm_insert;
    String sysDate;
    String textDate;


    //**Explicit
    DiabetesTABLE diabetesTABLE;
    HashMap<String, String> dateSelectDiabetes;
    TextView chooseDate, d_date, d_CostSugarBefore, d_CostSugarAfter, d_LevelCostBefore, d_LevelCostAfter,
            k_date, k_cost, k_level, p_date, p_costtop, p_costdown, p_heart, p_leveltop, p_leveldown, p_levelheart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        dfm = new SimpleDateFormat("dd-MMMM-yyyy");
        dfm_insert = new SimpleDateFormat("dd/MM/yyyy");

        c = Calendar.getInstance();
        sysDate = dfm_insert.format(c.getTime()) + "%";
        textDate = dfm.format(c.getTime());

        mCalendar = Calendar.getInstance();

        diabetesTABLE = new DiabetesTABLE(this);

        chooseDate = (TextView) findViewById(R.id.chooseDate);

        d_date = (TextView) findViewById(R.id.D_date);
        d_CostSugarBefore = (TextView) findViewById(R.id.CostSugarBefore);
        d_CostSugarAfter = (TextView) findViewById(R.id.CostSugarAfter);
        //d_LevelCostBefore = (TextView) findViewById(R.id.LevelCostBefore);
        //d_LevelCostAfter = (TextView) findViewById(R.id.LevelCostAfter);
        k_date = (TextView) findViewById(R.id.K_date);
        k_cost = (TextView) findViewById(R.id.CostGFR);
        //k_level = (TextView) findViewById(R.id.LevelCostGFR);
        p_date = (TextView) findViewById(R.id.P_date);
        p_costtop = (TextView) findViewById(R.id.Cost_PreTop);
        p_costdown = (TextView) findViewById(R.id.Cost_PreDown);
        p_heart = (TextView) findViewById(R.id.Cost_Heart);
        p_leveltop = (TextView) findViewById(R.id.LevelPreTop);
        p_leveldown = (TextView) findViewById(R.id.LevelPreDown);
        p_levelheart = (TextView) findViewById(R.id.LevelHaert);


        setValue(sysDate);

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePicker.setYearRange(1950, 2030);
                mDatePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        mDatePicker = DatePickerDialog.newInstance(onDateSetListener,
                mCalendar.get(Calendar.YEAR),       // ปี
                mCalendar.get(Calendar.MONTH),      // เดือน
                mCalendar.get(Calendar.DAY_OF_MONTH),// วัน (1-31)
                false);
        diabetesTABLE = new DiabetesTABLE(this);

    }//OnCreate


    private DatePickerDialog.OnDateSetListener onDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

                    mCalendar.set(year, month, day);
                    Date date = mCalendar.getTime();

                    textDate = dfm.format(date);
                    String textDate_insert = dfm_insert.format(date) + "%";

                    setValue(textDate_insert);
                }
            };

    public void setValue(String dateChoose) {
        dateSelectDiabetes = diabetesTABLE.selectDetailByDiabetes(dateChoose);

        chooseDate.setText(textDate);
        d_date.setText(dateSelectDiabetes.get(DiabetesTABLE.D_DateTime));
        d_CostSugarBefore.setText(dateSelectDiabetes.get(DiabetesTABLE.D_CostSugarBefore));
        d_CostSugarAfter.setText(dateSelectDiabetes.get(DiabetesTABLE.D_CostSugarAfter));
        //d_LevelCostBefore.setText(dateSelectDiabetes.get(DiabetesTABLE.D_LevelCostBefore));
        //d_LevelCostAfter.setText(dateSelectDiabetes.get(DiabetesTABLE.D_LevelCostAfter));
        k_date.setText(dateSelectDiabetes.get(DiabetesTABLE.K_DateTime));
        k_cost.setText(dateSelectDiabetes.get(DiabetesTABLE.K_CostGFR));
        //k_level.setText(dateSelectDiabetes.get(DiabetesTABLE.K_LevelCostGFR));
        p_date.setText(dateSelectDiabetes.get(DiabetesTABLE.P_DateTime));
        p_costtop.setText(dateSelectDiabetes.get(DiabetesTABLE.P_CostPressureTop));
        p_costdown.setText(dateSelectDiabetes.get(DiabetesTABLE.P_CostPressureDown));
        p_leveltop.setText(dateSelectDiabetes.get(DiabetesTABLE.P_Cost_Level_Top));
        p_leveldown.setText(dateSelectDiabetes.get(DiabetesTABLE.P_Cost_Level_Down));
        p_heart.setText(dateSelectDiabetes.get(DiabetesTABLE.P_HeartRate));
        p_levelheart.setText(dateSelectDiabetes.get(DiabetesTABLE.P_HeartRate_Level));

        //แสดงรูปภาพตามที่ต้องการ  D_CostSugarBefore
        if (dateSelectDiabetes.get(DiabetesTABLE.D_CostSugarBefore) != null) {
            findMyLevelDiseaseBefore(dateSelectDiabetes.get(DiabetesTABLE.D_CostSugarBefore));
        }


        //แสดงรูปภาพตามที่ต้องการ  D_CostSugarAfter
        if (dateSelectDiabetes.get(DiabetesTABLE.D_CostSugarAfter) != null) {
            findMyLevelDiseaseAfter(dateSelectDiabetes.get(DiabetesTABLE.D_CostSugarAfter));
        }


        //แสดงรูปภาพตามที่ต้องการ K_Level
        if (dateSelectDiabetes.get(DiabetesTABLE.K_CostGFR) != null) {
            findMyLevelCostGFR(dateSelectDiabetes.get(DiabetesTABLE.K_CostGFR));
        }

    }

    private String findMyLevelDiseaseBefore(String intCostSugarAfter) {
        String myResult = null;
        int IntCostSugarAfter = Integer.parseInt(intCostSugarAfter);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prorediabefore);


        if (IntCostSugarAfter > 120) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else if (IntCostSugarAfter <  80) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore3));
        } else {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore2));
        }

        return myResult;
    }//findMyLevelDiseasebefore


    private String findMyLevelDiseaseAfter(String intCostSugarAfter) {
        String myResult = null;
        Integer IntCostSugarAfter = Integer.parseInt(intCostSugarAfter);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prorediaafter);


        if (IntCostSugarAfter > 160) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else if (IntCostSugarAfter < 100) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore3));
        } else {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore2));
        }

        return myResult;
    }//findMyLevelDiseaseafter

    private String findMyLevelCostGFR(String intCostGFR) {
        String myResult = null;
        Integer IntCostGFR = Integer.parseInt(intCostGFR);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.levelkid);

        if (IntCostGFR > 90) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else if (IntCostGFR > 60 ) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else if (IntCostGFR > 30) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else if (IntCostGFR > 15) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        }
        return myResult;
    }//findMyLevelDiseaseafter



}//Main Class
