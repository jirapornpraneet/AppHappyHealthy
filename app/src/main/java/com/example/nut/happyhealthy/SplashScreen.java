package com.example.nut.happyhealthy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;

public class SplashScreen extends AppCompatActivity {
    AnimationDrawable splash;

    private Handler objHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        objHandler = new Handler();
        objHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent objIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(objIntent);
                finish();

            }
        }, 3000);

        ImageView image = (ImageView) findViewById(R.id.imageView9);
        splash = (AnimationDrawable) image.getBackground();
        image.post(new Starter());


        ImageView imageView = (ImageView) findViewById(R.id.imageView10);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        imageView.setAnimation(anim);

        objHandler = new Handler();
        objHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent objIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(objIntent);
                finish();

            }
        }, 3000);

    }//oncreate

    class Starter implements Runnable {
        public void run() {
            splash.start();
        }
    }
}//MainClass
