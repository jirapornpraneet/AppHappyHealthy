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

public class Kidney extends AppCompatActivity {


    //การประกาศตัวแปร
    private KidneyTABLE  objkidneyTABLE;
    private EditText K_date,K_time,K_costGFR;
    private String  str_K_Date,str_K_Time,intCostGFR ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidney);

        //Use the current date as the default date to the picker
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        final EditText txtTime = (EditText) findViewById(R.id.K_time);
        final EditText txtDate = (EditText) findViewById(R.id.K_date);
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Kidney.this, new TimePickerDialog.OnTimeSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(Kidney.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtDate.setText(i2 + "-" + i1 + "-" + i);
                    }
                }, day, month, year);
                datePickerDialog.setTitle("เลือกวันที่");
                datePickerDialog.show();

            }
        });//setdatepicker

        //Bind widget
        K_date = (EditText) findViewById(R.id.K_date);
        K_time = (EditText) findViewById(R.id.K_time);
        K_costGFR = (EditText) findViewById(R.id.K_costGFR);

        connectDataBase();

    }//Oncreate

    private void connectDataBase() {

        objkidneyTABLE = new KidneyTABLE(this);
    }//ConnectDatabase


    public void ClickDisLevelsGFR(View view) {

        //get value edit tezt
        str_K_Date = K_date.getText().toString().trim();
        str_K_Time = K_time.getText().toString().trim();
        intCostGFR = K_costGFR.getText().toString().trim();


        //Checkspace
        if (str_K_Date.equals("") || str_K_Time.equals("") || intCostGFR.equals("")) {
            showAlert();


        }else  {

            confirmKidney();



        }
    }//Click

    private void confirmKidney() {


        // Find BMI
        int  intcostgfr = Integer.parseInt(intCostGFR);


        int IntCostGFR = intcostgfr;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("คุณต้องการบันทึกข้อมูลใช่ไหม?");
        builder.setMessage("วันที่ =" + str_K_Date+ "\n"
                + "เวลา = " + str_K_Time +"\n" +
                "ค่าการทำงานไตของผู้ใช้งาน = " + intCostGFR);
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
                upDataKidneytoSQLite();

            }
        });
        builder.show();
    }//ConfirmKidney

    private void upDataKidneytoSQLite() {

        KidneyTABLE objkidneyTABLE = new KidneyTABLE(this);
        long inSertDataUser = objkidneyTABLE.addNewValueToSQLite
                (str_K_Date, str_K_Time, Integer.parseInt(intCostGFR));
        K_date.setText("");
        K_time.setText("");
        K_costGFR.setText("");
        Toast.makeText(Kidney.this,"บันทึกข้อมูลเรียบร้อย",Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(Kidney.this, DisplayKidney.class);
        startActivity(objIntent);
        finish();
    }//UpDateKidneytoSQLite

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


}//Main class
