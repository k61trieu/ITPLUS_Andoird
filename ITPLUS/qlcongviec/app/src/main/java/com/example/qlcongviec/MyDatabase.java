package com.example.qlcongviec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION=1;
    public static final String DB_NAME="qlcongviec.sql";
    public static final String DB_TABLENAME="CONGVIEC";
    public MyDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public MyDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public long insert(String table, String nullColumnHack, ContentValues values){
        SQLiteDatabase database=getWritableDatabase();
        return database.insert(table,nullColumnHack,values);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs){
        SQLiteDatabase database=getWritableDatabase();
        return database.update(table,values,whereClause,whereArgs);
    }

    public int delete(String table, String whereClause, String[] whereArgs){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(table, whereClause, whereArgs);
    }

    public void executeSQL(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor selectData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor= database.rawQuery(sql, null);
        return cursor;
    }

    public Cursor selectData(String sql, String[] selectionArgs){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor= database.rawQuery(sql, selectionArgs);
        return cursor;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
