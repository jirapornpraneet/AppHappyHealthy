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

public class Diabetes extends AppCompatActivity {

    //การประกาศตัวแปร
    private DiabetesTABLE objdiabetesTABLE;
    private EditText D_date,D_time,D_costSugarBefore,D_costSugarAfter;
    private String  str_D_Date,str_D_Time,intCostSugarBefore,intCostSugarAfter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes);

        //Use the current date as the default date to the picker
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        final EditText txtTime = (EditText) findViewById(R.id.D_time);
        final EditText txtDate = (EditText) findViewById(R.id.D_date);

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Diabetes.this, new TimePickerDialog.OnTimeSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(Diabetes.this, new DatePickerDialog.OnDateSetListener() {
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
        D_date = (EditText) findViewById(R.id.D_date);
        D_time = (EditText) findViewById(R.id.D_time);
        D_costSugarBefore = (EditText) findViewById(R.id.D_costSugarBefore);
        D_costSugarAfter = (EditText) findViewById(R.id.D_costSugarAfter);

        connectDataBase();


    }////main method

    private void connectDataBase() {

        objdiabetesTABLE = new DiabetesTABLE(this);

    }//ConnectDataBase

    public void ClickDisLevelsSugar(View view) {

        //get value edit tezt
        str_D_Date = D_date.getText().toString().trim();
        str_D_Time = D_time.getText().toString().trim();
        intCostSugarBefore = D_costSugarBefore.getText().toString().trim();
        intCostSugarAfter = D_costSugarAfter.getText().toString().trim();


        //Checkspace
        if (str_D_Date.equals("") || str_D_Time.equals("") || intCostSugarBefore.equals("")|| intCostSugarAfter.equals("")) {

            showAlert();


            /**MyAlert myAlert = new MyAlert();
             myAlert.myDialog(this, "เบาหวาน", "กรุณาใส่ข้อมูลผู้ใช้งานให้ครบค่ะ");**/


        }else  {
            confirmDiabetes();


        }
    }//Click

    private void confirmDiabetes() {

        // Find BMI
        int  intcostsugarbefore = Integer.parseInt(intCostSugarBefore);
        int  intcostsugarafter = Integer.parseInt(intCostSugarAfter);

        int IntCostSugarBefore = intcostsugarbefore;
        int IntCostSugarAfter = intcostsugarafter;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("คุณต้องการบันทึกข้อมูลใช่ไหม?");
        builder.setMessage("วันที่ =" + str_D_Date+ "\n"
                + "เวลา = " + str_D_Time +"\n"
                + "ค่าน้ำตาลก่อนอาหารของผู้ใช้งาน = " + intCostSugarBefore +"\n"
                + "ค่าน้ำตาลหลังอาหารของผู้ใช้งาน = " + intCostSugarAfter);
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
                upDataDiabetestoSQLite();

            }
        });
        builder.show();
    }//ComfirmData

    private void upDataDiabetestoSQLite() {

        DiabetesTABLE objdiabetesTABLE = new DiabetesTABLE(this);
        long inSertDataUser = objdiabetesTABLE.addNewValueToSQLite
                (str_D_Date, str_D_Time, Integer.parseInt(intCostSugarBefore), Integer.parseInt(intCostSugarAfter));
        D_date.setText("");
        D_time.setText("");
        D_costSugarBefore.setText("");
        D_costSugarAfter.setText("");
        Toast.makeText(Diabetes.this,"บันทึกข้อมูลเรียบร้อย",Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(Diabetes.this, DisplayDisease.class);
        startActivity(objIntent);
        finish();
    }//upDataDiabetestoSQLite

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



}//Main Class