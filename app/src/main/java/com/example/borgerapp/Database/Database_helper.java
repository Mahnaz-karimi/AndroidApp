package com.example.borgerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_helper extends SQLiteOpenHelper {
    // cratation of database
    public static final String Database_Name= "User.db";

    // cratation of table
    public static final String Table_Name= "User_table";

    // cratation column in table
    public static final String Column_1= "ID";
    public static final String Column_2= "Name";

    public Database_helper(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name + " (" + Column_1 + " INTEGER PRIMARY KEY, " + Column_2 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
    public boolean insertdata (String ID, String Name){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Column_1, ID);
        contentValues.put(Column_2,Name);

        long result =db.insert(Table_Name, null,contentValues);
        return result != -1;
    }
    public Cursor ViewAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + Table_Name, null);

    }
}

