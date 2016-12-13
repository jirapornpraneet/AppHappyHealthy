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

public class History_Diabetes extends AppCompatActivity {

    ListView listView;
    ListAdapter adapter;
    DiabetesTABLE dia;
    ArrayList<HashMap<String, String>> diabList;
    String[] Choice;
    String DateDiabetes, LevelBefore, LevelAfter;  //ประกาศตัวแปรที่จะให้ alertshowเมื่อคลิก
    int D_id, Cost1Diabetes, Cost2Diabetes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_diabetes);

        Choice = new String[]{"ดูข้อมูล", "ลบข้อมูล"};//ใส่ตัวเลือก

        listView = (ListView) findViewById(R.id.listView);

        dia = new DiabetesTABLE(this);

        setListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                D_id = Integer.parseInt(diabList.get(i).get(DiabetesTABLE.D_Id));
                DateDiabetes = diabList.get(i).get(DiabetesTABLE.D_DateTime).toString();
                Cost1Diabetes = Integer.parseInt(diabList.get(i).get(DiabetesTABLE.D_CostSugarBefore));
                Cost2Diabetes = Integer.parseInt(diabList.get(i).get(DiabetesTABLE.D_CostSugarAfter));
                LevelBefore = diabList.get(i).get(DiabetesTABLE.D_LevelCostBefore).toString();
                LevelAfter = diabList.get(i).get(DiabetesTABLE.D_LevelCostAfter).toString();


                //AlertHistory();
                AlertDialog.Builder builder = new AlertDialog.Builder(History_Diabetes.this);
                builder.setTitle(" วันที่บันทึก : "  + DateDiabetes);
                builder.setItems(Choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            AlertHistory();
                        } else if (which == 1) {
                            android.support.v7.app.AlertDialog.Builder builder_1 = new android.support.v7.app.AlertDialog.Builder(History_Diabetes.this);
                            builder_1.setMessage("คุณต้องการลบหรือไม่  ?");
                            builder_1.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dia.delete(D_id);
                                    Toast.makeText(getApplicationContext(), "Date . " + D_id + " is Deleted Success", Toast.LENGTH_SHORT).show();
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


    }

    private void AlertHistory() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle(" วันที่บันทึก : " + DateDiabetes);
        objAlert.setMessage(" ค่าน้ำตาลก่อนอาหาร : " + Cost1Diabetes + "\n" +
                " อยู่ในเกณฑ์ที่ : " + LevelBefore + "\n" +
                " ค่าน้ำตาลหลังอาหาร : " + Cost2Diabetes + "\n" +
                " อยู่ในเกณฑ์ที่ : " + LevelAfter);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();
    }//AlertHistory

    public void ClickBackHisDiaHome(View view) {
        startActivity(new Intent(History_Diabetes.this, MainActivity.class));
    }//ClickBackHome

    public void ClickAddDia(View view) {
        startActivity(new Intent(History_Diabetes.this, Diabetes.class));
    }//ClickAddDia

    public void setListView() {
        diabList = dia.getDiabetesList();
        adapter = new SimpleAdapter(History_Diabetes.this, diabList, R.layout.history_diabetes, new String[] {DiabetesTABLE.D_DateTime}, new int[]{R.id.date});
        listView.setAdapter(adapter);
    }

}//MainClass

