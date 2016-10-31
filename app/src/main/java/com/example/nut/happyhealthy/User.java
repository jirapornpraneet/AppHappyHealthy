package com.example.nut.happyhealthy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class User extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


    }//OnCreate

    public void ClickDataUser(View view) {
        startActivity(new Intent(User.this,DataUser.class));
    }

    public void ClickDiseaseUser(View view) {
        startActivity(new Intent(User.this,DiseaseUser.class));
    }

}//Main Class
