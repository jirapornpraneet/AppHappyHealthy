package com.jiraporn.nut.happyhealthy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class History_Food extends AppCompatActivity {

    ListView listViewFoodHis;
    ListAdapter adapterFoodHis;
    FoodHistoryTABLE foodHistoryTABLE;
    ArrayList<HashMap<String, String>> foodHisList;
    //String Foodname, FoodUnit, FoodNetUnit,FoodDetail;
    String[] Choice;
    int HisFoodId;
    Intent intent;
    String choose_D;
    SimpleDateFormat dfm,dfm_insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__food);

        Choice = new String[]{"ลบข้อมูล"};//ใส่ตัวเลือก



        intent = getIntent();
        choose_D = intent.getStringExtra("choose_Date");

        listViewFoodHis = (ListView) findViewById(R.id.listViewFoodHis);
        foodHistoryTABLE = new FoodHistoryTABLE(this);

        listViewFoodHis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HisFoodId = Integer.parseInt(foodHisList.get(i).get(FoodHistoryTABLE.History_Food_Id));

                //AlertHistory();
                AlertDialog.Builder builder = new AlertDialog.Builder(History_Food.this);
                builder.setItems(Choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            android.support.v7.app.AlertDialog.Builder builder_1 = new android.support.v7.app.AlertDialog.Builder(History_Food.this);
                            builder_1.setMessage("คุณต้องการลบหรือไม่  ?");
                            builder_1.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    foodHistoryTABLE.delete(HisFoodId);
                                    Toast.makeText(getApplicationContext(), "ลบข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                                    setListView();
                                }
                            });
                            builder_1.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });

                            builder_1.show();
                        } else {

                        }
                    }
                });//คลิกเพื่อเปลี่ยนหน้า
                builder.show();

            }
        });
        foodHisList = foodHistoryTABLE.getFoodHisList(choose_D);
        adapterFoodHis = new SimpleAdapter(History_Food.this, foodHisList, R.layout.history_food, new String[]{FoodHistoryTABLE.Food_Name,FoodHistoryTABLE.Food_Calories,FoodHistoryTABLE.Food_Unit,FoodHistoryTABLE.Food_Detail,FoodHistoryTABLE.Food_TotalAmount}, new int[]{R.id.his_food_name, R.id.his_food_calories,R.id.his_food_unit,R.id.his_food_detail,R.id.his_amount});
        listViewFoodHis.setAdapter(adapterFoodHis);
    }//OnCreate

    public void ClickBackHisFoodHome(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }//ClickBackHome

    public void setListView() {
        foodHisList = foodHistoryTABLE.getFoodHisList(choose_D);
        adapterFoodHis = new SimpleAdapter(History_Food.this, foodHisList, R.layout.history_food, new String[]{FoodHistoryTABLE.Food_Name,FoodHistoryTABLE.Food_Calories,FoodHistoryTABLE.Food_Unit,FoodHistoryTABLE.Food_Detail,FoodHistoryTABLE.Food_TotalAmount}, new int[]{R.id.his_food_name, R.id.his_food_calories,R.id.his_food_unit,R.id.his_food_detail,R.id.his_amount});
        listViewFoodHis.setAdapter(adapterFoodHis);
        String dun[] = {"asdasd","asdasd"};
    }

}//MainClass
