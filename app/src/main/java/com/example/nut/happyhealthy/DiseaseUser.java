package com.example.nut.happyhealthy;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;


@SuppressWarnings("deprecation")
public class DiseaseUser extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_user);

        /** Pegando id tabhost **/
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        /** กำหนดแต่ละส่วนแท็บ **/
        TabHost.TabSpec tab5 = tabHost.newTabSpec("Diabetes");
        TabHost.TabSpec tab6 = tabHost.newTabSpec("Kidney");
        TabHost.TabSpec tab7 = tabHost.newTabSpec("Pressure");

        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab5 **/
        tab5.setIndicator("Diabetes");
        //tab5.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher));
        tab5.setContent(new Intent(this, Diabetes.class));

        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab6 **/
        tab6.setIndicator("Kidney");
        //tab6.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher));
        tab6.setContent(new Intent(this, Kidney.class));

        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab7 **/
        tab7.setIndicator("Pressure");
        //tab7.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher));
        tab7.setContent(new Intent(this, Pressure.class));



        tabHost.addTab(tab5);
        tabHost.addTab(tab6);
        tabHost.addTab(tab7);



    }//OnCreate


}//MainClass
