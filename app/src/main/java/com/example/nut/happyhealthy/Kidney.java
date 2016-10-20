package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class Kidney extends AppCompatActivity {


    //การประกาศตัวแปร
    private EditText K_date,K_time,K_costGFR;
    private String  dateString,timeString,costGFRString ;
    private static final String urlPHP = "http://csnonrmutsb.com/happyhealthy/php_add_kidney.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidney);

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
