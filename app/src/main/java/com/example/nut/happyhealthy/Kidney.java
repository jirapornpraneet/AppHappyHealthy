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
import android.widget.TextView;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Kidney extends AppCompatActivity {


    //การประกาศตัวแปร
    private KidneyTABLE  objkidneyTABLE;
    private EditText K_costGFR;
    private String  str_K_Date,intCostGFR,str_L_cost ;
    private TextView K_date;
    SimpleDateFormat df_show,df_insert;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidney);





        //Bind widget
        K_date = (TextView) findViewById(R.id.K_date);
        K_costGFR = (EditText) findViewById(R.id.K_costGFR);

        connectDataBase();

        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        df_insert = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        c = Calendar.getInstance();
        K_date.setText(df_show.format(c.getTime()));

    }//Oncreate

    private void connectDataBase() {

        objkidneyTABLE = new KidneyTABLE(this);
    }//ConnectDatabase


    public void ClickDisLevelsGFR(View view) {

        //get value edit tezt
        str_K_Date = K_date.getText().toString().trim();
        intCostGFR = K_costGFR.getText().toString().trim();


        //Checkspace
        if (str_K_Date.equals("") || intCostGFR.equals("")) {
            showAlert();


        }else  {

            confirmKidney();



        }
    }//Click

    private void confirmKidney() {

        str_L_cost = findMyLevelCostGFR();


        /** Find BMI
        int  intcostgfr = Integer.parseInt(intCostGFR);


        int IntCostGFR = intcostgfr;**/


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("คุณต้องการบันทึกข้อมูลใช่ไหม?");
        builder.setMessage(" วันที่ : " + str_K_Date+ "\n" +
                " ค่าการทำงานไต : " + intCostGFR + "\n"+
                " อยู่ในเกณฑ์ที่ : " + str_L_cost);
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

    private String findMyLevelCostGFR() {
        String[] resultStrings = getResources().getStringArray(R.array.my_kidney);
        String myResult = null;
        Integer IntCostGFR = Integer.parseInt(intCostGFR);

        if (IntCostGFR > 90) {
            myResult = resultStrings[0];
        } else if (IntCostGFR > 60 ) {
            myResult = resultStrings[1];
        } else if (IntCostGFR > 30) {
            myResult = resultStrings[2];
        } else if (IntCostGFR > 15) {
            myResult = resultStrings[3];
        } else {
            myResult = resultStrings[4];
        }
        return myResult;
    }//findMyLevelCostGFR

    private void upDataKidneytoSQLite() {

        KidneyTABLE objkidneyTABLE = new KidneyTABLE(this);
        long inSertDataUser = objkidneyTABLE.addNewValueToSQLite
                (str_K_Date, Integer.parseInt(intCostGFR),str_L_cost);
        K_date.setText("");
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

    public void ClickHistoryKidney(View view) {
        startActivity(new Intent(Kidney.this,History_Kidney.class));
    }//ClickHistoryKidney


    public void ClickBackKidHome(View view) {
        startActivity(new Intent(Kidney.this,MainActivity.class));
    }//ClickBackHome


}//Main class
