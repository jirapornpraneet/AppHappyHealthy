package com.jiraporn.nut.happyhealthy;

/**
 * Created by Nut on 15/11/2559.
 */
import android.content.Context;

 import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


 public class MyDatabase extends SQLiteAssetHelper {

 private static final String DATABASE_NAME = "HappyHealthy_Sqlite.db";
 private static final int DATABASE_VERSION = 1;

 public MyDatabase(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);
 }

 }