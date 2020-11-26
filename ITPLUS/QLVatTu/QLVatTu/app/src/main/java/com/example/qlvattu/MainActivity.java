package com.example.qlvattu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQ_CAM = 123;
    private static final int REQ_CAM_PER = 124;
    private static final int REQ_GAL_PER = 125;
    private static final int REQ_GAL = 126;
    ImageView imgCamera, imgGallery, imgReceiver, imgGetAnh;
    Button btnGetImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        imgCamera.setOnClickListener(this);
        imgGallery.setOnClickListener(this);
        btnGetImage.setOnClickListener(this);
    }

    private void mapping() {
        imgCamera = findViewById(R.id.imgCamera);
        imgGallery = findViewById(R.id.imgGallery);
        imgReceiver = findViewById(R.id.imgReceiver);
        imgGetAnh = findViewById(R.id.imgGetAnh);
        btnGetImage = findViewById(R.id.btnGetImage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgCamera){
            openCamera();
        } else if (v.getId() == R.id.imgGallery){
            openGallery();
        }else if (v.getId() == R.id.btnGetImage){
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgReceiver.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            imgGetAnh.setImageBitmap(bitmap);
        }
    }

    private void openGallery() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //Từ API 24 trở lên thì không được phép mở thẳng camera mà phải xin quyền người người dùng
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQ_GAL_PER);
        }else{
            //Từ API 23 trở xuống thì được phép mở thẳng camera mà không phải xin quyền người người dùng
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQ_GAL);
        }
    }

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //Từ API 24 trở lên thì không được phép mở thẳng camera mà phải xin quyền người người dùng
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQ_CAM_PER);
        }else{
            //Từ API 23 trở xuống thì được phép mở thẳng camera mà không phải xin quyền người người dùng
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQ_CAM);
        }
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("AAA", ": "+resultCode);
        if (requestCode == REQ_CAM){
            if (resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imgReceiver.setImageBitmap(bitmap);
            }else {
                Toast.makeText(this, "Người dùng không chọn ảnh này!", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQ_GAL){
            if (resultCode == RESULT_OK){
                try {
                    //Uri uri = data.getData();
                    //imgReceiver.setImageURI(uri);
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imgReceiver.setImageBitmap(selectedImage);
                }catch (Exception ex){

                }

            }else {
                Toast.makeText(this, "Người dùng không chọn ảnh này!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CAM_PER){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //bắt đầu mở camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQ_CAM);
            }else {
                Toast.makeText(this, "user do not accept!", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQ_GAL_PER){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //bắt đầu mở gallery
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQ_GAL);
            }else {
                Toast.makeText(this, "user do not accept!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}