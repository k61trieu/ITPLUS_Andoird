package com.example.sellingapp08.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sellingapp08.R;
import com.example.sellingapp08.database.MyDatabase;
import com.example.sellingapp08.model.Product;
import com.example.sellingapp08.util.StringUtil;
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
//                Toast.makeText(this, product.getName()+" được thêm vào giỏ hàng !", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
//                startActivity(intent);
            }else {
//                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
//                startActivity(intent);
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
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
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
        initActionBar();
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
    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(intent);
//        Toast.makeText(this, "Hien thi man hinh gio hang o day", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}
