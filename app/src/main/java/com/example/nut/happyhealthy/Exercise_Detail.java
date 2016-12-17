package com.example.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Exercise_Detail extends AppCompatActivity {

    ExerciseTABLE exerciseTABLE;
    HashMap<String, String> detailExe;
    TextView e_name, e_cal, e_duration,e_detail,e_dis,e_des;
    EditText editExeCal_Total;
    Button ExeCal_Total;
    Double Exetotal;
    ImageView rec;
    Calendar c;
    SimpleDateFormat df_show;
    ExerciseHistoryTABLE exerciseHistoryTABLE;
    int exercise_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        exerciseHistoryTABLE = new ExerciseHistoryTABLE(this);

        //จำนวนเริ่มต้นการออกกำลังกาย
        Exetotal = 1.0;
        exerciseTABLE = new ExerciseTABLE(this);

        editExeCal_Total = (EditText) findViewById(R.id.et_exe2);
        ExeCal_Total = (Button) findViewById(R.id.buttonExe2);

        e_name = (TextView) findViewById(R.id.tv_exercise_name);
        e_cal = (TextView) findViewById(R.id.tv_exercise_calories);
        e_detail = (TextView) findViewById(R.id.tv_exe_detail);
        e_des = (TextView) findViewById(R.id.tv_exe_des);
        e_dis = (TextView) findViewById(R.id.tv_exe_dis);
        //e_duration = (TextView) findViewById(R.id.exercise_duration);

        Intent intent4 = getIntent();
        exercise_id = intent4.getIntExtra("exercise_id", 0);

        detailExe = exerciseTABLE.selectDetailByExeId(exercise_id);

        SetDetailExe(Exetotal);

        rec = (ImageView) findViewById(R.id.recExe);

        //รับค่าปุม
        ExeCal_Total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exetotal = Double.parseDouble(editExeCal_Total.getText().toString());
                SetDetailExe(Exetotal);
            }
        });

        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                df_show = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                c = Calendar.getInstance();
                exerciseHistoryTABLE.HisExeDate = df_show.format(c.getTime());
                exerciseHistoryTABLE.ExeId = exercise_id;
                exerciseHistoryTABLE.ExeDuration = Double.valueOf(editExeCal_Total.getText().toString());
                exerciseHistoryTABLE.addExeHis(exerciseHistoryTABLE);

                Intent intent = new Intent(Exercise_Detail.this, Report.class);
                startActivity(intent);
                finish();

            }
        });


    }//OnCreate

    //setข้อมูลออกกำลังกายให้ออกมาตามรับค่าที่เรากำหนด
    public void SetDetailExe(Double t) {

        e_name.setText(detailExe.get("exercise_name"));
        e_cal.setText(String.format("%.2f", (Double.parseDouble(detailExe.get("exercise_calories")) * t)));
        //e_duration.setText(String.format("%.2f",(Double.parseDouble(detailExe.get("exercise_duration"))* t) ));
        e_detail.setText(detailExe.get("exercise_detail"));
        e_dis.setText(detailExe.get("exercise_disease"));
        e_des.setText(detailExe.get("exercise_description"));


    }//setdetailfood



    public void ClickBackExeDetailHome(View view) {
        startActivity(new Intent(Exercise_Detail.this,ExerciseType.class));
    }//ClickBackHome


}//MainClass
