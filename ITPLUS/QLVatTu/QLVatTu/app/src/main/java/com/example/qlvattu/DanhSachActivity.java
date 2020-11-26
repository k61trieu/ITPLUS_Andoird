package com.example.qlvattu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DanhSachActivity extends AppCompatActivity {
    private static final int REQ_CODE_ADD = 123;
    ListView lvDanhSach;
    VatTuAdapter adapter;
    List<VatTu> list;
    Button btnThemVatTu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);
        mapping();
        init();
        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(position);
                return false;
            }
        });
    }
    public void themVatTu(View view){
        //showPopupMenu();
        //startActivityForResult(new Intent(DanhSachActivity.this, AddVatTuActivity.class), REQ_CODE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_ADD){
            if (resultCode == RESULT_OK){
                VatTu vatTu = (VatTu) data.getSerializableExtra("vattu");
                list.add(vatTu);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void init() {
        list = new ArrayList<>();
        //list.add(new VatTu("Máy tính", "Mô tả máy tính", ));
        adapter = new VatTuAdapter(DanhSachActivity.this, R.layout.activity_line_vat_tu, list);
        lvDanhSach.setAdapter(adapter);
    }

    private void mapping() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
        btnThemVatTu = findViewById(R.id.btnAdd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuShowAddVatTu){
            startActivityForResult(new Intent(DanhSachActivity.this, AddVatTuActivity.class), REQ_CODE_ADD);
        }else if(item.getItemId() == R.id.mnuLogin){
            Dialog dialog = new Dialog(DanhSachActivity.this);
            dialog.setContentView(R.layout.dialog_login);
            final EditText txtvUserName = dialog.findViewById(R.id.txtvUserName);
            EditText txtvPassWord = dialog.findViewById(R.id.txtvPassWord);
            Button btnLogin = dialog.findViewById(R.id.btnLogin);
            Button btnHuy = dialog.findViewById(R.id.btnHuy);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String user = txtvUserName.getText().toString();
                    Toast.makeText(DanhSachActivity.this, ""+user, Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void showPopupMenu(final int position){
        PopupMenu popupMenu = new PopupMenu(DanhSachActivity.this, btnThemVatTu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pop_up, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.mnuXoa){
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        popupMenu.show();
    }
}