package com.example.nut.happyhealthy;

import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    //ประกาศตัวแปร ตารางใน database
    private UserTABLE objUserTABLE;
    private DiabetesTABLE objdiabetesTABLE;
    private KidneyTABLE objkidneyTABLE;
    private PressureTABLE objpressureTABLE;
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

        //Check Empty Databaseเช็คว่าในแอพเรามีข้อมูลมั้ยถ้าไม่มีให้ไปที่หน้าไไหนถ้ามีไปหน้าไหน เพื่อถ้าไม่มีข้อมูลจะสามารถรันได้ปกติ
        checkUserTABLE();


    }//OnCreate

    private void connectedDatabase() {


        objUserTABLE = new UserTABLE(this);
        objdiabetesTABLE = new DiabetesTABLE(this);
        objdiabetesTABLE = new DiabetesTABLE(this);
        objkidneyTABLE = new KidneyTABLE(this);
        objpressureTABLE = new PressureTABLE(this);
        objfoodTypeTABLE = new FoodTypeTABLE(this);
        objfoodTABLE = new FoodTABLE(this);
        objexerciseTypeTABLE = new ExerciseTypeTABLE(this);
        objexerciseTABLE = new ExerciseTABLE(this);
        objexerciseHistoryTABLE = new ExerciseHistoryTABLE(this);
        objfoodHistoryTABLE = new FoodHistoryTABLE(this);
        objTimeTABLE = new TimeTABLE(this);



    }//connectedDatabase



    private void checkUserTABLE() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
        if (objCursor.getCount() <= 0) {

            Log.d("cal1", "objCursor = null");
            Intent objIntent = new Intent(MainActivity.this, DataUser.class);
            startActivity(objIntent);

        } else {
            objCursor.close();
            Log.d("cal1", "objCursor = Have Data");
        }

    }   // checkUserTABLe

    @Override
    protected void onRestart() {
        super.onRestart();

        checkUserTABLE();

    }

}//MainClass
