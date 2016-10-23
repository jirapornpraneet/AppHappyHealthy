package com.example.nut.happyhealthy;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ยินดีต้อนรับ");
        builder.setMessage("กรุณาบอกข้อมูลเกี่ยวกับท่าน" +
                "ก่อนเริ่มต้นใช้งานแอพพลิเคชชั่นนะค่ะ");
        builder.setPositiveButton("OK", null);
        builder.show();

        /** Pegando id tabhost **/
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        /** กำหนดแต่ละส่วนแท็บ **/
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Home");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("User");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("IntroHealthy");


        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab1 **/
        tab1.setIndicator("Home");
        tab1.setIndicator("", getResources().getDrawable(R.drawable.ic_home));
        tab1.setContent(new Intent(this, Home.class));


        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab2 **/
        tab2.setIndicator("User");
        tab2.setIndicator("", getResources().getDrawable(R.drawable.ic_user));
        tab2.setContent(new Intent(this, User.class));

        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab3 **/
        tab3.setIndicator("IntroHealthy");
        tab3.setIndicator("", getResources().getDrawable(R.drawable.ic_healthy));
        tab3.setContent(new Intent(this, IntroHealthy.class));



        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);


    }//OnCreate

}//MainClass
