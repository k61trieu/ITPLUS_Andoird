package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDatabase myDatabase= new MyDatabase(MainActivity.this,"sqlim.sqlite",null,1);
    String create_table="create table sosim(id interger primary key autoincrement, so text, gia text)";
       myDatabase.executeSQL(create_table);
        Toast.makeText(this,"ok Database created",Toast.LENGTH_SHORT).show();
        Cursor cursor= myDatabase.selectData("select * from sosim");
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String so=cursor.getString(1);
            String gia=cursor.getString(2);
            SoSim soSim= new SoSim(id, so, gia);
            Toast.makeText(this,""+soSim,Toast.LENGTH_SHORT).show();
        }
    }
}