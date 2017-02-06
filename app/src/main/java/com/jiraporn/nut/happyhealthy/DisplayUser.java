package com.jiraporn.nut.happyhealthy;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayUser extends AppCompatActivity {


    SQLiteDatabase db;
    MyDatabase myDatabase;

    private UserTABLE objUserTABLE;
    //Explicit
    private EditText TVName, TVAge, TVWeight, TVHeight, TVSex;
    private TextView TVBMR, TVBMI, weightStdTextView;
    private String strName, strAge, intHeight, douWeight, douBmr, douBmi, weightStdString, strSex;
    private RadioButton man,women;
    private RadioGroup User_Sex;

    RadioGroup choose_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        myDatabase = new MyDatabase(this);
        // Bind Widget
        bindWidget();

        // Show View
        showView();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        showView();
    }

    private void showView() {
        db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserTABLE.USER, null);

        if (cursor.moveToFirst()) {
            do {
                strName = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Name));
                strSex = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Sex));
                strAge = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Age));
                intHeight = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Height));
                douWeight = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Weight));
                douBmr = cursor.getString(cursor.getColumnIndex(UserTABLE.User_BMR));
                douBmi = cursor.getString(cursor.getColumnIndex(UserTABLE.User_BMI));
                weightStdString = findMyAlertWeight(douBmi);
            } while (cursor.moveToNext());
        }
        cursor.close();

        if (strSex.equals("man")) {
            man.setChecked(true);
        }else{
            women.setChecked(true);
        }

        TVName.setText(strName);
        //TVSex.setText(strSex);
        TVAge.setText(strAge);
        TVWeight.setText(douWeight);
        TVHeight.setText(intHeight);
        TVBMR.setText(douBmr);
        TVBMI.setText(douBmi);
        weightStdTextView.setText(weightStdString);



    } // Show View

    private String findMyAlertWeight(String douBmi) {

        String[] resultStrings = getResources().getStringArray(R.array.my_alert);
        String myResult = null;

        double douBMI = Double.parseDouble(douBmi);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.imageView64);


        if (douBMI <= 18.5) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi1));
        } else if (douBMI < 22.9) {
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi2));
        } else if (douBMI < 24.9) {
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi3));
        } else if (douBMI < 29.9) {
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi4));
        } else {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi5));
        }

        return myResult;

    }

    private void bindWidget() {

        TVName = (EditText) findViewById(R.id.tv_Name);
       // TVSex = (EditText) findViewById(R.id.tv_Sex);
        TVAge = (EditText) findViewById(R.id.tv_Age);
        TVWeight = (EditText) findViewById(R.id.tv_Weight);
        TVHeight = (EditText) findViewById(R.id.tv_Height);
        TVBMR = (TextView) findViewById(R.id.tv_BMR);
        TVBMI = (TextView) findViewById(R.id.tv_BMI);
         weightStdTextView = (TextView) findViewById(R.id.weightStdTextView);
        man = (RadioButton) findViewById(R.id.man);
        women = (RadioButton) findViewById(R.id.woman);
        User_Sex = (RadioGroup) findViewById(R.id.User_Sex);

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

    }//bindWidget


    public void ClickSaveDataUser(View view) {

        //get value edit tezt
        strName = TVName.getText().toString().trim();
        strAge = TVAge.getText().toString().trim();
        douWeight = TVWeight.getText().toString().trim();
        intHeight = TVHeight.getText().toString().trim();
        //strSex = TVSex.getText().toString().trim();

        //Checkspace
        if (strName.equals("") || strAge.equals("") || douWeight.equals("") || intHeight.equals("") || strSex.equals("")) {
            showAlert();

        } else {//UnCheck
            confirmData();
            showView();

        }





    }//ClickSaveUser


    private void confirmData() {

        // Find BMI
        double douweight = Double.parseDouble(douWeight);
        double douheight = Double.parseDouble(intHeight);
        double douAge = Double.parseDouble(strAge);

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

        UserTABLE objUserTABLE = new UserTABLE(this);
//        long inSertDataUser = objUserTABLE.addNewValueToSQLite(strName, strSex, strAge, Integer.parseInt(intHeight), Double.parseDouble(douWeight), Double.parseDouble(douBmr), Double.parseDouble(douBmi));
        objUserTABLE.addNewValueToSQLite(
                strName, strSex,
                strAge, Integer.parseInt(intHeight), Double.parseDouble(douWeight),
                Double.parseDouble(douBmr), Double.parseDouble(douBmi));
        TVName.setText("");
        TVAge.setText("");
        TVHeight.setText("");
        TVWeight.setText("");
        Toast.makeText(DisplayUser.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();

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
        if (strSex.equals("male")) {
            intResult = 0;
        } else {
            intResult = 1;
        }

        return intResult;
    }




}//MainClass
