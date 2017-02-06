package com.jiraporn.nut.happyhealthy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class History_Exe extends AppCompatActivity {

    ListView listViewExeHis;
    ListAdapter adapterExeHis;
    FoodHistoryTABLE foodHistoryTABLE;
    ArrayList<HashMap<String, String>> exeHisList;
    //String Foodname, FoodUnit, FoodNetUnit,FoodDetail;
    int HisExeId;
    Intent intent;
    String choose_D;
    String[] Choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__exe);

        Choice = new String[]{"ลบข้อมูล"};//ใส่ตัวเลือก

        intent = getIntent();
        choose_D = intent.getStringExtra("choose_Date");

        listViewExeHis = (ListView) findViewById(R.id.listViewExeHis);
        foodHistoryTABLE = new FoodHistoryTABLE(this);


        listViewExeHis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HisExeId = Integer.parseInt(exeHisList.get(i).get(FoodHistoryTABLE.History_Exercise_Id));
                //AlertHistory();
                AlertDialog.Builder builder = new AlertDialog.Builder(History_Exe.this);
                builder.setItems(Choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            android.support.v7.app.AlertDialog.Builder builder_1 = new android.support.v7.app.AlertDialog.Builder(History_Exe.this);
                            builder_1.setMessage("คุณต้องการลบหรือไม่  ?");
                            builder_1.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    foodHistoryTABLE.delete2(HisExeId);
                                    Toast.makeText(getApplicationContext(), "ลบข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                                    setListView();
                                }
                            });
                            builder_1.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });

                            builder_1.show();
                        } else {

                        }
                    }
                });//คลิกเพื่อเปลี่ยนหน้า
                builder.show();

            }
        });

        exeHisList = foodHistoryTABLE.getExeHisList(choose_D);
        adapterExeHis = new SimpleAdapter(History_Exe.this, exeHisList, R.layout.history_exe, new String[]{FoodHistoryTABLE.Exercise_Name,FoodHistoryTABLE.Exercise_Calories,FoodHistoryTABLE.Exercise_Disease,FoodHistoryTABLE.Exercise_TotalDuration}, new int[]{R.id.his_exercise_name, R.id.his_exercise_calories,R.id.his_exercise_disease,R.id.his_totalexe});
        listViewExeHis.setAdapter(adapterExeHis);
    }//OnCreate

    public void ClickBackHisExeHome(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }//ClickBackHome

    public void setListView() {
        exeHisList = foodHistoryTABLE.getExeHisList(choose_D);
        adapterExeHis = new SimpleAdapter(History_Exe.this, exeHisList, R.layout.history_exe, new String[]{FoodHistoryTABLE.Exercise_Name,FoodHistoryTABLE.Exercise_Calories,FoodHistoryTABLE.Exercise_Disease,FoodHistoryTABLE.Exercise_TotalDuration}, new int[]{R.id.his_exercise_name, R.id.his_exercise_calories,R.id.his_exercise_disease,R.id.his_totalexe});
        listViewExeHis.setAdapter(adapterExeHis);
    }

}//MainClass
