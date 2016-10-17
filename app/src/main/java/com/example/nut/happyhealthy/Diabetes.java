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

public class Diabetes extends AppCompatActivity {

    //การประกาศตัวแปร
    private EditText UH_date,UH_time, UH_costsugar;
    private double costsugarDouble;
    private int dateInt, timeInt;
    private static final String urlPHP = "http://csnonrmutsb.com/happyhealthy/php_add_user.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes);


        //Bind wiget
        UH_date = (EditText) findViewById(R.id.UH_date);
        UH_time = (EditText) findViewById(R.id.UH_time);
        UH_costsugar = (EditText) findViewById(R.id.UH_costsugar);


    }////main method

    public void ClickDisLevelsSugar(View view) {

        //get value edit tezt
        dateInt = UH_date.getText().toString().trim();
        timeInt = UH_time.getText().toString().trim();
        costsugarDouble = UH_costsugar.getText().toString().trim();

        //updatetoserver
        updateNewUserToServer();
        startActivity(new Intent(Diabetes.this,DisplayUser.class));

    }

    private void updateNewUserToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("UH_date", dateInt)
                .add("UH_time", timeInt)
                .add("UH_costsugar", costsugarDouble)
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

}//Main Class