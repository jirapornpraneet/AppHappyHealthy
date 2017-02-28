package com.jiraporn.nut.happyhealthy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Food extends AppCompatActivity {

    private FoodTABLE foodTABLE;
    private EditText f_name,f_cal,f_amount,f_unit,f_netweight,f_protein,f_fat,f_carbohydrate,f_sugar,f_sodium;
    private String str_food_name,int_food_amount,dou_food_cal,str_food_unit,
    dou_food_netweight,dou_protein,dou_fat,dou_carbohydrate,dou_sugar,dou_sodium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__food);
        //Bind wiget
        f_name = (EditText) findViewById(R.id.f_name);
        f_cal = (EditText) findViewById(R.id.f_cal);
        f_amount = (EditText) findViewById(R.id.f_amount);
        f_unit = (EditText) findViewById(R.id.f_unit);
        f_netweight = (EditText) findViewById(R.id.f_netweight);
        f_protein = (EditText) findViewById(R.id.f_protein);
        f_fat = (EditText) findViewById(R.id.f_fat);
        f_carbohydrate = (EditText) findViewById(R.id.f_carbohydrate);
        f_sugar = (EditText) findViewById(R.id.f_sugar);
        f_sodium = (EditText) findViewById(R.id.f_sodium);
        connectDataBase();

    }//OnCreate
    private void connectDataBase() {

        foodTABLE = new FoodTABLE(this);

    }//ConnectDataBase

    public void ClickSaveFood(View view) {

        //get value edit tezt
        str_food_name  = f_name.getText().toString().trim();
        int_food_amount = f_amount.getText().toString().trim();
        dou_food_cal = f_cal.getText().toString().trim();
        str_food_unit= f_unit.getText().toString().trim();
        dou_food_netweight= f_netweight.getText().toString().trim();
        dou_protein= f_protein.getText().toString().trim();
        dou_fat= f_fat.getText().toString().trim();
        dou_carbohydrate= f_carbohydrate.getText().toString().trim();
        dou_sugar= f_sugar.getText().toString().trim();
        dou_sodium= f_sodium.getText().toString().trim();


        //Checkspace
        if (str_food_name.equals("") || int_food_amount.equals("") || dou_food_cal.equals("")) {

            showAlert();



        } else {
            upDataAddFoodtoSQLite();

        }
    }//Click

    private void upDataAddFoodtoSQLite() {

        FoodTABLE foodTABLE = new FoodTABLE(this);
        long inSertDataUser = foodTABLE.addNewValueToSQLite
                ( str_food_name,Integer.parseInt(int_food_amount),Double.parseDouble(dou_food_cal),str_food_unit,
        Double.parseDouble(dou_food_netweight), Double.parseDouble(dou_protein) ,Double.parseDouble(dou_fat) ,
        Double.parseDouble(dou_carbohydrate),Double.parseDouble(dou_sugar), Double.parseDouble(dou_sodium));
        f_name.setText("");
        f_cal.setText("");
        f_amount.setText("");
        f_unit.setText("");
        f_netweight.setText("");
        f_protein.setText("");
        f_fat.setText("");
        f_carbohydrate.setText("");
        f_sugar.setText("");
        f_sodium.setText("");

        Toast.makeText(Add_Food.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
        Intent objIntent = new Intent(getApplicationContext(), Food_Type_1.class);
        objIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(objIntent);
        finish();
    }//upDataDiabetestoSQLite


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ข้อมูลไม่ครบถ้วน");
        builder.setMessage("กรุณาใส่ข้อมูล");
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }//ShowAlert

    public void ClickAddFoodBack(View view) {
        startActivity(new Intent(Add_Food.this, MainActivity.class));
    }//ClickHistoryDiabetes

}//MainClass
