package com.example.nut.happyhealthy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Food extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        //คลิกเลือกว่ากำหนดจะไปประเภทอาหารไหน
        ImageView imgB_1 = (ImageView) findViewById(R.id.food1);
        ImageView imgB_2 = (ImageView) findViewById(R.id.food2);


        imgB_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Food_Type_1.class);
                intent.putExtra("type_food",1);
                startActivity(intent);
            }
        });

        imgB_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Food_Type_1.class);
                intent.putExtra("type_food",2);
                startActivity(intent);
            }
        });


    }

    public void ClickFoodBackHome(View view) {
        startActivity(new Intent(Food.this, MainActivity.class));
    }//ClickBackHome

    //**public void ClickFoodList(View view) {
    //    startActivity(new Intent(Food.this, Food_Type_1.class));


    //}//ClickFoodList

}//MainClass

