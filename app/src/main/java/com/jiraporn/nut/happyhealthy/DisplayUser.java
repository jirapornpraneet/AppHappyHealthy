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
    UserTABLE userTABLE;
    User_HistoryTABLE userHis;
    //Explicit
    private EditText TVName, TVAge, TVWeight, TVHeight;
    private TextView TVBMR, TVBMI, weightStdTextView;
    private String strName, strAge, intHeight, douWeight, douBmr, douBmi, weightStdString, strGender, strChooseGender;
    private RadioButton male, female;
    private RadioGroup User_Gender;
    SimpleDateFormat df_show, df_search;
    Calendar c;
    int userId,User_Id,History_User_Id;


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
                strGender = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Gender));
                strChooseGender = strGender;
                strAge = cursor.getString(cursor.getColumnIndex(UserTABLE.User_Age));
                intHeight = cursor.getString(cursor.getColumnIndex(User_HistoryTABLE.History_User_Height));
                douWeight = cursor.getString(cursor.getColumnIndex(User_HistoryTABLE.History_User_Weight));
                douBmr = cursor.getString(cursor.getColumnIndex(User_HistoryTABLE.History_User_BMR));
                douBmi = cursor.getString(cursor.getColumnIndex(User_HistoryTABLE.History_User_BMI));
                weightStdString = findMyAlertWeight(douBmi);
            } while (cursor.moveToNext());
        }
        cursor.close();

        if (strGender.equals("male")) {
            male.setChecked(true);
        } else {
            female.setChecked(true);
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
        male = (RadioButton) findViewById(R.id.male );
        female = (RadioButton) findViewById(R.id.female);
        User_Gender = (RadioGroup) findViewById(R.id.User_Gender);

        User_Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.male:
                        strChooseGender = "male";
                        break;
                    case R.id.female:
                        strChooseGender = "female";
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
                || strChooseGender.equals("")) {
            showAlert();
        } else {//UnCheck
            confirmData();
            showView();


           /** if (chkUserData()) {
                //ReturnTrue
                if (chkUserHis()) {
                    //ReturnTrue

                } else {
                    insertUserHis(userId, "");
                    //ReturnFlase chkUserHis
                }
            } else {
                int userId_2 = insertUserData();
                insertUserHis(userId_2, "");
                //ReturnFlase ChkUserData and chkUserHis
            }

            showView();
        }**/

    }//ClickSaveUser
    }


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


        douBmr = String.format("%.2f", douBMR);
        UpdateUser();


    }//confirmData
    private void UpdateUser(){
        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        c = Calendar.getInstance();
        UserTABLE objUserTABLE = new UserTABLE(this);
        User_HistoryTABLE user_historyTABLE = new User_HistoryTABLE(this);

        objUserTABLE.addNewValueToSQLite(TVName.getText().toString()
                , strChooseGender
                , TVAge.getText().toString());

        user_historyTABLE.updateUserHistory(df_show.format(c.getTime())
                , Double.parseDouble(TVWeight.getText().toString())
                , Double.parseDouble(douBmr)
                , Double.parseDouble(douBmi)
                , Integer.parseInt(TVHeight.getText().toString()));


        TVName.setText("");
        TVAge.setText("");
        TVHeight.setText("");
        TVWeight.setText("");
        Toast.makeText(DisplayUser.this, "บันทึกข้อมูลเรียบร้อย" , Toast.LENGTH_SHORT).show();


    }



    /**public boolean chkUserData() {
        Log.d("checkdatauser", String.valueOf(TVName.getText().toString().equals(strName))
                + " ++++ " + TVAge.getText().toString().equals(strAge)
                + " ++++ " + strChooseGender.equals(strGender));
        if (TVName.getText().toString().equals(strName)
                && TVAge.getText().toString().equals(strAge)
                && strChooseGender.equals(strGender)) {
            return true;
        } else {
            return false;
        }
    }**/

   /** public boolean chkUserHis() {
        if (TVWeight.getText().toString().equals(douWeight)
                && TVHeight.getText().toString().equals(intHeight)
                && TVBMR.getText().toString().equals(douBmr)
                && TVBMI.getText().toString().equals(douBmi)) {
            return true;
        } else {
            return false;
        }
    }**/

    /**public int insertUserData() {
        UserTABLE objUserTABLE = new UserTABLE(this);
        int idUser = objUserTABLE.addNewInsertToSQLite(TVName.getText().toString()
                , strChooseGender
                , TVAge.getText().toString());
        return idUser;
    }**/


    /**private void insertUserHis(int userId, String messInsert) {
        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        df_search = new SimpleDateFormat("dd/MM/yyyy");
        c = Calendar.getInstance();
        User_HistoryTABLE user_historyTABLE = new User_HistoryTABLE(this);

        Cursor searchHis = user_historyTABLE.checkUserHistoryTABLE(df_search.format(c.getTime()));

        if (searchHis.getCount() <= 0) {
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
                    , df_search.format(c.getTime())
                    , searchHis.getInt(searchHis.getColumnIndex(User_HistoryTABLE.History_User_Id)));
        }

        TVName.setText("");
        TVAge.setText("");
        TVHeight.setText("");
        TVWeight.setText("");
        Toast.makeText(DisplayUser.this, "บันทึกข้อมูลเรียบร้อย" + messInsert, Toast.LENGTH_SHORT).show();

    }//insertUserHis**/

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
        if (strGender.equals("male")) {
            intResult = 0;
        } else {
            intResult = 1;
        }

        return intResult;
    }

    public void ClickDeleteData(View view) {

        userTABLE = new UserTABLE(this);
        userHis = new User_HistoryTABLE(this);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("คุณต้องการลบหรือไม่  ?");
        builder.setMessage("คุณต้องการลบหรือไม่  ?");
        builder.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                userTABLE.deleteUser(User_Id);
                userHis.deleteUserHistory(History_User_Id);
                //userTABLE.delete(User_Id);
                TVName.setText("");
                TVAge.setText("");
                TVHeight.setText("");
                TVWeight.setText("");
                TVBMR.setText("");
                TVBMI.setText("");
                weightStdTextView.setText("");

                Toast.makeText(getApplicationContext(), "ลบข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.show();
    }




}//MainClass
