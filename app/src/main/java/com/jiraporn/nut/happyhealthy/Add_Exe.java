package com.jiraporn.nut.happyhealthy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Add_Exe extends AppCompatActivity {


    //การประกาศตัวแปร
    private ExerciseTABLE exerciseTABLE;
    private EditText e_name,e_minute,e_cal,e_detail;
    private String str_exe_name,dou_duration,dou_exe_cal,str_exe_detaill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__exe);
        //Bind wiget
        e_name = (EditText) findViewById(R.id.tv_exercise_name);
        e_minute = (EditText) findViewById(R.id.e_minute);
        e_cal = (EditText) findViewById(R.id.e_cal);
        e_detail = (EditText) findViewById(R.id.e_detail);

        connectDataBase();
    }//OnCreate
    private void connectDataBase() {

        exerciseTABLE = new ExerciseTABLE(this);

    }//ConnectDataBase


    public void ClickSaveExe(View view) {

        //get value edit tezt
        str_exe_name = e_name.getText().toString().trim();
        dou_duration = e_minute.getText().toString().trim();
        dou_exe_cal = e_cal.getText().toString().trim();
        str_exe_detaill = e_detail.getText().toString().trim();

        //Checkspace
        if (str_exe_name.equals("") || dou_duration.equals("") || dou_exe_cal.equals("") || str_exe_detaill.equals("")) {

            showAlert();



        } else {
            upDataAddExetoSQLite();

        }
    }//Click

    private void upDataAddExetoSQLite() {

        ExerciseTABLE exerciseTABLE = new ExerciseTABLE(this);
        long inSertDataUser = exerciseTABLE.addNewValueToSQLite
                ( str_exe_name, Double.parseDouble(dou_duration), Double.parseDouble(dou_exe_cal), str_exe_detaill);
        e_name.setText("");
        e_cal.setText("");
        e_minute.setText("");
        e_detail.setText("");
        Toast.makeText(Add_Exe.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(getApplicationContext(), ExerciseType.class);
        objIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(objIntent);
        finish();
    }//upDataDiabetestoSQLite


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ข้อมูลไม่ครบถ้วน");
        builder.setMessage("กรุณาใส่ข้อมูล");
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }//ShowAlert
    public void ClickAddExeBack(View view) {
        startActivity(new Intent(Add_Exe.this, MainActivity.class));
    }//ClickHistoryDiabetes
}//MainClass
