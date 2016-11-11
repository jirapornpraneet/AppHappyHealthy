package com.example.nut.happyhealthy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Calendar;

public class Pressure extends AppCompatActivity {

    //การประกาศตัวแปร
    private PressureTABLE objpressureTABLE;
    private EditText P_date,P_time,P_costPressureTop,P_costPressureDown;
    private String  str_P_Date,str_P_Time,intCostPressureDown,intCostPressureTop,str_LP_cost_down,str_LP_cost_top;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);


        //Use the current date as the default date to the picker
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        final EditText txtTime = (EditText) findViewById(R.id.P_time);
        final EditText txtDate = (EditText) findViewById(R.id.P_date);

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Pressure.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        txtTime.setText(i + ":" + i1);
                    }
                }, hour, minute, true);
                timePickerDialog.setTitle("เลือกเวลา");
                timePickerDialog.show();
            }
        });//setTimepicker


        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Pressure.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtDate.setText(i2 + "-" + i1 + "-" + i);
                    }
                }, day, month, year);
                datePickerDialog.setTitle("เลือกวันที่");
                datePickerDialog.show();

            }
        });//setdatepicker


        //Bind wiget
        P_date = (EditText) findViewById(R.id.P_date);
        P_time = (EditText) findViewById(R.id.P_time);
        P_costPressureTop = (EditText) findViewById(R.id.P_costPressureTop);
        P_costPressureDown = (EditText) findViewById(R.id.P_costPressureDown);

        connectDataBase();


    }//Oncreate

    private void connectDataBase() {
       objpressureTABLE = new PressureTABLE(this);
    }//ConnectDatabase

    public void ClickDisLevelsPre(View view) {

        //get value edit tezt
        str_P_Date = P_date.getText().toString().trim();
        str_P_Time = P_time.getText().toString().trim();
        intCostPressureDown = P_costPressureDown.getText().toString().trim();
        intCostPressureTop = P_costPressureTop.getText().toString().trim();


        //Checkspace
        if (str_P_Date.equals("") || str_P_Time.equals("") || intCostPressureDown.equals("")|| intCostPressureTop.equals("")) {
            showAlert();


        }else  {
            confirmPressure();

        }
    }//Click

    private void confirmPressure() {

        str_LP_cost_down = findMyLevelPressureDown();
        str_LP_cost_top = findMyLevelPressureTop();

        /** Find BMI
        int  intcostpressuredown = Integer.parseInt(intCostPressureDown);
        int  intcostpressuretop = Integer.parseInt(intCostPressureTop);

        int IntCostPressureDown = intcostpressuredown;
        int IntCostPressureTop = intcostpressuretop ;**/

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("คุณต้องการบันทึกข้อมูลใช่ไหม?");
        builder.setMessage(" วันที่  : " + str_P_Date + "\n"
                + " เวลา : " + str_P_Time + "\n"
                + " ค่าความดันตัวล่าง : " + intCostPressureDown + "\n"
                + " อยู่ในเกณฑ์ที่ : " + str_LP_cost_down+"\n"
                + " ค่าความดันตัวบน : " + intCostPressureTop + "\n"
                + " อยู่ในเกณฑ์ที่ : " + str_LP_cost_top);
        builder.setCancelable(false);
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                upDataPressuretoSQLite();

            }
        });
        builder.show();
    }//confirmPressure

    private String findMyLevelPressureTop() {
            String[] resultStrings = getResources().getStringArray(R.array.my_pressure);
            String myResult = null;
            Integer IntCostPressureTop = Integer.parseInt(intCostPressureTop);

            if (IntCostPressureTop < 100) {
                myResult = resultStrings[0];
            } else if (IntCostPressureTop < 130) {
                myResult = resultStrings[1];
            } else if (IntCostPressureTop < 140) {
                myResult = resultStrings[2];
            } else if (IntCostPressureTop < 160) {
                myResult = resultStrings[3];
            } else if (IntCostPressureTop < 180) {
                myResult = resultStrings[4];
            } else {
                myResult = resultStrings[5];
            }

            return myResult;

    }//findMyLevelPressureTop

    private String findMyLevelPressureDown() {
            String[] resultStrings = getResources().getStringArray(R.array.my_pressure);
            String myResult = null;
            Integer IntCostPressureDown = Integer.parseInt(intCostPressureDown);

            if (IntCostPressureDown < 60) {
                myResult = resultStrings[0];
            } else if (IntCostPressureDown < 88) {
                myResult = resultStrings[1];
            } else if (IntCostPressureDown < 90) {
                myResult = resultStrings[2];
            } else if (IntCostPressureDown < 100) {
                myResult = resultStrings[3];
            } else if (IntCostPressureDown < 110) {
                myResult = resultStrings[4];
            } else {
                myResult = resultStrings[5];
            }

            return myResult;

        }//findMyLevelPressureDown

    private void upDataPressuretoSQLite() {
        PressureTABLE objpressureTABLE = new PressureTABLE(this);
        long inSertDataUser = objpressureTABLE.addNewValueToSQLite
                (str_P_Date, str_P_Time, Integer.parseInt(intCostPressureDown), Integer.parseInt(intCostPressureTop),str_LP_cost_down,str_LP_cost_top);
        P_date.setText("");
        P_time.setText("");
        P_costPressureTop.setText("");
        P_costPressureDown.setText("");
        Toast.makeText(Pressure.this,"บันทึกข้อมูลเรียบร้อย",Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(Pressure.this, DisplayPressure.class);
        startActivity(objIntent);
        finish();
    }// upDataPressuretoSQLit

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ข้อมูลไม่ครบถ้วน");
        builder.setMessage("กรุณาใส่ข้อมูลผู้ใช้งานให้ครบ");
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }//ShowAlert

    public void ClickHisPre(View view) {
        startActivity(new Intent(Pressure.this,History_Pressure.class));
    }//ClickHistoryPre




}//Main class
