package com.jiraporn.nut.happyhealthy;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DisplayUser extends AppCompatActivity {


    SQLiteDatabase db;
    MyDatabase myDatabase;

    private UserTABLE objUserTABLE;
    private User_HistoryTABLE user_historyTABLE;
    //Explicit
    private EditText TVName, TVAge, TVWeight, TVHeight;
    private TextView TVBMR, TVBMI, weightStdTextView;
    private String strName, strAge, intHeight, douWeight, douBmr, douBmi, weightStdString, strSex, strChooseSex,strAct;
    private RadioButton man, women;
    private RadioGroup User_Sex;
    SimpleDateFormat df_show, df_search;
    Calendar c;
    int userId;
    private Spinner myACTSpinner;
    String[] str_Act;
    private ArrayList<String> StrAct = new ArrayList<>();
    int intResultFac = 0;

    RadioGroup choose_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        myDatabase = new MyDatabase(this);
        // Bind Widget
        bindWidget();




        // Show View
        showView();

        CreateSpinner();

        ArrayAdapter<String> adapterAct = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, StrAct);
        myACTSpinner.setAdapter(adapterAct);

        myACTSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str_Act = getResources().getStringArray(R.array.my_act);
                strAct = str_Act[intResultFac = i+1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void CreateSpinner() {
        str_Act = getResources().getStringArray(R.array.my_act);
        for (String value : str_Act) {
            StrAct.add(value);
        }

    }//CreateSpinner

    @Override
    protected void onRestart() {
        super.onRestart();
        showView();
    }


    private void showView() {
        db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT *,max(" + User_HistoryTABLE.History_User_Id + ") " +
                " FROM " + UserTABLE.USER + "," + User_HistoryTABLE.User_History +
                " WHERE " + UserTABLE.USER + "." + UserTABLE.User_Id + " = " + User_HistoryTABLE.User_History + "." + User_HistoryTABLE.User_Id, null);

        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(UserTABLE.User_Id));
                strName = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Name));
                strSex = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Sex));
                strChooseSex = strSex;
                strAge = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Age));
                strAct  = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Act));
                intHeight = cursor.getString(cursor.getColumnIndex(User_HistoryTABLE.History_User_Height));
                douWeight = cursor.getString(cursor.getColumnIndex(User_HistoryTABLE.History_User_Weight));
                douBmr = cursor.getString(cursor.getColumnIndex(User_HistoryTABLE.History_User_BMR));
                douBmi = cursor.getString(cursor.getColumnIndex(User_HistoryTABLE.History_User_BMI));
                weightStdString = findMyAlertWeight(douBmi);
            } while (cursor.moveToNext());
        }
        cursor.close();

        if (strSex.equals("man")) {
            man.setChecked(true);
        } else {
            women.setChecked(true);
        }



        TVName.setText(strName);
        //TVSex.setText(strSex);
        TVAge.setText(strAge);
        TVWeight.setText(douWeight);
        TVHeight.setText(intHeight);
        TVBMR.setText(douBmr);
        TVBMI.setText(douBmi);
        weightStdTextView.setText(weightStdString);



    } // Show View

    private String findMyAlertWeight(String douBmi) {

        String[] resultStrings = getResources().getStringArray(R.array.my_alert);
        String myResult = null;

        double douBMI = Double.parseDouble(douBmi);

        Resources res = getResources();

        ImageView imageView = (ImageView) findViewById(R.id.imageView64);


        if (douBMI <= 18.5) {
            myResult = resultStrings[0];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi1));
        } else if (douBMI < 22.9) {
            myResult = resultStrings[1];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi2));
        } else if (douBMI < 24.9) {
            myResult = resultStrings[2];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi3));
        } else if (douBMI < 29.9) {
            myResult = resultStrings[3];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi4));
        } else {
            myResult = resultStrings[4];
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi5));
        }

        return myResult;

    }

    private void bindWidget() {

        TVName = (EditText) findViewById(R.id.tv_Name);
        // TVSex = (EditText) findViewById(R.id.tv_Sex);
        TVAge = (EditText) findViewById(R.id.tv_Age);
        TVWeight = (EditText) findViewById(R.id.tv_Weight);
        TVHeight = (EditText) findViewById(R.id.tv_Height);
        TVBMR = (TextView) findViewById(R.id.tv_BMR);
        TVBMI = (TextView) findViewById(R.id.tv_BMI);
        weightStdTextView = (TextView) findViewById(R.id.weightStdTextView);
        man = (RadioButton) findViewById(R.id.man);
        women = (RadioButton) findViewById(R.id.woman);
        User_Sex = (RadioGroup) findViewById(R.id.User_Sex);
        myACTSpinner = (Spinner) findViewById(R.id.spinner2);

        User_Sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.man:
                        strChooseSex = "man";
                        break;
                    case R.id.woman:
                        strChooseSex = "woman";
                        break;
                }

            }
        });

    }//bindWidget


    public void ClickSaveDataUser(View view) {


        TVName.setText(TVName.getText().toString().trim());
        TVAge.setText(TVAge.getText().toString().trim());
        TVWeight.setText(TVWeight.getText().toString().trim());
        TVHeight.setText(TVHeight.getText().toString().trim());
        //strSex = TVSex.getText().toString().trim();

        //Checkspace
        if (TVName.getText().toString().equals("")
                || TVAge.getText().toString().equals("")
                || TVWeight.getText().toString().equals("")
                || TVHeight.getText().toString().equals("")
                || strChooseSex.equals("")
                || checkSpinner()) {
            showAlert();
        } else {//UnCheck
            confirmData();

            if (chkUserData()) {
                //ReturnTrue
                if (chkUserHis()) {
                    //ReturnTrue

                } else {
                   insertUserHis(userId);
                    //ReturnFlase chkUserHis
                }
            } else {
                int userId_2 = insertUserData();
                insertUserHis(userId_2);
                //ReturnFlase ChkUserData and chkUserHis

            }

            showView();
        }

    }//ClickSaveUser


    private void confirmData() {
        // Find BMI
        double douweight = Double.parseDouble(TVWeight.getText().toString());
        double douheight = Double.parseDouble(TVHeight.getText().toString());
        double douAge = Double.parseDouble(TVAge.getText().toString());

        double douBMI = douweight / (Math.pow(douheight / 100, 2));
        //  bmiString = Double.toString(douBMI);
        douBmi = String.format("%.2f", douBMI);
        Log.d("cal", "Weight = " + douweight);
        Log.d("cal", "Height = " + douheight);
        Log.d("cal", "BMI = " + douBmi);

        // Find BMR
        double douBMR = 0;
        switch (MaleOrFemale()) {
            case 0: // male
                douBMR = 66 + (13.7 * douweight) + (5 * douheight) - (6.8 * douAge);
                break;
            case 1: // female
                douBMR = 665 + (9.6 * douweight) + (1.8 * douheight) - (4.7 * douAge);
                break;
        } // switch

        switch (intResultFac) {
            case 1:
                douBMR = douBMR * 1.2;
                break;
            case 2:
                douBMR = douBMR * 1.375;
                break;
            case 3:
                douBMR = douBMR * 1.55;
                break;
            case 4:
                douBMR = douBMR * 1.725;
                break;
            case 5:
                douBMR = douBMR * 1.9;
                break;
        }

        douBmr = String.format("%.2f", douBMR);


    }//confirmData


    public boolean chkUserData() {
        if (TVName.getText().toString().equals(strName)
                && TVAge.getText().toString().equals(strAge)
                && strChooseSex.equals(strSex)
                && myACTSpinner.equals(strAct)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean chkUserHis() {
        if (TVWeight.getText().toString().equals(douWeight)
                && TVHeight.getText().toString().equals(intHeight)
                && TVBMR.getText().toString().equals(douBmr)
                && TVBMI.getText().toString().equals(douBmi)) {
            return true;
        } else {
            return false;
        }
    }

    public int insertUserData() {
        UserTABLE objUserTABLE = new UserTABLE(this);
        int idUser = objUserTABLE.addNewInsertToSQLite(TVName.getText().toString()
                , strChooseSex
                , TVAge.getText().toString()
                , strAct);
        return idUser;
    }



    private void insertUserHis(int userId) {
        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        df_search = new SimpleDateFormat("dd/MM/yyyy");
        c = Calendar.getInstance();
        User_HistoryTABLE user_historyTABLE = new User_HistoryTABLE(this);

        int searchHis = user_historyTABLE.checkUserHistoryTABLE(df_search.format(c.getTime()));

        if (searchHis <= 0) {
            user_historyTABLE.insertUserHistory(df_show.format(c.getTime())
                    , Double.parseDouble(TVWeight.getText().toString())
                    , Double.parseDouble(douBmr)
                    , Double.parseDouble(douBmi)
                    , Integer.parseInt(TVHeight.getText().toString())
                    , userId);
        } else {
            user_historyTABLE.updateUserHistory(df_show.format(c.getTime())
                    , Double.parseDouble(TVWeight.getText().toString())
                    , Double.parseDouble(douBmr)
                    , Double.parseDouble(douBmi)
                    , Integer.parseInt(TVHeight.getText().toString())
                    , df_search.format(c.getTime()));
        }

        TVName.setText("");
        TVAge.setText("");
        TVHeight.setText("");
        TVWeight.setText("");
        Toast.makeText(DisplayUser.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();

    }//insertUserHis

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ข้อมูลไม่ครบถ้วน");
        builder.setMessage("กรุณาใส่ข้อมูลให้ครบ");
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    } //ShowAlert



    private int MaleOrFemale() {

        int intResult = 0;
        if (strSex.equals("male")) {
            intResult = 0;
        } else {
            intResult = 1;
        }

        return intResult;
    }

    private boolean checkSpinner() {

        boolean bolSpinner = true;

        if (strAct.equals(str_Act[0])) {

            bolSpinner = true;

        } else {

            bolSpinner = false;

        }

        return bolSpinner;
    }

}//MainClass
