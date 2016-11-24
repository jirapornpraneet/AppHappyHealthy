package com.example.nut.happyhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.HashMap;

public class DisplayUser extends AppCompatActivity {


    SQLiteDatabase db;
    MyDatabase myDatabase;
    //Explicit
    private TextView TVName, TVSex, TVAge, TVWeight, TVHeight, TVBMR, TVBMI, weightStdTextView;
    private String strName, strSex, strAge, intHeight, douWeight, douBmr, douBmi, weightStdString;

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
        TVName.setText(strName);
        TVSex.setText(strSex);
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

        if (douBMI < 18.5) {
            myResult = resultStrings[0];
        } else if (douBMI < 22.9) {
            myResult = resultStrings[1];
        } else if (douBMI < 24.0) {
            myResult = resultStrings[2];
        } else if (douBMI < 29.9) {
            myResult = resultStrings[3];
        } else if (douBMI < 39.9) {
            myResult = resultStrings[4];
        } else {
            myResult = resultStrings[5];
        }

        return myResult;
    }

    private void bindWidget() {

        TVName = (TextView) findViewById(R.id.tv_Name);
        TVSex = (TextView) findViewById(R.id.tv_Sex);
        TVAge = (TextView) findViewById(R.id.tv_Age);
        TVWeight = (TextView) findViewById(R.id.tv_Weight);
        TVHeight = (TextView) findViewById(R.id.tv_Height);
        TVBMR = (TextView) findViewById(R.id.tv_BMR);
        TVBMI = (TextView) findViewById(R.id.tv_BMI);
        weightStdTextView = (TextView) findViewById(R.id.weightStdTextView);

    }//bindWidget

    public void ClickEditDataUser(View view) {
        startActivity(new Intent(DisplayUser.this, DataUser.class));
    }//ClickEditUser

    public void ClickSaveDataUser(View view) {
        startActivity(new Intent(DisplayUser.this, MainActivity.class));
    }//ClickSaveUser


    /**
     * Created by Nut on 30/10/2559.
     */
}//MainClass
