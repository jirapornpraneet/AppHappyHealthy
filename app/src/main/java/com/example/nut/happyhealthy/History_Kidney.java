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
import java.util.ArrayList;
import java.util.HashMap;

public class History_Kidney extends AppCompatActivity {

    ListView listViewKidney;
    ListAdapter adapterKidney;
    KidneyTABLE kid;
    ArrayList<HashMap<String, String>> kidneyList;
    String[] Choice;
    String DateKidney,LevelCostGFR;
    int K_id,CostGFR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_kidney);

        Choice = new String[]{"Open", "Delete"};//ใส่ตัวเลือก

        listViewKidney = (ListView) findViewById(R.id.listViewKidney);

        kid = new KidneyTABLE(this);

        listViewKidney.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                K_id = Integer.parseInt(kidneyList.get(i).get(KidneyTABLE.K_Id));
                DateKidney = kidneyList.get(i).get(KidneyTABLE.K_DateTime).toString();
                CostGFR = Integer.parseInt(kidneyList.get(i).get(KidneyTABLE.K_CostGFR));
                LevelCostGFR = kidneyList.get(i).get(KidneyTABLE.K_LevelCostGFR).toString();


                //AlertHistoryKidney();
            }

        });

        kidneyList = kid.getKidneyList();
        adapterKidney = new SimpleAdapter(History_Kidney.this, kidneyList, R.layout.history_kidney, new String[]{"dateKidney"}, new int[]{R.id.dateKidney});
        listViewKidney.setAdapter(adapterKidney); //เป็นตัวที่เอาออกมาโชว์หน้าในlist

    }


    //เมื่อกดคลิกเข้าไปที่วันที่นั้นจะแสดงข้อมูลที่เราทำการเขียนไว้ใน OnItem
    private void AlertHistoryKidney() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle(" วันที่บันทึก : " + DateKidney);
        objAlert.setMessage(" ค่าการทำงานไต(GFR) : " + CostGFR +"\n"+
                            " อยู่ในเกณฑ์ที่ : " + LevelCostGFR);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();
    }//AlertHistoryKidney

    public void ClickBackHisKidHome(View view) {
        startActivity(new Intent(History_Kidney.this,MainActivity.class));
    }//ClickBackHome

    public void ClickAddKid(View view) {
        startActivity(new Intent(History_Kidney.this,Kidney.class));
    }//ClickAddDia


}//MainClass
