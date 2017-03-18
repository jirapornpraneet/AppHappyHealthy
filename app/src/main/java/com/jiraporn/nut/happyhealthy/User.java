package com.jiraporn.nut.happyhealthy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
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
    TextView chooseDate, d_date, d_CostSugar,  d_Level ,d_status,
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
        d_CostSugar = (TextView) findViewById(R.id.CostSugar);
        d_status = (TextView) findViewById(R.id.d_status);
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
        d_CostSugar.setText(dateSelectDiabetes.get(DiabetesTABLE.D_CostSugar));
        d_status.setText(dateSelectDiabetes.get(DiabetesTABLE.D_Status));
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
        if (dateSelectDiabetes.get(DiabetesTABLE.D_CostSugar) != null) {
            findMyLevelDiseaseBefore(dateSelectDiabetes.get(DiabetesTABLE.D_CostSugar)
                    ,dateSelectDiabetes.get(DiabetesTABLE.D_Status)
                    ,dateSelectDiabetes.get(DiabetesTABLE.D_People));
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

    /**private String findMyLevelDiseaseBefore(String intCostSugar) {
        String myResult = null;
        int IntCostSugarBefore = Integer.parseInt(intCostSugar);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prorediabefore);


        if (IntCostSugarBefore >=300 ) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
        } else if ((IntCostSugarBefore >= 200) & (IntCostSugarBefore < 300  )) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore4));
        } else if ((IntCostSugarBefore >= 100) & (IntCostSugarBefore < 200 )) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore5));
        } else if ((IntCostSugarBefore >= 70)  & (IntCostSugarBefore < 100 )) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore2));
        } else {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore3));
        }


        return myResult;
    }//findMyLevelDiseasebefore**/




    private void findMyLevelDiseaseBefore(String intCostSugar, String statSugar, String foodTime) {
//        String[] resultStrings = getResources().getStringArray(R.array.my_disease);
        String[] sugar = getResources().getStringArray(R.array.my_people);
        String[] time = getResources().getStringArray(R.array.my_status);
        Integer IntCostSugar = Integer.parseInt(intCostSugar);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.prorediabefore);

        if (statSugar.equals(sugar[0])) {
            if (foodTime.equals(time[0])) {
                if (IntCostSugar >= 126) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertnormal));
                } else if ((IntCostSugar >=70) & (IntCostSugar < 126)){
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertnormal2));
                } else {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertnormal1));
                }
            } else {
                if (IntCostSugar >= 200) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertnormal));
                } else if ((IntCostSugar >=70) & (IntCostSugar < 200)){
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertnormal2));
                } else {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertnormal1));
                }
            }
        } else {
            if (foodTime.equals(time[0])) {
                if (IntCostSugar >= 130) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
                } else if ((IntCostSugar >= 100) & (IntCostSugar < 130)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore4));
                } else if ((IntCostSugar >= 90) & (IntCostSugar < 100)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore5));
                } else if ((IntCostSugar >= 70) & (IntCostSugar < 90)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore2));
                } else {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore3));
                }
            } else {//เข้าloop ตลอด
                if (IntCostSugar >= 180) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore1));
                } else if ((IntCostSugar >= 150) & (IntCostSugar < 180)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore4));
                } else if ((IntCostSugar >= 110) & (IntCostSugar < 150)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore5));
                } else if ((IntCostSugar >= 70) & (IntCostSugar < 110)) {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore2));
                } else {
                    imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore3));
                }
            }
        }
    }//findMyLevelDiseasebefore

    private String findMyLevelCostGFR(String intCostGFR) {
        String myResult = null;
        Integer IntCostGFR = Integer.parseInt(intCostGFR);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.levelkid);

        if (IntCostGFR >= 90) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertdibefore2));
        } else if ((IntCostGFR >=60 ) & (IntCostGFR < 90)) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertkid2));
        } else if ((IntCostGFR >= 30) & (IntCostGFR <60)){
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertkid3));
        } else if ((IntCostGFR >=15) & (IntCostGFR <30)) {
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
            } else if ((IntCostPressureTop >= 160) & (IntCostPressureTop <180))  {
                CostTop = 1;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre2));
            } else if ((IntCostPressureTop >= 140) & (IntCostPressureTop <160)) {
                CostTop = 2;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre3));
            } else if ((IntCostPressureTop >= 130 ) & (IntCostPressureTop <140)) {
                CostTop = 3;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre4));
            } else if ((IntCostPressureTop >= 120 ) & (IntCostPressureTop <130)) {
                CostTop = 4;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre5));
            } else if ((IntCostPressureTop >= 90 ) & (IntCostPressureTop <120)) {
                CostTop = 5;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre0));
            } else {
                CostTop = 0;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre6));
            }
        }

        if (intCostPressureDown != null) {
            if (IntCostPressureDown >= 110) {
                CostDown = 0;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre1));
            } else if ((IntCostPressureDown >= 100) & (IntCostPressureDown <110 )) {
                CostDown = 1;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre2));
            } else if ((IntCostPressureDown >= 90 ) & (IntCostPressureDown <100)) {
                CostDown = 2;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre3));
            } else if ((IntCostPressureDown >= 85 ) & (IntCostPressureDown <90)) {
                CostDown = 3;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre4));
            } else if ((IntCostPressureDown >= 80 ) & (IntCostPressureDown <85)){
                CostDown = 4;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre5));
            } else if ((IntCostPressureDown >= 60  ) & (IntCostPressureDown <80)) {
                CostDown = 5;
                imageView.setImageDrawable(res.getDrawable(R.drawable.alertpre0));
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

        if (IntHeart >= 101) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart5));
        } else if ((IntHeart >= 85)& (IntHeart < 101)) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart4));
        } else if ((IntHeart >= 70)& (IntHeart < 85)) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart3));
        } else if ((IntHeart >=60)& (IntHeart < 70)) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart2));
        } else if ((IntHeart >=41)&(IntHeart < 60 )) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart1));
        } else {
            imageView.setImageDrawable(res.getDrawable(R.drawable.alertheart5));
        }


        return myResult;

    }//findMyLevelHeart

    public void ClickBackuserMain(View view) {
        startActivity(new Intent(User.this, MainActivity.class));

    }//ClickBackHome

}//Main Class
