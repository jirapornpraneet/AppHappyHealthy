package com.jiraporn.nut.happyhealthy;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    //ประกาศตัวแปร ตารางใน database
    UserTABLE objUserTABLE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objUserTABLE = new UserTABLE(this);
//        connectedDatabase();

        /** Pegando id tabhost **/
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        /** กำหนดแต่ละส่วนแท็บ **/
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Home");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Report");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("FoodExe");
        TabHost.TabSpec tab4 = tabHost.newTabSpec("SaveDia");
        TabHost.TabSpec tab5 = tabHost.newTabSpec("User");



        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab1 **/
        tab1.setIndicator("Home");
        tab1.setIndicator("", getResources().getDrawable(R.drawable.ic_home));
        tab1.setContent(new Intent(this, Home.class));



        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab2 **/
        tab2.setIndicator("Report");
        tab2.setIndicator("", getResources().getDrawable(R.drawable.ic_report));
        tab2.setContent(new Intent(this,Report.class));


        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab3 **/
        tab3.setIndicator("AlertHealthy");
        tab3.setIndicator("", getResources().getDrawable(R.drawable.ic_foodexe));
        tab3.setContent(new Intent(this, User.class));


        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab4 **/
        tab4.setIndicator("Intro");
        tab4.setIndicator("", getResources().getDrawable(R.drawable.ic_heart));
        tab4.setContent(new Intent(this, IntroHealthy.class));



        /** ส่วนใหญ่ของแต่ละเรียกใช้หน้าtab5 **/
        tab5.setIndicator("User");
        tab5.setIndicator("", getResources().getDrawable(R.drawable.ic_user));
        tab5.setContent(new Intent(this, DisplayUser.class));





        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);
        tabHost.addTab(tab5);


        //Check Empty Databaseเช็คว่าในแอพเรามีข้อมูลมั้ยถ้าไม่มีให้ไปที่หน้าไไหนถ้ามีไปหน้าไหน เพื่อถ้าไม่มีข้อมูลจะสามารถรันได้ปกติ
        checkUserTABLE();

    }//OnCreate

    private void connectedDatabase() {




    }//connectedDatabase


    private void checkUserTABLE() {
        if (objUserTABLE.checkUserTABLE()==0) {
            Intent objIntent = new Intent(MainActivity.this, DataUser.class);
            startActivity(objIntent);
        }


    }   // checkUserTABLe

    @Override
    protected void onRestart() {
        super.onRestart();

        checkUserTABLE();
        //checkUserHistoryTABLE();

    }

}//MainClass