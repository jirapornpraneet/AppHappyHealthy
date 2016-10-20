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

public class Pressure extends AppCompatActivity {

    //การประกาศตัวแปร
    private EditText P_date,P_time,P_costPressureTop,P_costPressureDown;
    private String  dateString,timeString,PressureTopString,PressureDownString ;
    private static final String urlPHP = "http://csnonrmutsb.com/happyhealthy/php_add_pressure.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);

        //Bind wiget
        P_date = (EditText) findViewById(R.id.P_date);
        P_time = (EditText) findViewById(R.id.P_time);
        P_costPressureTop = (EditText) findViewById(R.id.P_costPressureTop);
        P_costPressureDown = (EditText) findViewById(R.id.P_costPressureDown);


    }//Oncreate

    public void ClickDisLevelsPre(View view) {

        //get value edit tezt
        dateString = P_date.getText().toString().trim();
        timeString = P_time.getText().toString().trim();
        PressureTopString = P_costPressureTop.getText().toString().trim();
        PressureDownString = P_costPressureDown.getText().toString().trim();


        //Checkspace
        if (dateString.equals("") || timeString.equals("") || PressureTopString.equals("")|| PressureDownString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ความดัน", "กรุณาใส่ข้อมูลผู้ใช้งานให้ครบค่ะ");


        }else  {
            //Checked
            updateNewPressureToServer();
            startActivity(new Intent(Pressure.this,DisplayUser.class));


        }
    }//Click

    private void updateNewPressureToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("P_date", dateString)
                .add("P_time", timeString)
                .add("P_costPressureTop", PressureTopString)
                .add("P_costPressureDown", PressureDownString)
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
