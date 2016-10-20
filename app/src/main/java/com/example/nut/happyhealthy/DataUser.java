package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class DataUser extends AppCompatActivity {


    //การประกาศตัวแปร
    private EditText User_name, User_age, User_weight, User_height;
    private RadioGroup User_sex;
    private RadioButton man, woman;
    private String nameString, ageString, weightString, heightString, sexString;
    private static final String urlPHP = "http://csnonrmutsb.com/happyhealthy/php_add_user.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);


        //Bind wiget
        User_name = (EditText) findViewById(R.id.User_name);
        User_age = (EditText) findViewById(R.id.User_age);
        User_weight = (EditText) findViewById(R.id.User_weight);
        User_height = (EditText) findViewById(R.id.User_height);
        User_sex = (RadioGroup) findViewById(R.id.User_sex);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);

        //Radio Controller
        User_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {

                    case R.id.man:
                        sexString = "0";
                        break;
                    case R.id.woman:
                        sexString = "1";
                        break;
                }


            }
        });


    }//main method

    public void ClickDisPlay(View view) {

        //get value edit tezt
        nameString = User_name.getText().toString().trim();
        ageString = User_age.getText().toString().trim();
        weightString = User_weight.getText().toString().trim();
        heightString = User_height.getText().toString().trim();

        //Checkspace
        if (nameString.equals("") || ageString.equals("") || weightString.equals("") || heightString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ข้อมูลผู้ใช้งาน", "กรุณาใส่ข้อมูลผู้ใช้งานให้ครบค่ะ");


        }else if (checkChoose()) {
            //Checked
            updateNewUserToServer();
            startActivity(new Intent(DataUser.this,DisplayUser.class));

        }else {//UnCheck
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"ยังไม่เลือกเพศ","กรุณาระบุเพศผู้ใช้งาน");


        }//ClickDisPlay
    }

    private void updateNewUserToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd","true")
                .add("User_name", nameString)
                .add("User_age", ageString)
                .add("User_weight", weightString)
                .add("User_height", heightString)
                .add("User_sex", sexString)
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


    private boolean checkChoose() {
        boolean status = true;
        status = man.isChecked() ||
                woman.isChecked();

        return status;
    }


}//main
