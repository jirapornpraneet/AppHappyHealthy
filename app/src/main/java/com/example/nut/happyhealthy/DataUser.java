package com.example.nut.happyhealthy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
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
import java.util.Calendar;

public class DataUser extends AppCompatActivity {


    //การประกาศตัวแปร
    private EditText User_Name, User_BirthDay, User_History_Weight, User_Height;
    private RadioGroup User_Sex;
    private RadioButton man, woman;
    private String nameString, birthdayString, weightString, heightString, sexString ;
    private static final String urlPHP = "http://csnonrmutsb.com/happyhealthy/php_add_user.php";
    private static final String urlPHP2 = "http://csnonrmutsb.com/happyhealthy/php_add_user_history.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);

        //เลือกวันเกิด
        //Use the current date as the default date to the picker
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final EditText txtDate = (EditText) findViewById(R.id.User_BirthDay);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DataUser.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtDate.setText(i2 + "-" + i1 + "-" + i);
                    }
                }, day, month, year);
                datePickerDialog.setTitle("เลือกวันที่");
                datePickerDialog.show();
            }
        });//setdatepicker




        //Bind wiget
        User_Name = (EditText) findViewById(R.id.User_Name);
        User_BirthDay = (EditText) findViewById(R.id.User_BirthDay);
        User_History_Weight = (EditText) findViewById(R.id.User_History_Weight);
        User_Height = (EditText) findViewById(R.id.User_Height);
        User_Sex = (RadioGroup) findViewById(R.id.User_Sex);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);

        //Radio Controller
        User_Sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {

                    case R.id.man:
                        sexString = "man";
                        break;
                    case R.id.woman:
                        sexString = "woman";
                        break;
                }


            }
        });


    }//main method

    public void ClickDisPlay(View view) {

        //get value edit tezt
        nameString = User_Name.getText().toString().trim();
        birthdayString = User_BirthDay.getText().toString().trim();
        weightString = User_History_Weight.getText().toString().trim();
        heightString = User_Height.getText().toString().trim();


        //Checkspace
        if (nameString.equals("") || birthdayString.equals("") || weightString.equals("") || heightString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ข้อมูลผู้ใช้งาน", "กรุณาใส่ข้อมูลผู้ใช้งานให้ครบค่ะ");


        }else if (checkChoose()) {
            //Checked
            updateNewUserToServer();
            updateNewUserHistoryToServer();
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
                .add("User_Name", nameString)
                .add("User_BirthDay", birthdayString)
                .add("User_Height", heightString)
                .add("User_Sex", sexString)
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

    }//updateuser

    private void updateNewUserHistoryToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("User_History_Weight", weightString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP2).post(requestBody).build();
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
    }//updateuserhistory



    private boolean checkChoose() {
        boolean status = true;
        status = man.isChecked() ||
                woman.isChecked();

        return status;
    }



}//main
