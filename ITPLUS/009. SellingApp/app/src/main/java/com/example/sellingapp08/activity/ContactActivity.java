package com.example.sellingapp08.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.sellingapp08.R;

public class ContactActivity extends AppCompatActivity {
    Toolbar toolbarCT;
    //    Button btnContactBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mapping();
        init();
    }

    private void init() {
        initActionBar();
        initBack();
    }

    private void initBack() {
//        btnContactBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    private void initActionBar() {
        setSupportActionBar(toolbarCT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCT.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbarCT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
        private void mapping(){
//        btnContactBack = findViewById(R.id.btnContactBack);
            toolbarCT = findViewById(R.id.toolbarCT);
        }

}