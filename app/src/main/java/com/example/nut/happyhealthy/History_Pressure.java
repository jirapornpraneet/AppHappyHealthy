package com.example.nut.happyhealthy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class History_Pressure extends AppCompatActivity {

    ListView listViewPre;
    ListAdapter adapterPre;
    PressureTABLE pre;
    ArrayList<HashMap<String, String>> preList;
    String DatePre, TimePre,Level_P_Down,Level_P_Top;
    int Cost_Down, Cost_Top;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pressure);

        listViewPre = (ListView) findViewById(R.id.listViewPre);

        pre = new PressureTABLE(this);

        listViewPre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DatePre = preList.get(i).get("datePre").toString();
                TimePre = preList.get(i).get("timePre").toString();
                Cost_Down = Integer.parseInt(preList.get(i).get("cos_down"));
                Cost_Top = Integer.parseInt(preList.get(i).get("cos_top"));
                Level_P_Down = preList.get(i).get("cos_level_down").toString();
                Level_P_Top = preList.get(i).get("cos_level_top").toString();

                AlertHistoryPre();
            }
        });//OnItemClickList

        preList = pre.getPreList();
        adapterPre = new SimpleAdapter(History_Pressure.this, preList, R.layout.history_pressure, new String[]{"datePre"}, new int[]{R.id.datePre});
        listViewPre.setAdapter(adapterPre);

    }//OnCreate

    private void AlertHistoryPre() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle(" วันที่บันทึก : " + DatePre );
        objAlert.setMessage(" เวลาที่บันทึก : " + TimePre + "\n" +
                            " ค่าความดันล่างของผู้ใช้งาน : " + Cost_Down + "\n"+
                            " อยู่ในเกณฑ์ที่ : " + Level_P_Down + "\n"+
                            " ค่าความดันบนของผู้ใช้งาน : " + Cost_Top + "\n"+
                            " อยู่ในเกณฑ์ที่ : " + Level_P_Top );
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();

    }
}//MainClass
