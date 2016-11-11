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

public class History_Diabetes extends AppCompatActivity {

    ListView listView;
    ListAdapter adapter;
    DiabetesTABLE dia;
    ArrayList<HashMap<String, String>> diabList;
    String DateDiabetes, TimeDiabetes,LevelBefore,LevelAfter;  //ประกาศตัวแปรที่จะให้ alertshowเมื่อคลิก
    int Cost1Diabetes, Cost2Diabetes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_diabetes);

        listView = (ListView) findViewById(R.id.listView);

        dia = new DiabetesTABLE(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DateDiabetes = diabList.get(i).get("date").toString();
                TimeDiabetes = diabList.get(i).get("time").toString();
                Cost1Diabetes = Integer.parseInt(diabList.get(i).get("cos_1"));
                Cost2Diabetes = Integer.parseInt(diabList.get(i).get("cos_2"));
                LevelBefore = diabList.get(i).get("cos_L_before").toString();
                LevelAfter = diabList.get(i).get("cos_L_after").toString();


                AlertHistory();

            }
        });

        diabList = dia.getDiabetesList();
        adapter = new SimpleAdapter(History_Diabetes.this, diabList, R.layout.history_diabetes, new String[]{"date"}, new int[]{R.id.date});
        listView.setAdapter(adapter);
    }

    private void AlertHistory() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle(" วันที่บันทึก : " + DateDiabetes );
        objAlert.setMessage(" เวลาที่บันทึก : " + TimeDiabetes + "\n"+
                            " ค่าน้ำตาลก่อนอาหาร : " + Cost1Diabetes+ "\n"+
                            " อยู่ในเกณฑ์ที่ : " +LevelBefore + "\n" +
                            " ค่าน้ำตาลหลังอาหาร : " + Cost2Diabetes+"\n" +
                            " อยู่ในเกณฑ์ที่ : "+ LevelAfter);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();
    }
}

