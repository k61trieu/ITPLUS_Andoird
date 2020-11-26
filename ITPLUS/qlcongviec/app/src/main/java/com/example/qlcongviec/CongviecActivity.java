package com.example.qlcongviec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CongviecActivity extends AppCompatActivity {
    EditText edtTieuDe,edtNoiDung;
    Button btnThem,btnHuy;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congviec);
        mapping();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    private void add() {
        myDatabase=new MyDatabase(this);
        ContentValues values=new ContentValues();
        String tieude=edtTieuDe.getText().toString();
        values.put("TieuDe",tieude);
        String noidung=edtNoiDung.getText().toString();
        values.put("NoiDung",noidung);
        myDatabase.insert("CONGVIEC",null,values);
        Toast.makeText(this,"Thêm thành công",Toast.LENGTH_LONG).show();
    }

    private void mapping() {
        edtTieuDe=findViewById(R.id.edtTieuDe);
        edtNoiDung=findViewById(R.id.edtNoiDung);
        btnThem=findViewById(R.id.btnThem);
        btnHuy=findViewById(R.id.btnHuy);
    }
}