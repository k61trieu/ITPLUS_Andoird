package com.example.sqllite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
public void executeSQL(String sql){
        SQLiteDatabase database =getWritableDatabase();
        database.execSQL(sql);

}
public Cursor selectData(String sqlSelect){
        SQLiteDatabase database= getReadableDatabase();
        Cursor cursor = database.selectData() ;
}


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
