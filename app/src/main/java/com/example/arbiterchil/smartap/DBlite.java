package com.example.arbiterchil.smartap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBlite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Record.db";
    public static final String TABLE_NAME="RecordTable";
    public static final String COL_1="ID";
    public static final String COL_2="FullName";
    public static final String  COL_3="GRADE";





    public DBlite(Context context ){
        super(context,DATABASE_NAME ,null ,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FullName TEXT,GRADE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);


    }
    public  boolean InsertData(String FullName , String Grade){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,FullName);
        contentValues.put(COL_3,Grade);
       long result  =  db.insert(TABLE_NAME , null,contentValues);
       if (result == -1)
           return false;
       else
           return true;

    }
}
