package com.example.nut.happyhealthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_diabetes);

        listView = (ListView) findViewById(R.id.listView);

        dia = new DiabetesTABLE(this);

        diabList = dia.getDiabetesList();
        adapter = new SimpleAdapter(History_Diabetes.this, diabList, R.layout.history_diabetes, new String[]{"id", "date", "time" ,"cos_1","cos_2"}, new int[]{R.id.id, R.id.date,R.id.time,R.id.cos_1,R.id.cos_2});
        listView.setAdapter(adapter);
    }
}
