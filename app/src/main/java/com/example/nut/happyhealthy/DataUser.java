package com.example.nut.happyhealthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DataUser extends AppCompatActivity {

    //Explicit การประกาศตัวแปร
    private EditText name, age, weight, height;
    private RadioGroup selectsex;
    private RadioButton man, woman;
    private String nameString, ageString, weightString, heightString, sexString;
    private static final String urlPHP = "http://csnonrmutsb.com/happyhealthy/php_add_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);


        //Bind Widget
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        selectsex = (RadioGroup) findViewById(R.id.selectsex);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);


    }//OnCreate

    public void ClickDisPlayUser(View view) {

        //get value edit text
        nameString = name.getText().toString().trim();
        ageString = age.getText().toString().trim();
        weightString = weight.getText().toString().trim();
        heightString = height.getText().toString().trim();




    }//ClickDataUser



}//Main Class
