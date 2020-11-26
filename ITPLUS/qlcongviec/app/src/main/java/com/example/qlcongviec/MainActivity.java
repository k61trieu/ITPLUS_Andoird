package com.example.qlcongviec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvDSCongViec;
    List<String> list;
    AdapterCongViec adapter;
    MyDatabase myDatabase;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }

     private void init() {
        list=new ArrayList<String>();
        list.clear();
        myDatabase=new MyDatabase(this);
        String create_table = "CREATE TABLE if not exists CONGVIEC(TieuDe String, NoiDung String)";
        myDatabase.executeSQL(create_table);
        cursor=myDatabase.selectData("Select*from "+MyDatabase.DB_TABLENAME,null);
        while (cursor.moveToNext()) {
            String tieude = cursor.getString(0);
            list.add(tieude);
        }
        adapter=new AdapterCongViec(MainActivity.this,R.layout.activity_line_congviec, list);
        lvDSCongViec.setAdapter(adapter);
    }

    private void mapping() {
        lvDSCongViec=findViewById(R.id.lvDSCongViec);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_add){
            init();
            Intent intent=new Intent(MainActivity.this,CongviecActivity.class);
            startActivity(intent);
        }
        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }
}