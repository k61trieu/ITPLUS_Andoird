package com.example.inteld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button btnGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle= getIntent().getBundleExtra("thungHang");

        btnGo =findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this); {

        };
    {

    }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        String hoten ="tuc anh anh";
        int tuoi=20;
        float diem=8;
        List<String> sothich = new ArrayList<>();
        sothich.add("Bong da");
        sothich.add("Xem phim");
        sothich.add("Choi game");
        Student student= new Student();
        student.setName(hoten);
        student.setAge(tuoi);
        student.setScore(diem);
        student.setHobby(sothich);
        //intent.putExtra("hoten",hoten);
        //intent.putExtra("tuoi", tuoi);
        //intent.putExtra("diem", diem);
        //intent.putExtra("So thich",(Serializable))
        intent.putExtra("student",student);
        Bundle bundle= new Bundle();
        bundle.putString("hoten",hoten);
        bundle.putInt("tuoi" , tuoi);
        bundle.putFloat("diem",diem);
        bundle.putSerializable("sothich", (Serializable)sothich);


        startActivity(intent);
    }
}