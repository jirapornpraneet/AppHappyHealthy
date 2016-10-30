package com.example.nut.happyhealthy;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity{

    //ประกาศตัวแปร ตารางใน database
    private UserTABLE objUserTABLE;
    private DiabetesTABLE objdiabetesTABLE;
    private BloodSugarTABLE objbloodSugarTABLE;
    private KidneyTABLE objkidneyTABLE;
    private KidneyLevelsTABLE objkidneyLevelsTABLE;
    private PressureTABLE objpressureTABLE;
    private BloodPressureTABLE objbloodPressureTABLE;
    private FoodTypeTABLE objfoodTypeTABLE;
    private FoodTABLE objfoodTABLE;
    private ExerciseTypeTABLE objexerciseTypeTABLE;
    private ExerciseTABLE objexerciseTABLE;
    private ExerciseHistoryTABLE objexerciseHistoryTABLE;
    private FoodHistoryTABLE objfoodHistoryTABLE;
    private TimeTABLE objTimeTABLE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectedDatabase();


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

    private void connectedDatabase() {


        objUserTABLE = new UserTABLE(this);
        objdiabetesTABLE = new DiabetesTABLE(this);
        objdiabetesTABLE = new DiabetesTABLE(this);
        objbloodSugarTABLE = new BloodSugarTABLE(this);
        objkidneyTABLE = new KidneyTABLE(this);
        objkidneyLevelsTABLE = new KidneyLevelsTABLE(this);
        objpressureTABLE = new PressureTABLE(this);
        objbloodPressureTABLE = new BloodPressureTABLE(this);
        objfoodTypeTABLE = new FoodTypeTABLE(this);
        objfoodTABLE = new FoodTABLE(this);
        objexerciseTypeTABLE = new ExerciseTypeTABLE(this);
        objexerciseTABLE = new ExerciseTABLE(this);
        objexerciseHistoryTABLE = new ExerciseHistoryTABLE(this);
        objfoodHistoryTABLE = new FoodHistoryTABLE(this);
        objTimeTABLE = new TimeTABLE(this);



    }//connectedDatabase

}//MainClass
