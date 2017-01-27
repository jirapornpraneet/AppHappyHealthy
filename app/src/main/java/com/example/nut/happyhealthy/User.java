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
        //p_leveltop = (TextView) findViewById(R.id.LevelPreTop);
        //p_leveldown = (TextView) findViewById(R.id.LevelPreDown);
        // p_levelheart = (TextView) findViewById(R.id.LevelHaert);


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
        //p_leveltop.setText(dateSelectDiabetes.get(DiabetesTABLE.P_Cost_Level_Top));
        // p_leveldown.setText(dateSelectDiabetes.get(DiabetesTABLE.P_Cost_Level_Down));
        p_heart.setText(dateSelectDiabetes.get(DiabetesTABLE.P_HeartRate));
        //p_levelheart.setText(dateSelectDiabetes.get(DiabetesTABLE.P_HeartRate_Level));

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

        //แสดงรูปภาพตามที่ต้องการ P_LevelTop
        if (dateSelectDiabetes.get(DiabetesTABLE.P_CostPressureTop) != null) {
            findMyLevelPressureTop(dateSelectDiabetes.get(DiabetesTABLE.P_CostPressureTop), null);
        }

        //แสดงรูปภาพตามที่ต้องการ P_LevelDown
        if (dateSelectDiabetes.get(DiabetesTABLE.P_CostPressureDown) != null) {
            findMyLevelPressureTop(null, dateSelectDiabetes.get(DiabetesTABLE.P_CostPressureDown));
        }

        //แสดงรูปภาพตามที่ต้องการ K_LevelHeart
        if (dateSelectDiabetes.get(DiabetesTABLE.P_HeartRate) != null) {
            findMyLevelHeart(dateSelectDiabetes.get(DiabetesTABLE.P_HeartRate));
        }

    }

    private String findMyLevelDiseaseBefore(String intCostSugarBefore) {
        String myResult = null;
        int IntCostSugarBefore = Integer.parseInt(intCostSugarBefore);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prorediabefore);


        if (IntCostSugarBefore >=300 ) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else if (IntCostSugarBefore >= 200) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore4));
        } else if (IntCostSugarBefore >= 100 ) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore5));
        } else if (IntCostSugarBefore <= 70) {
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


        if (IntCostSugarAfter >= 300 ) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else if (IntCostSugarAfter >= 200) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore4));
        } else if (IntCostSugarAfter >= 110 ) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore5));
        } else if (IntCostSugarAfter <= 70) {
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
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore2));
        } else if (IntCostGFR > 60) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertkid2));
        } else if (IntCostGFR > 30) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertkid3));
        } else if (IntCostGFR > 15) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertkid4));
        } else {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertkid5));
        }
        return myResult;
    }//findMyLevelDiseaseafter


    private String findMyLevelPressureTop(String intCostPressureTop, String intCostPressureDown) {
        String[] resultStrings = getResources().getStringArray(R.array.my_pressure);
        String myResult = null;
        int IntCostPressureTop = 0;
        int IntCostPressureDown = 0;
        if (intCostPressureTop != null) {
            IntCostPressureTop = Integer.parseInt(intCostPressureTop);
        }
        if (intCostPressureDown != null) {
            IntCostPressureDown = Integer.parseInt(intCostPressureDown);
        }
        int CostTop = 0, CostDown = 0;
        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.leveltop);

        if (intCostPressureTop != null) {
            if (IntCostPressureTop >= 180) {
                CostTop = 0;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre1));
            } else if (IntCostPressureTop >= 160) {
                CostTop = 1;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre2));
            } else if (IntCostPressureTop >= 140) {
                CostTop = 2;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre3));
            } else if (IntCostPressureTop >= 130) {
                CostTop = 3;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre4));
            } else if (IntCostPressureTop >= 120) {
                CostTop = 4;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre5));
            } else if (IntCostPressureTop >= 90) {
                CostTop = 5;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre5));
            } else {
                CostTop = 0;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre6));
            }
        }

        if (intCostPressureDown != null) {
            if (IntCostPressureDown >= 110) {
                CostDown = 0;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre1));
            } else if (IntCostPressureDown >= 100) {
                CostDown = 1;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre2));
            } else if (IntCostPressureDown >= 90) {
                CostDown = 2;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre3));
            } else if (IntCostPressureDown >= 85) {
                CostDown = 3;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre4));
            } else if (IntCostPressureDown >= 80) {
                CostDown = 4;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre5));
            } else if (IntCostPressureDown >= 60) {
                CostDown = 5;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre5));
            } else {
                CostDown = 0;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre6));
            }
        }

        if (CostTop > CostDown) {
            myResult = resultStrings[CostDown];
        } else {
            myResult = resultStrings[CostTop];
        }
        return myResult;

    }//findMyLevelPressureTop


    private String findMyLevelHeart(String intCostHeart) {
        String[] resultStrings = getResources().getStringArray(R.array.my_heartrate);
        String myResult = null;
        Integer IntHeart = Integer.parseInt(intCostHeart);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.levelheart);

        if (IntHeart >= 41) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart1));
        } else if (IntHeart < 60) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart1));
        } else if (IntHeart < 70) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart2));
        } else if (IntHeart < 85) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart3));
        } else if (IntHeart < 101) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart4));
        } else {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart5));
        }

        return myResult;

    }//findMyLevelHeart

    public void ClickBackuserMain(View view) {
        startActivity(new Intent(User.this, MainActivity.class));

    }//ClickBackHome

}//Main Class
