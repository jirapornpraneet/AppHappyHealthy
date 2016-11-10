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
        adapterKidney = new SimpleAdapter(History_Kidney.this, kidneyList, R.layout.history_kidney, new String[]{"dateKidney", "timeKidney"}, new int[]{R.id.dateKidney, R.id.timeKidney});
        listViewKidney.setAdapter(adapterKidney);

    }

    private void AlertHistoryKidney() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle("You Click " + DateKidney + " Time " + TimeKidney);
        objAlert.setMessage("Your Weight = " + "CostSugar " + CostGFR);
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
