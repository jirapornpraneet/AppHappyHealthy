package com.jiraporn.nut.happyhealthy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Kidney extends AppCompatActivity {


    //การประกาศตัวแปร
    private KidneyTABLE  objkidneyTABLE;
    private EditText K_costGFR;
    private String  str_K_Date,intCostGFR,str_L_cost ;
    private TextView K_date;
    SimpleDateFormat df_show;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidney);





        //Bind widget
        K_date = (TextView) findViewById(R.id.K_date);
        K_costGFR = (EditText) findViewById(R.id.K_costGFR);

        connectDataBase();

        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        c = Calendar.getInstance();
        K_date.setText(df_show.format(c.getTime()));

    }//Oncreate

    private void connectDataBase() {

        objkidneyTABLE = new KidneyTABLE(this);
    }//ConnectDatabase


    public void ClickDisLevelsGFR(View view) {

        //get value edit tezt
        str_K_Date = df_show.format(c.getTime());
        intCostGFR = K_costGFR.getText().toString().trim();


        //Checkspace
        if (str_K_Date.equals("") || intCostGFR.equals("")) {
            showAlert();


        }else  {

            confirmKidney();
            showNotification();



        }
    }//Click

    private void confirmKidney() {

        str_L_cost = findMyLevelCostGFR();


        /** Find BMI
        int  intcostgfr = Integer.parseInt(intCostGFR);


        int IntCostGFR = intcostgfr;**/


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("คุณต้องการบันทึกข้อมูลใช่ไหม?");
        builder.setMessage(" วันที่ : " + str_K_Date+ "\n" +
                " ค่าการทำงานไต : " + intCostGFR + "\n"+
                " อยู่ในเกณฑ์ที่ : " + str_L_cost);
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
                upDataKidneytoSQLite();

            }
        });
        builder.show();
    }//ConfirmKidney

    private String findMyLevelCostGFR() {
        String[] resultStrings = getResources().getStringArray(R.array.my_kidney);
        String myResult = null;
        Integer IntCostGFR = Integer.parseInt(intCostGFR);

        if (IntCostGFR >= 90) {
            myResult = resultStrings[0];
        } else if ((IntCostGFR >=60 ) & (IntCostGFR < 90)) {
            myResult = resultStrings[1];
        } else if ((IntCostGFR >= 30) & (IntCostGFR <60)){
            myResult = resultStrings[2];
        } else if ((IntCostGFR >=15) & (IntCostGFR <30)) {
            myResult = resultStrings[3];
        } else {
            myResult = resultStrings[4];
        }
        return myResult;
    }//findMyLevelCostGFR

    private void upDataKidneytoSQLite() {

        KidneyTABLE objkidneyTABLE = new KidneyTABLE(this);
        long inSertDataUser = objkidneyTABLE.addNewValueToSQLite
                (str_K_Date, Integer.parseInt(intCostGFR),str_L_cost);
        K_date.setText("");
        K_costGFR.setText("");
        Toast.makeText(Kidney.this,"บันทึกข้อมูลเรียบร้อย",Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(getApplicationContext(), DisplayKidney.class);
        objIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(objIntent);
        finish();
    }//UpDateKidneytoSQLite

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

        Intent intent = new Intent(this, DisplayKidney.class);
//        intent.putExtra("str_L_before", str_L_before);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(User.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_alert)
                        .setContentTitle("วันนี้ค่าการทำงานไต")
                        .setContentText( intCostGFR + ":"  + str_L_cost)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);

    }

    public void ClickHistoryKidney(View view) {
        startActivity(new Intent(getApplicationContext(),History_Kidney.class));

    }//ClickHistoryKidney


    public void ClickBackKidHome(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }//ClickBackHome


}//Main class
