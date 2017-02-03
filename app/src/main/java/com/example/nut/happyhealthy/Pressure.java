package com.example.nut.happyhealthy;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Pressure extends AppCompatActivity {

    //การประกาศตัวแปร
    private PressureTABLE objpressureTABLE;
    private EditText P_costPressureTop,P_costPressureDown,P_HeartRate;
    private String  str_P_Date,intCostPressureDown,intCostPressureTop,str_LP_cost_down,str_LP_cost_top,intHeart,str_heart;
    private TextView P_date;
    SimpleDateFormat df_show;
    Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);


        //Bind wiget
        P_date = (TextView) findViewById(R.id.P_date);
        P_costPressureTop = (EditText) findViewById(R.id.P_costPressureTop);
        P_costPressureDown = (EditText) findViewById(R.id.P_costPressureDown);
        P_HeartRate = (EditText) findViewById(R.id.P_HeartRate);

        connectDataBase();


        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        c = Calendar.getInstance();
        P_date.setText(df_show.format(c.getTime()));


    }//Oncreate

    private void connectDataBase() {
       objpressureTABLE = new PressureTABLE(this);
    }//ConnectDatabase

    public void ClickDisLevelsPre(View view) {

        //get value edit tezt
        str_P_Date = df_show.format(c.getTime());
        intCostPressureDown = P_costPressureDown.getText().toString().trim();
        intCostPressureTop = P_costPressureTop.getText().toString().trim();
        intHeart = P_HeartRate.getText().toString().trim();


        //Checkspace
        if (str_P_Date.equals("") || intCostPressureDown.equals("")|| intCostPressureTop.equals("") || intHeart.equals("")) {
            showAlert();


        }else  {
            confirmPressure();
            showNotification();

        }
    }//Click

    private void confirmPressure() {

        str_LP_cost_down = findMyLevelPressureTop();
        str_LP_cost_top = findMyLevelPressureTop();
        str_heart = findMyLevelHeart();

        /** Find BMI
        int  intcostpressuredown = Integer.parseInt(intCostPressureDown);
        int  intcostpressuretop = Integer.parseInt(intCostPressureTop);

        int IntCostPressureDown = intcostpressuredown;
        int IntCostPressureTop = intcostpressuretop ;**/

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("คุณต้องการบันทึกข้อมูลใช่ไหม?");
        builder.setMessage(" วันที่  : " + str_P_Date + "\n"
                + " ค่าความดันตัวบน : " + intCostPressureTop + "\n"
                + " ค่าความดันล่าง: " + intCostPressureDown + "\n"
                + " อยู่ในเกณฑ์ที่ : " + findMyLevelPressureTop()+"\n"
                + " อัตราการเต้นหัวใจ : " + intHeart + "\n"
                + " อยู่ในเกณฑ์ที่ : " + str_heart + "\n");

        builder.setCancelable(false);
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                upDataPressuretoSQLite();

            }
        });
        builder.show();
    }//confirmPressure

    private String findMyLevelPressureTop() {
            String[] resultStrings = getResources().getStringArray(R.array.my_pressure);
            String myResult = null;
            Integer IntCostPressureTop = Integer.parseInt(intCostPressureTop);
            Integer IntCostPressureDown = Integer.parseInt(intCostPressureDown);
            int CostTop,CostDown;

        if (IntCostPressureTop >= 180 ) {//ระดับอันตราย
            CostTop = 0;
        } else if (IntCostPressureTop >= 160) {//ระดับสูงมากและอันตราย
            CostTop = 1;
        } else if (IntCostPressureTop >= 140) {//ระดับสูงมาก
            CostTop = 2;
        } else if (IntCostPressureTop >= 130 ) {//ระดับค่อนข้างสูง
            CostTop = 3;
        } else if (IntCostPressureTop >= 120 ) {//ระดับปกติ
            CostTop = 4;
        } else if (IntCostPressureTop >= 90 ) {//ระดับเหมาะสม
            CostTop = 5;
        } else {
            CostTop = 0;
        }


        if (IntCostPressureDown >= 110) {
            CostDown = 0;
        } else if (IntCostPressureDown >= 100) {
            CostDown = 1;
        } else if (IntCostPressureDown >= 90 ) {
            CostDown = 2;
        } else if (IntCostPressureDown >= 85 ) {
            CostDown = 3;
        } else if (IntCostPressureDown >= 80 ) {
            CostDown = 4;
        } else if (IntCostPressureDown >= 60  ) {
            CostDown = 5;
        } else {
            CostDown = 0;
        }


        if (CostTop > CostDown ) {
            myResult = resultStrings[CostDown];
        } else {
            myResult = resultStrings[CostTop];
        }
        return myResult;

    }//findMyLevelPressureTop



    private String findMyLevelHeart() {
        String[] resultStrings = getResources().getStringArray(R.array.my_heartrate);
        String myResult = null;
        Integer IntHeart = Integer.parseInt(intHeart);

        if (IntHeart >=41) {
            myResult = resultStrings[0];
        } else if (IntHeart < 60) {
            myResult = resultStrings[1];
        } else if (IntHeart < 70) {
            myResult = resultStrings[2];
        } else if (IntHeart < 85) {
            myResult = resultStrings[3];
        } else if (IntHeart < 101 ) {
            myResult = resultStrings[4];
        } else {
            myResult = resultStrings[5];
        }

        return myResult;

    }//findMyLevelHeart

    private void upDataPressuretoSQLite() {
        PressureTABLE objpressureTABLE = new PressureTABLE(this);
        long inSertDataUser = objpressureTABLE.addNewValueToSQLite
                (str_P_Date,Integer.parseInt(intCostPressureDown), Integer.parseInt(intCostPressureTop),str_LP_cost_down,str_LP_cost_top,Integer.parseInt(intHeart),str_heart);
        P_date.setText("");
        P_costPressureTop.setText("");
        P_costPressureDown.setText("");
        P_HeartRate.setText("");
        Toast.makeText(Pressure.this,"บันทึกข้อมูลเรียบร้อย",Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(getApplicationContext(), DisplayPressure.class);
        objIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(objIntent);
        finish();
    }// upDataPressuretoSQLit

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ข้อมูลไม่ครบถ้วน");
        builder.setMessage("กรุณาใส่ข้อมูลผู้ใช้งานให้ครบ");
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }//ShowAlert


    public void showNotification() {

        Intent intent = new Intent(this,DisplayPressure.class);
//        intent.putExtra("str_L_before", str_L_before);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(User.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_alert)
                        .setContentTitle("วันนี้ความดันโลหิต")
                        .setContentText( " ความดัน : " +  findMyLevelPressureTop()+ "\n")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);

    }

    public void ClickHisPre(View view) {
        startActivity(new Intent(getApplicationContext(),History_Pressure.class));

    }//ClickHistoryPre

    public void ClickBackRreHome(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }//ClickBackHome




}//Main class
