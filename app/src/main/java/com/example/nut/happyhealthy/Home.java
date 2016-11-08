package com.example.nut.happyhealthy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Home extends Activity implements DatePickerDialog.OnDateSetListener {

    //Button picDate;
    //TextView showDate;
    //ประกาศตัวแปร
    private TextView showDate;

    int day,month, year;
    int dayFinal,monthFinal, yearFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //picDate = (Button) findViewById(R.id.picDate);
        showDate = (TextView) findViewById(R.id.showDate);

        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Home.this, Home.this,
                        year,month,day);
                datePickerDialog.show();


            }
        });



    }//Oncreate

    public void ClickFood(View view) {
        startActivity(new Intent(Home.this,FoodType.class));
    }//ClickFood

    public void ClickExe(View view) {startActivity(new Intent(Home.this,ExerciseType.class));}//Clickexe

    public void ClickReport(View view) {startActivity(new Intent(Home.this,Report.class));}//ClickReport


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        dayFinal = i;
        monthFinal = i1 + 1;
        yearFinal = i2;

        //String sSpecificDate = MainActivity.createStringDay(i, i1 + 1,i2);
        showDate.setText("วันที่ :" +  yearFinal+ "/" + monthFinal+ "/" +  dayFinal);

        //displayMedicineByDay(sSpecificDate);

    }
}//MainClass