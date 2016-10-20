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
    private EditText D_date,D_time,D_costSugar;
    private String  dateString,timeString,costSugarString ;
    private static final String urlPHP = "http://csnonrmutsb.com/happyhealthy/php_add_diabetes.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes);


        //Bind wiget
        D_date = (EditText) findViewById(R.id.D_date);
        D_time = (EditText) findViewById(R.id.D_time);
        D_costSugar = (EditText) findViewById(R.id.D_costSugar);


    }////main method

    public void ClickDisLevelsSugar(View view) {

        //get value edit tezt
        dateString = D_date.getText().toString().trim();
        timeString = D_time.getText().toString().trim();
        costSugarString = D_costSugar.getText().toString().trim();


        //Checkspace
        if (dateString.equals("") || timeString.equals("") || costSugarString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "เบาหวาน", "กรุณาใส่ข้อมูลผู้ใช้งานให้ครบค่ะ");


        }else  {
            //Checked
            updateNewDiabetesToServer();
            startActivity(new Intent(Diabetes.this,DisplayUser.class));


        }
    }//Click

    private void updateNewDiabetesToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("D_date", dateString)
                .add("D_time", timeString)
                .add("D_costSugar", costSugarString)
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