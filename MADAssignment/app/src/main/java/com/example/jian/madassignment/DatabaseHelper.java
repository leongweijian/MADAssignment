package com.example.jian.madassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(id text primary key, password text, name text, ic text, email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String id, String password, String name, String ic, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("ic", ic);
        contentValues.put("email", email);
        long ins = db.insert("user", null, contentValues);
        if(ins==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkId(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where id=?" , new String[]{id});
        if(cursor.getCount()>0){
            return false;
        }else{
            return true;
        }
    }
}
