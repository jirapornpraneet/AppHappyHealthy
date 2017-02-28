package com.jiraporn.nut.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Food_Type_1 extends AppCompatActivity {

    ListView listViewFood1;
    ListAdapter adapterFood1;
    FoodTABLE foodTABLE;
    ArrayList<HashMap<String, String>> foodList;
    String Foodname, FoodUnit, FoodNetUnit,FoodDetail;
    double FoodCalories, FoodNetweight, FoodProtein, FoodFat, FoodCarbohydrate, FoodSugars, FoodSodium;
    int FoodAmount, FoodId;
    Button b_FoodSearch;
    EditText textFood_Search;
    String word_FoodSearch;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__type_1);

        textFood_Search = (EditText) findViewById(R.id.tv_Sfood);
        b_FoodSearch = (Button) findViewById(R.id.bFoodSearch);
        listViewFood1 = (ListView) findViewById(R.id.listViewFood1);

        foodTABLE = new FoodTABLE(this);

        b_FoodSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word_FoodSearch = textFood_Search.getText().toString();
                setListFood(word_FoodSearch);
            }
        });

        listViewFood1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FoodId = Integer.parseInt(foodList.get(i).get("food_id"));
//                Foodname = foodList.get(i).get("food_name").toString();
//                FoodCalories = Double.parseDouble(foodList.get(i).get("food_calories"));
//                FoodAmount = Integer.parseInt(foodList.get(i).get("food_amount"));
//                FoodUnit = foodList.get(i).get("food_unit").toString();
//                FoodNetweight = Double.parseDouble(foodList.get(i).get("food_netweight"));
//                FoodNetUnit = foodList.get(i).get("food_netunit").toString();
//                FoodProtein = Double.parseDouble(foodList.get(i).get("food_protein"));
//                FoodFat = Double.parseDouble(foodList.get(i).get("food_fat"));
//                FoodCarbohydrate = Double.parseDouble(foodList.get(i).get("food_carbohydrate"));
//                FoodSugars = Double.parseDouble(foodList.get(i).get("food_sugars"));
//                FoodSodium = Double.parseDouble(foodList.get(i).get("food_sodium"));
//                FoodDetail = foodList.get(i).get("food_detail").toString();

                //ส่งค่าไปอีกหน้าหนึ่ง putExtra
                Intent intent2;
                intent2 = new Intent(getApplicationContext(), FoodDetail.class);
                intent2.putExtra("food_id", FoodId);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);

            }
        });

        setListFood("");



    }//onCreate


    public void setListFood(String word) {
        foodList = foodTABLE.getFoodList(word);
        adapterFood1 = new SimpleAdapter(Food_Type_1.this, foodList, R.layout.food_1, new String[]{"food_name", "food_calories", "food_unit","food_detail"}, new int[]{R.id.food_name, R.id.food_calories, R.id.food_unit , R.id.food_detail});
        listViewFood1.setAdapter(adapterFood1); //เป็นตัวที่เอาออกมาโชว์หน้าในlist
    }

    public void ClickBackFoodHome(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }//ClickBackHome


    public void ClickAddFood(View view) {
        startActivity(new Intent(Food_Type_1.this, Add_Food.class));
    }//ClickHistoryDiabetes
}//MainClass

