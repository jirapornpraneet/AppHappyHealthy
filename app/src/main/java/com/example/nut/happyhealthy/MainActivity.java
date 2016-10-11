package com.example.nut.happyhealthy;

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

        /** Pegando id tabhost **/
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        /** กำหนดแต่ละส่วนแท็บ **/
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Home");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Food");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Exercise");
        TabHost.TabSpec tab4 = tabHost.newTabSpec("IntroHealthy");

        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab1 **/
        tab1.setIndicator("Home");
        tab1.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher));
        tab1.setContent(new Intent(this, Home.class));

         /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab2 **/
        tab2.setIndicator("Food");
        //tab2.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher));
        tab2.setContent(new Intent(this, Food.class));

        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab3 **/
        tab3.setIndicator("Exercise");
        //tab3.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher));
        tab3.setContent(new Intent(this, Exercise.class));

        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab4 **/
        tab4.setIndicator("Healthy");
        //tab4.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher));
        tab4.setContent(new Intent(this, IntroHealthy.class));


        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);

    }//OnCreate

}//MainClass
