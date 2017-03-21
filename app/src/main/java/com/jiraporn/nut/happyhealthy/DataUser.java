package com.jiraporn.nut.happyhealthy;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class DataUser extends AppCompatActivity {

    //การประกาศตัวแปรั
    private UserTABLE objUserTABLE;
    private User_HistoryTABLE user_historyTABLE;
    private EditText User_Name, User_Age, User_Weight, User_Height;
    private RadioGroup User_Sex;
    private RadioButton man, woman;
    private Spinner myACTSpinner;
    private String strName, strSex, strAge, intHeight, douWeight, douBmr, douBmi;
    SimpleDateFormat df_show;
    Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);

        connectDataBase();

        bindWidget();





    }//main method

    private void connectDataBase() {

        objUserTABLE = new UserTABLE(this);
        user_historyTABLE = new User_HistoryTABLE(this);
    }//ConnectDataBase






    private void bindWidget() {

        //Bind wiget
        User_Name = (EditText) findViewById(R.id.User_Name);
        User_Age = (EditText) findViewById(R.id.User_Age);
        User_Weight = (EditText) findViewById(R.id.User_Weight);
        User_Height = (EditText) findViewById(R.id.User_Height);
        User_Sex = (RadioGroup) findViewById(R.id.User_Sex);


        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);

        //Radio Controller
        User_Sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.man:
                        strSex = "man";
                        break;
                    case R.id.woman:
                        strSex = "woman";
                        break;
                }
                // TVSex.setText(strSex);
            }
        });
    }//bindwidget

    public void ClickDisPlay(View view) {

        //get value edit tezt
        strName = User_Name.getText().toString().trim();
        strAge = User_Age.getText().toString().trim();
        douWeight = User_Weight.getText().toString().trim();
        intHeight = User_Height.getText().toString().trim();

        //  actString = myACTSpinner.toString();

        //Checkspace
        if (strName.equals("") || strAge.equals("") || douWeight.equals("") || intHeight.equals("") ) {
            showAlert();
        } else if (checkChoose()) {
            confirmData();
        } else {//UnCheck
            showAlertSex();
        }//ClickDisPlay
    }

    private void showAlertSex() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ยังไม่เลือกเพศ");
        builder.setMessage("กรุณาระบุเพศผู้ใช้งาน");
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        builder.show();
    }//showAlertSex


    private void confirmData() {

        // Find BMI
        double douweight = Double.parseDouble(douWeight);
        double douheight = Double.parseDouble(intHeight);
        double douAge = Double.parseDouble(strAge);
        //double douFac = Double.parseDouble(DouFac);

        double douBMI = douweight / (Math.pow(douheight / 100, 2));
        //  bmiString = Double.toString(douBMI);
        douBmi = String.format("%.2f", douBMI);
        Log.d("cal", "Weight = " + douweight);
        Log.d("cal", "Height = " + douheight);
        Log.d("cal", "BMI = " + douBmi);

        // Find BMR
        double douBMR = 0;
        switch (MaleOrFemale()) {
            case 0: // male
                douBMR = 66 + (13.7 * douweight) + (5 * douheight) - (6.8 * douAge);
                break;
            case 1: // female
                douBMR = 665 + (9.6 * douweight) + (1.8 * douheight) - (4.7 * douAge);
                break;
        } // switch


        douBmr = String.format("%.2f", douBMR);

        UpdateUsertoSQLite();

    }//confirmData

    private void UpdateUsertoSQLite() {
        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        c = Calendar.getInstance();

        UserTABLE objUserTABLE = new UserTABLE(this);
        User_HistoryTABLE user_historyTABLE = new User_HistoryTABLE(this);
//        long inSertDataUser = objUserTABLE.addNewValueToSQLite(strName, strSex, strAge, Integer.parseInt(intHeight), Double.parseDouble(douWeight), Double.parseDouble(douBmr), Double.parseDouble(douBmi));
        objUserTABLE.addNewInsertToSQLite(strName, strSex, strAge);

        user_historyTABLE.insertUserHistory(df_show.format(c.getTime())
                , Double.parseDouble(douWeight)
                , Double.parseDouble(douBmr)
                , Double.parseDouble(douBmi)
                , Integer.parseInt(intHeight)
                , 1);

        User_Name.setText("");
        User_Age.setText("");
        User_Height.setText("");
        User_Weight.setText("");
        Toast.makeText(DataUser.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
        objIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(objIntent);

        finish();

    }//UpdateUsertoSQLite


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ข้อมูลไม่ครบถ้วน");
        builder.setMessage("กรุณาใส่ข้อมูลให้ครบ");
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    } //ShowAlert

    private int MaleOrFemale() {

        int intResult = 0;
        if (strSex.equals("man")) {
            intResult = 0;
        } else {
            intResult = 1;
        }

        return intResult;
    }



    private boolean checkChoose() {
        boolean status = true;
        status = man.isChecked() ||
                woman.isChecked();

        return status;
    }



}//main