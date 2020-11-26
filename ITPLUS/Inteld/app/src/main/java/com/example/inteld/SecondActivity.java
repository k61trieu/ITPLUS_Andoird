package com.example.inteld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhthu2);
        btnBack =findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        startActivityForResult(intent, REQ_CODE);
        /*Intent intent= getIntent();
        String hoTen=intent.getStringExtra("hoTen");
        int tuoi = intent.getIntExtra("tuoi",0);
        float diem = intent.getFloatExtra("diem", 0);
        List<String> soThich=(List<String>)intent.getSerializableExtra("soThich");
        Toast.makeText(this,"hoTen= "+hoTen,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"tuoi= "+tuoi,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"diem= "+diem,Toast.LENGTH_SHORT).show();
        for(String st:soThich){
            Toast.makeText(this,"so thich"+st,Toast.LENGTH_SHORT).show();*/
        Intent intent= getIntent();
        Student student=(Student) intent.getSerializableExtra("student");
        Toast.makeText(this, ""+student, Toast.LENGTH_SHORT).show();







        }



    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent1);

    }

}

