package com.ad0820e.appbanhang.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ad0820e.appbanhang.R;
import com.ad0820e.appbanhang.database.MyDatabase;
import com.ad0820e.appbanhang.model.Product;
import com.ad0820e.appbanhang.util.StringUtil;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetailActivity extends AppCompatActivity {
    MyDatabase myDatabase;
    Cursor cursor;


    Toolbar toolbar;
    ImageView imgProduct;
    TextView txtvName, txtvPrice, txtvQuantity, txtvDescription;
    Button btnMinus, btnPlus, btnAddCart;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        mapping();
        initDatabase();
        init();
    }
    private void initDatabase() {
        //gọi database
        myDatabase = new MyDatabase(ProductDetailActivity.this, StringUtil.DB_NAME,null, 1);
    }
    public void clickProductDetail(View v){
        switch (v.getId()){
            case R.id.btnMinus:
                minusQuantity();
                break;
            case R.id.btnPlus:
                plusQuantity();
                break;
            case R.id.btnAddCart:
                addToCart();
                break;
        }
    }

    private void addToCart() {
        if (!checkCart()){
            ContentValues cv = new ContentValues();
            cv.put(StringUtil.ID_FIELD,product.getId());
            cv.put(StringUtil.NAME_FIELD,product.getName());
            cv.put(StringUtil.PRICE_FIELD, product.getPrice());
            cv.put(StringUtil.AVATAR_FIELD, product.getAvatar());
            cv.put(StringUtil.QUANTITY_FIELD, txtvQuantity.getText().toString());
            cv.put(StringUtil.CATEGORYID_FIELD, product.getCategoryId());

            long check = myDatabase.insertData(StringUtil.TABLE_NAME, null, cv);
            if (check > 0){
                Toast.makeText(this, product.getName()+" được thêm vào giỏ hàng !", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Lỗi giỏ hàng !", Toast.LENGTH_LONG).show();
            }
        }
    }
    private boolean checkCart() {
        cursor = myDatabase.selectData("select * from "+StringUtil.TABLE_NAME+" where id="+product.getId());
        if (cursor.moveToNext()){
            ContentValues cv = new ContentValues();
            int quantity = (cursor.getInt(4) + Integer.parseInt(txtvQuantity.getText().toString()));
            cv.put(StringUtil.QUANTITY_FIELD, quantity);
            String whereClause = "id = ?";
            String[] whereArgs = {cursor.getInt(0)+""};
            int chek = myDatabase.update(StringUtil.TABLE_NAME, cv, whereClause, whereArgs);
            if (chek == 1){
                Toast.makeText(this, cursor.getString(1)+" được thêm vào giỏ hàng !", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Lỗi giỏ hàng !", Toast.LENGTH_LONG).show();
            }

            return true;
        }
        return false;
    }

    private void plusQuantity() {
        int qtt = Integer.parseInt(txtvQuantity.getText().toString());
        qtt++;
        txtvQuantity.setText(qtt+"");
    }

    private void minusQuantity() {
        int qtt = Integer.parseInt(txtvQuantity.getText().toString());
        if (qtt > 1){
            qtt--;
        }
        txtvQuantity.setText(qtt+"");
    }

    private void init() {
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("productDetail");
        Picasso.get().load(product.getAvatar()).into(imgProduct);
        txtvName.setText(product.getName());

        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        String price = formatter.format(Double.parseDouble(product.getPrice()+""))+" VNĐ";
        txtvPrice.setText("Giá "+price);

        txtvDescription.setText(product.getDescription());
    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbar);
        imgProduct = findViewById(R.id.imgProduct);
        txtvName = findViewById(R.id.txtvName);
        txtvPrice = findViewById(R.id.txtvPrice);
        txtvQuantity = findViewById(R.id.txtvQuantity);
        txtvDescription = findViewById(R.id.txtvDescription);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnAddCart = findViewById(R.id.btnAddCart);
    }
}