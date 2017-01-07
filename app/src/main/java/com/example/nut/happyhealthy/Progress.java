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
        if (view.getId() == R.id.save) {
            EditText etcomusers = (EditText) findViewById(R.id.before);
            double commiteduser = Double.parseDouble(etcomusers.getText().toString());

            EditText ettotcomusers = (EditText) findViewById(R.id.after);
            double totalcommiteduser = Double.parseDouble(ettotcomusers.getText().toString());

            int percent = (int) ((commiteduser / 200) * 100);


            int percent2 = (int) ((totalcommiteduser / 300) * 100);


            ProgressBar myprogressBar = (ProgressBar) findViewById(R.id.myprogressbar);
            ProgressBar myprogressBar2 = (ProgressBar) findViewById(R.id.myprogressbar2);

            Resources res = getResources();
            Resources res2 = getResources();

            Rect bounds = myprogressBar.getProgressDrawable().getBounds();
            Rect bounds2 = myprogressBar2.getProgressDrawable().getBounds();

            if (percent >= 50) {
                myprogressBar.setProgressDrawable(res.getDrawable(R.drawable.greenprogressbar));
            } else {
                myprogressBar.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar));
            }
            myprogressBar.getProgressDrawable().setBounds(bounds);
            myprogressBar.setProgress(percent);


            if (percent2 >= 50) {
                myprogressBar2.setProgressDrawable(res.getDrawable(R.drawable.greenprogressbar));
            } else {
                myprogressBar2.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar));
            }
            myprogressBar2.getProgressDrawable().setBounds(bounds2);
            myprogressBar2.setProgress(percent2);

        }
    }


}
