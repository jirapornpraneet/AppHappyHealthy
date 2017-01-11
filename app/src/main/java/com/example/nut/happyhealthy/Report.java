package com.example.nut.happyhealthy;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class Report extends AppCompatActivity {

    DatePickerDialog mDatePicker;
    Calendar mCalendar;
    Calendar c;
    SimpleDateFormat df_show;
    String sysDate;
    String textDate;

    TextView chooseDate, totalCal, totalFood, totalExe, protain, carbo, fat, sugar, sodium;

    FoodHistoryTABLE foodHistoryTABLE;
    HashMap<String, String> dataSelectSum;

    SimpleDateFormat dfm,dfm_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        dfm = new SimpleDateFormat("dd-MMMM-yyyy");
        dfm_insert = new SimpleDateFormat("yyyy-MM-dd");

        c = Calendar.getInstance();
        sysDate = dfm_insert.format(c.getTime())+"%";
        textDate = dfm.format(c.getTime());

        mCalendar = Calendar.getInstance();

        chooseDate = (TextView) findViewById(R.id.chooseDate);
        totalCal = (TextView) findViewById(R.id.totalCal);
        totalFood = (TextView) findViewById(R.id.tv_sum_food_cal);
        totalExe = (TextView) findViewById(R.id.tv_sum_ex_cal);
        protain = (TextView) findViewById(R.id.tv_sum_pro);
        carbo = (TextView) findViewById(R.id.tv_sum_car);
        fat = (TextView) findViewById(R.id.tv_sum_fat);
        sugar = (TextView) findViewById(R.id.tv_sum_sugar);
        sodium = (TextView) findViewById(R.id.tv_sum_sodium);

        foodHistoryTABLE = new FoodHistoryTABLE(this);
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
                    sysDate = dfm_insert.format(date)+"%";

                    setValue(sysDate);
                }
            };

    public void setValue(String dateChoose) {
        dataSelectSum = foodHistoryTABLE.selectSUM(dateChoose);

        chooseDate.setText(textDate);
        totalFood.setText(dataSelectSum.get(FoodHistoryTABLE.SUM_Food_Cal));
        totalExe.setText(dataSelectSum.get(FoodHistoryTABLE.SUM_EX_Cal));
        protain.setText(dataSelectSum.get(FoodHistoryTABLE.SUM_pro));
        carbo.setText(dataSelectSum.get(FoodHistoryTABLE.SUM_car));
        fat.setText(dataSelectSum.get(FoodHistoryTABLE.SUM_fat));
        sugar.setText(dataSelectSum.get(FoodHistoryTABLE.SUM_sugar));
        sodium.setText(dataSelectSum.get(FoodHistoryTABLE.SUM_sodium));
        totalCal.setText(String.format("%.2f",(Double.parseDouble(dataSelectSum.get(FoodHistoryTABLE.Total_Cal)))));
    }
    public void ClickFoodHis(View view) {
        Intent intent = new Intent(Report.this,History_Food.class);
        intent.putExtra("choose_Date",sysDate);
        startActivity(intent);
        finish();
    }//ClickFood

    public void ClickExeHis(View view) {
        Intent intent = new Intent(Report.this,History_Exe.class);
        intent.putExtra("choose_Date",sysDate);
        startActivity(intent);
        finish();
    }//ClickFood
}//MainClass
