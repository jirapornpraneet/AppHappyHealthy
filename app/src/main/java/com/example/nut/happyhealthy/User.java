package com.example.nut.happyhealthy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class User extends Activity {

    //ประกาศตัวแปร ตารางใน database
    private UserTABLE objUserTABLE;
    private DiabetesTABLE objDiabetesTABLE;
    private KidneyTABLE objKidneyTABLE;
    private PressureTABLE objPressureTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        connectedDatabase();

        //Check userTABLE



    }//OnCreate

    public void ClickDataUser(View view) {
        startActivity(new Intent(User.this,DataUser.class));
    }//ClickDataUser

    public void ClickDiabetes(View view) {
       startActivity(new Intent(User.this,Diabetes.class));
    }//ClickDiabetes


    public void ClickKidney(View view) {
        startActivity(new Intent(User.this,Kidney.class));
    }//ClickKidney

    public void ClickPressure(View view) {
        startActivity(new Intent(User.this,Pressure.class));
    }//ClickPressure

    private void connectedDatabase() {

        objUserTABLE = new UserTABLE(this);
        objDiabetesTABLE = new DiabetesTABLE(this);
        objKidneyTABLE = new KidneyTABLE(this);
        objPressureTABLE = new PressureTABLE(this);


    }//connectedDatabase




}//Main Class
