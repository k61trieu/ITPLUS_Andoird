package com.example.sellingapp08.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.sellingapp08.R;

public class InformationActivity extends AppCompatActivity {
    Toolbar toolbarIn;
    DrawerLayout drawerLayoutIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        mapping();
        init();
    }

    private void init() {
        initBack();
    }

    private void initBack() {
        setSupportActionBar(toolbarIn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarIn.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbarIn.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void mapping() {
        toolbarIn = findViewById(R.id.toolbarIn);
        drawerLayoutIn = findViewById(R.id.drawerLayoutIn);
    }


}