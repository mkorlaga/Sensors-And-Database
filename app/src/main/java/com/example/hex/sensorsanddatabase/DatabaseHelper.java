package com.example.hex.sensorsanddatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Measure.db";
    public static final String TABLE_NAME = "measure_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Angle_1";
    public static final String COL_3 = "Angle_2";
    public static final String COL_4 = "Angle_3";
    public static final String COL_5 = "Lx";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Angle_1 INTEGER,Angle_2 INTEGER,Angle_3 INTEGER, Lx INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Angle_1,String Angle_2,String Angle_3, String Lx){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Angle_1);
        contentValues.put(COL_3,Angle_2);
        contentValues.put(COL_4,Angle_3);
        contentValues.put(COL_5,Lx);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Cursor getAllDate(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }
    public boolean updateData(String id, String Angle_1,String Angle_2,String Angle_3, String Lx){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2,Angle_1);
        contentValues.put(COL_3,Angle_2);
        contentValues.put(COL_4,Angle_3);
        contentValues.put(COL_5,Lx);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
}
