package com.example.borgerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_SignUp extends SQLiteOpenHelper {
    public static final String Database_Name= "Register.db";
    public static final String Table_Name= "Register_table";
    public static final String Column_1= "username";
    public static final String Column_2= "password";
    public Database_SignUp(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name + " (" + Column_1 + " TEXT PRIMARY KEY, " + Column_2 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public boolean insertData (String username, String password){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Column_1, username);
        contentValues.put(Column_2,password);

        long result =db.insert(Table_Name, null,contentValues);

        return result != -1;
    }
    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_Name + " WHERE " + Column_1 + "=?", new String[]{username});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_Name + " WHERE " + Column_1 + "=?" + " and " + Column_2 + "=?",  new String[] {username,password});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
}
