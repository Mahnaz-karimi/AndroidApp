package com.example.borgerapp.Database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MockSQLiteOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mockDatabase;

    public MockSQLiteOpenHelper(Context context) {
        super(context, "mock_db", null, 1);
        // Create a mock SQLiteDatabase instance
        mockDatabase = SQLiteDatabase.create(null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Implement this method if necessary
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement this method if necessary
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        // Return the mock SQLiteDatabase instance
        return mockDatabase;
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        // Return the mock SQLiteDatabase instance
        return mockDatabase;
    }
}
