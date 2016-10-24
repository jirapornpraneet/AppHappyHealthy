package com.example.nut.happyhealthy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

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
    private EditText K_date,K_time,K_costGFR;
    private String  dateString,timeString,costGFRString ;
    private static final String urlPHP = "http://csnonrmutsb.com/happyhealthy/php_add_kidney.php";

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

    }//Oncreate


    public void ClickDisLevelsGFR(View view) {

        //get value edit tezt
        dateString = K_date.getText().toString().trim();
        timeString = K_time.getText().toString().trim();
        costGFRString = K_costGFR.getText().toString().trim();


        //Checkspace
        if (dateString.equals("") || timeString.equals("") || costGFRString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ไต", "กรุณาใส่ข้อมูลผู้ใช้งานให้ครบค่ะ");


        }else  {
            //Checked
            updateNewKidneyToServer();
            startActivity(new Intent(Kidney.this,DisplayUser.class));


        }
    }//Click

    private void updateNewKidneyToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("K_date", dateString)
                .add("K_time", timeString)
                .add("K_costGFR", costGFRString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });

    }//update

}//Main class
