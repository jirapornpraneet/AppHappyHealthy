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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DisplayUser extends AppCompatActivity {


    SQLiteDatabase db;
    MyDatabase myDatabase;

    UserTABLE userTABLE;
    User_HistoryTABLE userHisTABLE;
    //Explicit
    private EditText TVName, TVAge, TVWeight, TVHeight;
    private TextView TVBMR, TVBMI, weightStdTextView;
    private String douBmr, douBmi, weightStdString, strChooseGender;
    private RadioButton male, female;
    private RadioGroup User_Gender;
    SimpleDateFormat df_show;
    Calendar c;
    int userId;
    Cursor cursor_data;
    double douBMR;
    double douweight, douheight;
    HashMap<String, String> dataUser, dataHis;

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

    private void bindWidget() {
        TVName = (EditText) findViewById(R.id.tv_Name);
        // TVSex = (EditText) findViewById(R.id.tv_Sex);
        TVAge = (EditText) findViewById(R.id.tv_Age);
        TVWeight = (EditText) findViewById(R.id.tv_Weight);
        TVHeight = (EditText) findViewById(R.id.tv_Height);
        TVBMR = (TextView) findViewById(R.id.tv_BMR);
        TVBMI = (TextView) findViewById(R.id.tv_BMI);
        weightStdTextView = (TextView) findViewById(R.id.weightStdTextView);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        User_Gender = (RadioGroup) findViewById(R.id.User_Gender);

    }//bindWidget

    public void clearView() {
        TVName.setText("");
        User_Gender.clearCheck();
        TVHeight.setText("");
        TVWeight.setText("");
        TVBMI.setText("");
        TVBMR.setText("");
        TVAge.setText("");
        weightStdTextView.setText("");
    }

    private void showView() {
        userTABLE = new UserTABLE(this);
        userHisTABLE = new User_HistoryTABLE(this);
        dataUser = userTABLE.getDataUser();
        dataHis = userHisTABLE.getUserHis();
        if (dataUser.get(UserTABLE.User_Gender).equals("male")) {
            male.setChecked(true);
        } else {
            female.setChecked(true);
        }
        strChooseGender = dataUser.get(UserTABLE.User_Gender);
        //findMyAlertWeight(dataHis.get(User_HistoryTABLE.History_User_BMI));
        weightStdString = findMyAlertWeight(dataHis.get(User_HistoryTABLE.History_User_BMI));

        TVName.setText(dataUser.get(UserTABLE.User_Name));
        //TVSex.setText(strSex);
        TVAge.setText(dataUser.get(UserTABLE.User_Age));
        TVWeight.setText(dataHis.get(User_HistoryTABLE.History_User_Weight));
        TVHeight.setText(dataHis.get(User_HistoryTABLE.History_User_Height));
        TVBMR.setText(dataHis.get(User_HistoryTABLE.History_User_BMR));
        TVBMI.setText(dataHis.get(User_HistoryTABLE.History_User_BMI));
        weightStdTextView.setText(weightStdString);


    } // Show View

    public void chooseSex(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.male:
                if (checked) {
                    strChooseGender = "male";
                }
                break;
            case R.id.female:
                if (checked) {
                    strChooseGender = "female";
                }
                break;
        }
    }

    private String findMyAlertWeight(String douBmi) {
        String[] resultStrings = getResources().getStringArray(R.array.my_alert);
        double douBMI = Double.parseDouble(douBmi);
        Resources res = getResources();
        ImageView imageView = (ImageView) findViewById(R.id.imageView64);

        if (douBMI <= 18.5) {
            imageView.setImageResource(R.drawable.bmi1);
            return resultStrings[0];
        } else if (douBMI < 22.9) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi2));
            return resultStrings[1];
        } else if (douBMI < 24.9) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi3));
            return resultStrings[2];
        } else if (douBMI < 29.9) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi4));
            return resultStrings[3];
        } else {
            imageView.setImageDrawable(res.getDrawable(R.drawable.bmi5));
            return resultStrings[4];
        }
    }

    public void ClickSaveDataUser(View view) {
        TVName.setText(TVName.getText().toString().trim());
        TVAge.setText(TVAge.getText().toString().trim());
        TVWeight.setText(TVWeight.getText().toString().trim());
        TVHeight.setText(TVHeight.getText().toString().trim());
        //strSex = TVSex.getText().toString().trim();

        //Checkspace
        if (TVName.getText().toString().equals("") || TVAge.getText().toString().equals("")
                || TVWeight.getText().toString().equals("") || TVHeight.getText().toString().equals("")
                || strChooseGender.equals("")) {
            showAlert();
        } else {//UnCheck
            confirmData();
            //showView();
            if (dataUser.isEmpty()) {
                InsertUser();
                showView();
            } else {
                if (chkUserData()) {
                    UpdateUser();
                    showView();
                } else {
                    ShowHaveData();
                }
            }

        }//ClickSaveUser
    }

    private void ShowHaveData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("มีข้อมูลผู้ใช้งานอยู่แล้ว ?");
        builder.setMessage("คุณต้องการลบหรือไม่  ?");
        builder.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                userTABLE.deleteUser();
                userHisTABLE.deleteUserHistory();
                Toast.makeText(getApplicationContext(), "ลบข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                clearView();
                dataUser.clear();
            }
        });
        builder.setCancelable(false);
        builder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    } //ShowAlert

    private void confirmData() {
        // Find BMI
        douweight = Double.parseDouble(TVWeight.getText().toString());
        douheight = Double.parseDouble(TVHeight.getText().toString());

        double douBMI = douweight / (Math.pow(douheight / 100, 2));

        if(strChooseGender.equals("male")){
            douBMR = 66 + (13.7 * douweight) + (5 * douheight) - (6.8 * Double.parseDouble(TVAge.getText().toString()));
        }else {
            douBMR = 665 + (9.6 * douweight) + (1.8 * douheight) - (4.7 * Double.parseDouble(TVAge.getText().toString()));
        }

        //  bmiString = Double.toString(douBMI);
        douBmi = String.format("%.2f", douBMI);
        Log.d("25Mar_1", douBmi);
        douBmr = String.format("%.2f", douBMR);


    }//confirmData

    private void UpdateUser() {
        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        c = Calendar.getInstance();
        UserTABLE objUserTABLE = new UserTABLE(this);
        User_HistoryTABLE user_historyTABLE = new User_HistoryTABLE(this);

        objUserTABLE.updateUserTable(TVName.getText().toString()
                , strChooseGender
                , TVAge.getText().toString());

        user_historyTABLE.updateUserHistory(df_show.format(c.getTime())
                , Double.parseDouble(TVWeight.getText().toString())
                , Double.parseDouble(douBmr)
                , Double.parseDouble(douBmi)
                , Integer.parseInt(TVHeight.getText().toString()));

        Toast.makeText(DisplayUser.this, "อัพเดตข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
    }

    private void InsertUser() {
        df_show = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        c = Calendar.getInstance();

        userTABLE.insertUserTable(TVName.getText().toString()
                , strChooseGender
                , TVAge.getText().toString());

        Log.d("24Mar_InsertUser", douBmi);
        userHisTABLE.insertUserHistory(df_show.format(c.getTime())
                , Double.parseDouble(TVWeight.getText().toString())
                , Double.parseDouble(douBmr)
                , Double.parseDouble(douBmi)
                , Integer.parseInt(TVHeight.getText().toString())
                , userId);

        Toast.makeText(DisplayUser.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
    }

    public boolean chkUserData() {
        if (TVName.getText().toString().equals(dataUser.get(UserTABLE.User_Name))
                && strChooseGender.equals(dataUser.get(UserTABLE.User_Gender))) {
            return true;
        } else {
            return false;
        }
    }

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

    public void ClickDeleteDataUser(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("มีข้อมูลผู้ใช้งานอยู่แล้ว");
        builder.setMessage("คุณต้องการลบหรือไม่  ?");
        builder.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dataUser.isEmpty()) {
                    NodataEmpty();
                } else {
                    if (chkUserData()) {
                        userTABLE.deleteUser();
                        userHisTABLE.deleteUserHistory();
                        Toast.makeText(getApplicationContext(), "ลบข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                        clearView();
                        dataUser.clear();
                    } else {
                        ShowHaveData();
                    }
                }

            }
        });
        builder.setCancelable(false);
        builder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.show();

    }

    public void NodataEmpty() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("ไม่มีข้อมูลผู้ใช้");
        builder.setMessage("บันทึกข้อมูลผู้ใช้งานก่อนลบข้อมูล ?");
        builder.setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getApplicationContext(), "ไม่มีข้อมูลผู้ใช้งาน", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.show();

    }

}//MainClass
