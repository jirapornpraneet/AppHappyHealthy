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

public class History_Kidney extends AppCompatActivity {

    ListView listViewKidney;
    ListAdapter adapterKidney;
    KidneyTABLE kid;
    ArrayList<HashMap<String, String>> kidneyList;
    String DateKidney, TimeKidney;
    int CostGFR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_kidney);

        listViewKidney = (ListView) findViewById(R.id.listViewKidney);

        kid = new KidneyTABLE(this);

        listViewKidney.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DateKidney = kidneyList.get(i).get("dateKidney").toString();
                TimeKidney = kidneyList.get(i).get("timeKidney").toString();
                CostGFR = Integer.parseInt(kidneyList.get(i).get("cos_gfr"));

                AlertHistoryKidney();
            }

        });

        kidneyList = kid.getKidneyList();
        adapterKidney = new SimpleAdapter(History_Kidney.this, kidneyList, R.layout.history_kidney, new String[]{"dateKidney"}, new int[]{R.id.dateKidney});
        listViewKidney.setAdapter(adapterKidney); //เป็นตัวที่เอาออกมาโชว์หน้าในlist

    }


    //เมื่อกดคลิกเข้าไปที่วันที่นั้นจะแสดงข้อมูลที่เราทำการเขียนไว้ใน OnItem
    private void AlertHistoryKidney() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle("วันที่บันทึก : " + DateKidney);
        objAlert.setMessage("เวลาที่บันทึก : " + TimeKidney + "\n"+
                            "ค่าการทำงานไต(GFR)ของผู้ใช้งาน : " + CostGFR);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();

    }//AlertHistoryKidney


}//MainClass
