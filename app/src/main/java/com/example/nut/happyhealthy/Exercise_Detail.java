package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class Exercise_Detail extends AppCompatActivity {

    ExerciseTABLE exerciseTABLE;
    HashMap<String,String> detailExe;
    TextView e_name,e_cal,e_duration ;
    EditText editExeCal_Total;
    Button ExeCal_Total;
    Double Exetotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        //จำนวนเริ่มต้นการออกกำลังกาย
        Exetotal = 1.0;
        exerciseTABLE = new ExerciseTABLE(this);

        editExeCal_Total = (EditText) findViewById(R.id.et_exe2);
        ExeCal_Total = (Button) findViewById(R.id.buttonExe2);

        e_name = (TextView) findViewById(R.id.exercise_name);
        e_cal = (TextView) findViewById(R.id.exercise_calories);
        e_duration = (TextView) findViewById(R.id.exercise_duration);

        Intent intent4 = getIntent();
        int exercise_id = intent4.getIntExtra("exercise_id", 0);

        detailExe = exerciseTABLE.selectDetailByExeId(exercise_id);

        SetDetailExe(Exetotal);

        //รับค่าปุม
        ExeCal_Total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exetotal = Double.parseDouble(editExeCal_Total.getText().toString());
                SetDetailExe(Exetotal);
            }
        });


    }//OnCreate

    //setข้อมูลออกกำลังกายให้ออกมาตามรับค่าที่เรากำหนด
    public void SetDetailExe(Double t) {

        e_name.setText(detailExe.get("exercise_name"));
        e_cal.setText(String.format("%.2f",(Double.parseDouble(detailExe.get("exercise_calories"))* t) ));
        // f_amount.setText(String.format("%.0f",(t) ));
        e_duration.setText(detailExe.get("exercise_duration"));
        //e_duration.setText(String.format("%.2f",(Double.parseDouble(detailExe.get("exercise_duration"))*e)));


    }//setdetailfood


}//MainClass
