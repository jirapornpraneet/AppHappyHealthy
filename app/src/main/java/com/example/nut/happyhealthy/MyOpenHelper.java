package com.example.nut.happyhealthy;

/**
 * Created by Nut on 29/10/2559.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String DATABASE_NAME = "DBHappyHealthy.db";
    private static final int DATABASE_VERSION = 1;

    //CreateTableUser
    private static final String USER = "create table  USER (User_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "User_Name text, " +
            "User_Sex text, " +
            "User_Age text, " +
            "User_Height integer, " +
            "User_Weight double, " +
            "User_BMR double, " +
            "User_BMI double);";

    //CreateTableDiabetes
    private static final String Diabetes = "create table Diabetes (D_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "D_Date text, " +
            "D_Time text, " +
            "D_CostSugar integer);";

    //CreateTableBloodSugar_Levels
    private static final String BloodSugar_Levels = "create table BloodSugar_Levels (BS_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "BS_Description text, " +
            "BS_BeforLow integer, " +
            "BS_BeforHigh integer, " +
            "BS_AfterLow integer, " +
            "BS_AfterHigh integer);";

    //CreateTableKidney
    private static final String Kidney = "create table Kidney (K_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "K_Date text, " +
            "K_Time text, " +
            "K_CostGFR integer);";

    //CreateTableKidney_Levels
    private static final String Kidney_Levels = "create table Kidney_Levels (Kidney_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Kidney_Description text, " +
            "Kidney_Low integer, " +
            "Kidney_High integer);";

    //CreateTablePressure
    private static final String Pressure = "create table Pressure (P_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "P_Date text, " +
            "P_Time text, " +
            "P_CostPressureLow integer, " +
            "P_CostPressureHigh integer);";

    //CreateTableBloodPressure_Levels
    private static final String BloodPressure_Levels = "create table BloodPressure_Levels (BP_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "BP_Description text, " +
            "BP_TopLow integer, " +
            "BP_TopHigh integer, " +
            "BP_DownLow integer, " +
            "BP_DownHigh integer);";

    //CreateTableFoodType
    private static final String Food_Type = "create table Food_Type (FoodType_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FoodType_Name text);";

    //CreateTableFood
    private static final String Food = "create table Food (Food_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Food_Name text, " +
            "Food_Calories double, " +
            "Food_Unit text, " +
            "Food_Netweight double, " +
            "Food_Carbohydrate double, " +
            "Food_Protein double, " +
            "Food_Fat double, " +
            "Food_Sugars double, " +
            "Food_Sodium double);";

    //CreateTableExercise_Type
    private static final String Exercise_Type = "create table Exercise_Type (ExerciseType_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ExerciseType_Name text);";


    //CreateTableExercise
    private static final String Exercise = "create table Exercise (Exercise_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Exercise_Name text, " +
            "Exercise_Calories double);";


    //CreateTableExercise_History
    private static final String Exercise_History = "create table Exercise_History (Exercise_History_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Exercise_History_Date text, " +
            "Exercise_History_Name text, " +
            "Exercise_History_Amount text, " +
            "Exercise_History_TotalCal integer);";


    //CreateTableFood_History
    private static final String Food_History = "create table Food_History (Food_History_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Food_History_Date text, " +
            "Food_History_Name text, " +
            "Food_History_Amount text, " +
            "Food_History_TotalCal integer);";


    //CreateTableTime
    private static final String Time = "create table Time (Time_Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Time_Name text, " +
            "Time_Start text, " +
            "Time_Stop text);";


    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {



        /**1**/  db.execSQL(USER);
        /**2**/  db.execSQL(Diabetes);
        /**3**/ db.execSQL(BloodSugar_Levels);
        /**4**/ db.execSQL(Kidney);
        /**5**/ db.execSQL(Kidney_Levels);
        /**6**/ db.execSQL(Pressure);
        /**7**/ db.execSQL(BloodPressure_Levels);
        /**8**/  db.execSQL(Food_Type);
        /**9**/ db.execSQL(Food);
        /**10**/ db.execSQL(Exercise_Type);
        /**11**/ db.execSQL(Exercise);
        /**12**/ db.execSQL(Exercise_History);
        /**13**/ db.execSQL(Food_History);
        /**14**/ db.execSQL(Time);




    }   // onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);

    }   // onUpgrade

}   // Main Class

