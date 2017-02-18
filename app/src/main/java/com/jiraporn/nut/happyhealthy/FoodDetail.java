package com.jiraporn.nut.happyhealthy;

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

public class FoodDetail extends AppCompatActivity {

    FoodTABLE foodTABLE;
    HashMap<String, String> detailFood;
    TextView f_name,f_cal,f_unit,f_netweight,f_netunit,f_pro ,f_fat,f_car,f_sugar,f_sodium,f_amount,f_detail ;
    EditText editCal_Total;
    Button bCal_Total;
    Double total;
    ImageView rec;
    Calendar c;
    SimpleDateFormat df_show;
    FoodHistoryTABLE foodHistoryTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //จำนวนเริ่มต้นการบริโภค
        total = 1.0;
        foodTABLE = new FoodTABLE(this);
        foodHistoryTABLE = new FoodHistoryTABLE(this);

        editCal_Total = (EditText) findViewById(R.id.et_exe2);
        //bCal_Total = (Button) findViewById(R.id.button);

        f_name = (TextView) findViewById(R.id.food_name2);
        f_cal = (TextView) findViewById(R.id.food_cal2);
       // f_amount = (TextView) findViewById(R.id.food_amount2);
        f_unit = (TextView) findViewById(R.id.food_unit2);
        f_netweight = (TextView) findViewById(R.id.food_netweight2);
        f_netunit = (TextView) findViewById(R.id.food_netunit2);
        f_pro = (TextView) findViewById(R.id.food_protein2);
        f_fat = (TextView) findViewById(R.id.food_fat2);
        f_car = (TextView) findViewById(R.id.food_carbohydrate2);
        f_sugar = (TextView) findViewById(R.id.food_sugar2);
        f_sodium = (TextView) findViewById(R.id.food_sodium2);
        f_detail = (TextView) findViewById(R.id.tv_food_detail);

        rec = (ImageView) findViewById(R.id.imageView30);

        //รับค่าแต่ละหน้า Show id
        Intent intent3 = getIntent();
        final int food_id = intent3.getIntExtra("food_id", 0);

        detailFood = foodTABLE.selectDetailByFoodId(food_id);

        SetDetailFood(total);

        //รับค่าปุ่ม
        editCal_Total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = Double.parseDouble(editCal_Total.getText().toString());
                SetDetailFood(total);
            }
        });

        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                df_show = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                c = Calendar.getInstance();
                foodHistoryTABLE.HisDate = df_show.format(c.getTime());
                foodHistoryTABLE.FoodId = food_id;
                foodHistoryTABLE.FoodAmount = Double.valueOf(editCal_Total.getText().toString());
                foodHistoryTABLE.addFoodHis(foodHistoryTABLE);


                Intent intent = new Intent(getApplicationContext(),Report.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

    }//OnCreate


    //setข้อมูลอาหารให้ออกมาตามรับค่าที่เรากำหนด
    public void SetDetailFood(Double t) {
        
        f_name.setText(detailFood.get("food_name"));
        f_cal.setText(String.format("%.2f",(Double.parseDouble(detailFood.get("food_calories"))* t) ));
       // f_amount.setText(String.format("%.0f",(t) ));
        f_unit.setText(detailFood.get("food_unit"));
        f_netweight.setText(String.format("%.2f",(Double.parseDouble(detailFood.get("food_netweight"))*t)));
        f_netunit.setText(detailFood.get("food_netunit"));
        f_pro.setText(String.format("%.2f",(Double.parseDouble(detailFood.get("food_protein"))*t)));
        f_fat.setText(String.format("%.2f",(Double.parseDouble(detailFood.get("food_fat"))*t)));
        f_car.setText(String.format("%.2f",(Double.parseDouble(detailFood.get("food_carbohydrate"))*t)));
        f_sugar.setText(String.format("%.2f",(Double.parseDouble(detailFood.get("food_sugars"))*t)));
        f_sodium.setText(String.format("%.2f",(Double.parseDouble(detailFood.get("food_sodium"))*t)));
        f_detail.setText(detailFood.get("food_detail"));

    }//setdetailfood

    public void ClickBackFoodetailHome(View view) {
        startActivity(new Intent(getApplicationContext(),Food_Type_1.class));
    }//ClickBackHome



}//MainClass
