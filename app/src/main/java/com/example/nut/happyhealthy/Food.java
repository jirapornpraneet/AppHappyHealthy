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
        ImageView imgB_3 = (ImageView) findViewById(R.id.food3);
        ImageView imgB_4 = (ImageView) findViewById(R.id.food4);
        ImageView imgB_5 = (ImageView) findViewById(R.id.food5);
        ImageView imgB_6 = (ImageView) findViewById(R.id.food6);



        imgB_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Food_Type_1.class);
                intent.putExtra("type_food",1);
                startActivity(intent);
            }
        });//imgB_1

        imgB_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Food_Type_1.class);
                intent.putExtra("type_food",2);
                startActivity(intent);
            }
        });//imgB_2

        imgB_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Food_Type_1.class);
                intent.putExtra("type_food",3);
                startActivity(intent);

            }
        });//imgB_3

        imgB_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Food_Type_1.class);
                intent.putExtra("type_food",4);
                startActivity(intent);

            }
        });//imgB_4

        imgB_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Food_Type_1.class);
                intent.putExtra("type_food",5);
                startActivity(intent);

            }
        });//imgB_5

        imgB_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Food_Type_1.class);
                intent.putExtra("type_food",6);
                startActivity(intent);

            }
        });//imgB_6


    }

    public void ClickFoodBackHome(View view) {
        startActivity(new Intent(Food.this, MainActivity.class));
    }//ClickBackHome

    //**public void ClickFoodList(View view) {
    //    startActivity(new Intent(Food.this, Food_Type_1.class));


    //}//ClickFoodList

}//MainClass

