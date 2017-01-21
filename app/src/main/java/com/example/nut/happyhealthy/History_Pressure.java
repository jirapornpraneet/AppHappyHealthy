package com.example.nut.happyhealthy;

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

public class History_Pressure extends AppCompatActivity {

    ListView listViewPre;
    ListAdapter adapterPre;
    PressureTABLE pre;
    ArrayList<HashMap<String, String>> preList;
    String DatePre,Level_P_Down,Level_P_Top,Level_Heart;
    int P_id,Cost_Down, Cost_Top,Cost_Heart;
    String[] Choice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pressure);

        Choice = new String[]{"ดูข้อมูล", "ลบข้อมูล"};//ใส่ตัวเลือก

        listViewPre = (ListView) findViewById(R.id.listViewPre);

        pre = new PressureTABLE(this);

        setListView();

        listViewPre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                P_id = Integer.parseInt(preList.get(i).get(PressureTABLE.P_Id));
                DatePre = preList.get(i).get(PressureTABLE.P_DateTime).toString();
                Cost_Down = Integer.parseInt(preList.get(i).get(PressureTABLE.P_CostPressureDown));
                Cost_Top = Integer.parseInt(preList.get(i).get(PressureTABLE.P_CostPressureTop));
                Cost_Heart = Integer.parseInt(preList.get(i).get(PressureTABLE.P_HeartRate));
                Level_P_Down = preList.get(i).get(PressureTABLE.P_Cost_Level_Down).toString();
                Level_P_Top = preList.get(i).get(PressureTABLE.P_Cost_Level_Top).toString();
                Level_Heart = preList.get(i).get(PressureTABLE.P_HeartRate_Level).toString();

                //AlertHistoryPre();
                AlertDialog.Builder builder = new AlertDialog.Builder(History_Pressure.this);
                builder.setTitle(" วันที่บันทึก : "  + DatePre);
                builder.setItems(Choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            AlertHistoryPre();
                        } else if (which == 1) {
                            android.support.v7.app.AlertDialog.Builder builder_1 = new android.support.v7.app.AlertDialog.Builder(History_Pressure.this);
                            builder_1.setMessage("คุณต้องการลบหรือไม่  ?");
                            builder_1.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    pre.delete(P_id);
                                    Toast.makeText(getApplicationContext(), "ลบข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                                    setListView();
                                }
                            });
                            builder_1.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });

                            builder_1.show();
                        }
                    }
                });//คลิกเพื่อเปลี่ยนหน้า
                // สุดท้ายอย่าลืม show() ด้วย
                builder.show();
            }
        });


    }//OnCreate

    private void AlertHistoryPre() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle(" วันที่บันทึก : " + DatePre );
        objAlert.setMessage(" ค่าความดันล่าง : " + Cost_Down + "\n"+
                            " อยู่ในเกณฑ์ที่ : " + Level_P_Down + "\n"+
                            " ค่าความดันบน : " + Cost_Top + "\n"+
                            " อยู่ในเกณฑ์ที่ : " + Level_P_Top + "\n"+
                            " ค่าการเต้นหัวใจ : " + Cost_Heart + "\n" +
                            " อยู่ในเกณฑ์ที่ : " + Level_Heart);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();

    }//AlertHistory

    public void ClickBackHisPreHome(View view) {
        startActivity(new Intent(History_Pressure.this,MainActivity.class));
    }//ClickBackHome


    public void ClickAddPre(View view) {
        startActivity(new Intent(History_Pressure.this,Pressure.class));
    }//ClickAddPre

    public void setListView() {
        preList = pre.getPreList();
        adapterPre = new SimpleAdapter(History_Pressure.this, preList, R.layout.history_pressure, new String[]{PressureTABLE.P_DateTime}, new int[]{R.id.datePre});
        listViewPre.setAdapter(adapterPre);
    }

}//MainClass
