package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBName = "register.db";

    public DBhelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the 'users' table
        db.execSQL("CREATE TABLE users (username TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the 'users' table if it exists
        db.execSQL("DROP TABLE IF EXISTS users");
        // Create new 'users' table
        onCreate(db);
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        db.close(); // Close the database connection
        return result != -1; // Return true if insertion was successful, false otherwise
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        int count = cursor.getCount();
        cursor.close(); // Close the cursor
        db.close(); // Close the database connection
        return count > 0;
    }

    public boolean checkUser(String username, String pwd) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, pwd});
        int count = cursor.getCount();
        cursor.close(); // Close the cursor
        db.close(); // Close the database connection
        return count > 0;
    }
}
