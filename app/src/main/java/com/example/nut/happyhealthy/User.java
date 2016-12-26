package com.example.nut.happyhealthy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    DiabetesTABLE diabetesTABLE;
    HashMap<String, String> dateSelectDiabetes;
    TextView chooseDate, d_CostSugarBefore, d_CostSugarAfter, d_LevelCostBefore, d_LevelCostAfter;

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
        d_CostSugarBefore = (TextView) findViewById(R.id.CostSugarBefore);
        d_CostSugarAfter = (TextView) findViewById(R.id.CostSugarAfter);
        d_LevelCostBefore = (TextView) findViewById(R.id.LevelCostBefore);
        d_LevelCostAfter = (TextView) findViewById(R.id.LevelCostAfter);

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
        d_CostSugarBefore.setText(dateSelectDiabetes.get(DiabetesTABLE.D_CostSugarBefore));
        d_CostSugarAfter.setText(dateSelectDiabetes.get(DiabetesTABLE.D_CostSugarAfter));
        d_LevelCostBefore.setText(dateSelectDiabetes.get(DiabetesTABLE.D_LevelCostBefore));
        d_LevelCostAfter.setText(dateSelectDiabetes.get(DiabetesTABLE.D_LevelCostAfter));
    }

}//Main Class
