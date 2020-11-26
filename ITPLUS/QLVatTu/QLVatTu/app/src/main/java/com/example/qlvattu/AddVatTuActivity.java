package com.example.qlvattu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class AddVatTuActivity extends AppCompatActivity {
    private static final int REQ_CODE_PER_CAM = 123;
    private static final int REQ_CODE_CAM = 124;
    String[] permissions = new String[]{Manifest.permission.CAMERA};
    EditText edtName, edtDesc;
    ImageView imgOpenCam, imgOpenGal, imgPreview;
    Button btnAddToList, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vat_tu);
        mapping();
        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String desc = edtDesc.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgPreview.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                VatTu vatTu = new VatTu();
                vatTu.setName(name);
                vatTu.setDesc(desc);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                vatTu.setAvatar(stream.toByteArray());
                Intent intent = new Intent();
                intent.putExtra("vattu", vatTu);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void openImage(View view){
        switch (view.getId()){
            case R.id.imgOpenCam:
                confirmOpenCamera();
                break;
        }
    }

    private void confirmOpenCamera() {
        if (ContextCompat.checkSelfPermission(AddVatTuActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            openCamera();
        }else{

            ActivityCompat.requestPermissions(AddVatTuActivity.this, permissions, REQ_CODE_PER_CAM);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE_PER_CAM){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_CAM){
            if (resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imgPreview.setImageBitmap(bitmap);
            }
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQ_CODE_CAM);
    }

    private void mapping() {
        edtName = findViewById(R.id.edtName);
        edtDesc = findViewById(R.id.edtDesc);
        imgOpenCam = findViewById(R.id.imgOpenCam);
        imgOpenGal = findViewById(R.id.imgOpenGal);
        imgPreview = findViewById(R.id.imgPreview);
        btnAddToList = findViewById(R.id.btnAddToList);
        btnCancel = findViewById(R.id.btnCancel);
    }
}