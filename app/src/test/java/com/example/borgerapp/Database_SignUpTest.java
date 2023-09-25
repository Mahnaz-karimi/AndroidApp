package com.example.borgerapp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.borgerapp.Database.Database_SignUp;
import com.example.borgerapp.Database.MockSQLiteOpenHelper;

public class Database_SignUpTest {
    private MockSQLiteOpenHelper mockSQLiteOpenHelper;
    private SQLiteDatabase mockDatabase;
    private Database_SignUp dbHelper;

    @BeforeEach
    public void setUp() {
        mockDatabase = mock(SQLiteDatabase.class);
        Context context = mock(Context.class);
        dbHelper = new Database_SignUp(context);
        when(context.openOrCreateDatabase(anyString(), anyInt(), any(), any())).thenReturn(mockDatabase);
        // when(mockDatabase.getWritableDatabase()).thenReturn(mockDatabase);
        // Mock the behavior of getWritableDatabase to also return the mock database
        when(mockDatabase.insert(eq(Database_SignUp.Table_Name), eq(null), any(ContentValues.class))).thenReturn(1L);


    }
    @Test
    public void testDatabaseCreation() {
        dbHelper.onCreate(mockDatabase);

        // Verify that the SQL statement for table creation was executed
        verify(mockDatabase).execSQL("CREATE TABLE " + Database_SignUp.Table_Name +
                " (" + Database_SignUp.Column_1 + " TEXT PRIMARY KEY, " +
                Database_SignUp.Column_2 + " TEXT)");
    }

    @Test
    public void testDatabaseUpgrade() {
        dbHelper.onUpgrade(mockDatabase, 1, 2);

        // Verify that the onUpgrade method correctly drops and recreates the table
        verify(mockDatabase).execSQL("DROP TABLE IF EXISTS " + Database_SignUp.Table_Name);
        verify(mockDatabase).execSQL("CREATE TABLE " + Database_SignUp.Table_Name +
                " (" + Database_SignUp.Column_1 + " TEXT PRIMARY KEY, " +
                Database_SignUp.Column_2 + " TEXT)");
    }

}
