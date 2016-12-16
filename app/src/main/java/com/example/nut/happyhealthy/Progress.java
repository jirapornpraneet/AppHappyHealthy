package com.example.nut.happyhealthy;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Progress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
    }


    public void Calculate(View view) {
        if (view.getId() == R.id.btncalc) {
            EditText etcomusers = (EditText) findViewById(R.id.comusersnumber);
            double commiteduser = Double.parseDouble(etcomusers.getText().toString());

            EditText ettotcomusers = (EditText) findViewById(R.id.totalcomusersnumber);
            double totalcommiteduser = Double.parseDouble(ettotcomusers.getText().toString());

            int percent = (int) ((commiteduser / totalcommiteduser) * 100);

            ProgressBar myprogressBar = (ProgressBar) findViewById(R.id.myprogressbar);

            Resources res = getResources();

            Rect bounds = myprogressBar.getProgressDrawable().getBounds();

            if (percent >= 50) {
                myprogressBar.setProgressDrawable(res.getDrawable(R.drawable.greenprogressbar));
            } else {
                myprogressBar.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar));
            }
            myprogressBar.getProgressDrawable().setBounds(bounds);
            myprogressBar.setProgress(percent);




        }
    }


}
