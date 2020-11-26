package com.nc.chien.listviewnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class AdtGridview extends AppCompatActivity {
    GridView grv;
    List<ShortCut> list;
    AdtShortCut adtShortCut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adt_gridview);
        mapping();
        init();
    }

    private void init() {
        list = new ArrayList<>();
        list.add(new ShortCut(R.drawable.anh,"Hình ảnh"));
        list.add(new ShortCut(R.drawable.caidat,"Cài đặt"));
        list.add(new ShortCut(R.drawable.camera,"Máy ảnh"));
        list.add(new ShortCut(R.drawable.chplay,"CH Play"));
        list.add(new ShortCut(R.drawable.chrome,"Chrome"));
        list.add(new ShortCut(R.drawable.danhba,"Danh bạ"));
        list.add(new ShortCut(R.drawable.fb,"Facebook"));
        list.add(new ShortCut(R.drawable.file,"Quản lý file"));
        list.add(new ShortCut(R.drawable.g,"Gapo"));
        list.add(new ShortCut(R.drawable.goi,"Cuộc gọi"));
        list.add(new ShortCut(R.drawable.itune,"Itune"));
        list.add(new ShortCut(R.drawable.lich,"Lịch"));
        list.add(new ShortCut(R.drawable.mess,"Tin nhắn"));
        list.add(new ShortCut(R.drawable.messenger,"Messenger"));
        list.add(new ShortCut(R.drawable.zalo,"Zalo"));
        list.add(new ShortCut(R.drawable.youtube,"Youtube"));
        list.add(new ShortCut(R.drawable.thoitiet,"Thời tiết"));
        list.add(new ShortCut(R.drawable.morizal,"Mozilla"));
        list.add(new ShortCut(R.drawable.viettelpay,"ViettelPay"));
        adtShortCut = new AdtShortCut(AdtGridview.this,R.layout.activity_shortcut,list);
        grv.setAdapter(adtShortCut);

    }

    private void mapping() {
        grv = findViewById(R.id.grv);
    }

}