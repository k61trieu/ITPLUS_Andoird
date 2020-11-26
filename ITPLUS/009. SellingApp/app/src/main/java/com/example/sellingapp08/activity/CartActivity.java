package com.example.sellingapp08.activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.sellingapp08.R;
import com.example.sellingapp08.adapter.CartAdapter;
import com.example.sellingapp08.database.MyDatabase;
import com.example.sellingapp08.model.ProductCart;
import com.example.sellingapp08.util.StringUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.sellingapp08.util.StringUtil.ID_FIELD;
import static com.example.sellingapp08.util.StringUtil.QUANTITY_FIELD;
import static com.example.sellingapp08.util.StringUtil.TABLE_NAME;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtvTotal;
    Button btnThanhtoan, btnMuahang;
    Toolbar toolbar;

    ListView lvCart;
    List<ProductCart> list;
    CartAdapter adapter;

    MyDatabase myDatabase;
    Cursor cursor;

    int total;

    NotificationManagerCompat notificationManagerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mapping();
        initDatabase();
        init();
        initActionBar();
        initButton();
//        checkProductMysql();
        initNotification();

    }

    private void init() {
        list = getDataCart();
        adapter = new CartAdapter(CartActivity.this, R.layout.line_cart, list);
        lvCart.setAdapter(adapter);
    }
    private void mapping() {
        lvCart = findViewById(R.id.lvCart);
        toolbar = findViewById(R.id.toolbar);
        txtvTotal = findViewById(R.id.txtvTotal);
        btnThanhtoan = findViewById(R.id.btnThanhtoan);
        btnMuahang = findViewById(R.id.btnMuahang);
    }
    private List<ProductCart> getDataCart() {
        List<ProductCart> list = new ArrayList<>();
        total = 0;
        try {
            cursor = myDatabase.selectData("select * from "+ TABLE_NAME);
            while (cursor.moveToNext()){
                ProductCart productCart = new ProductCart();

                productCart.setId(cursor.getInt(0));
                productCart.setName(cursor.getString(1));
                productCart.setPrice(cursor.getInt(2));
                productCart.setAvatar(cursor.getString(3));
                productCart.setQuantity(cursor.getInt(4));
                productCart.setCategoryid(cursor.getInt(5));

                list.add(productCart);
                total += (cursor.getInt(2)*cursor.getInt(4));

            }

        }catch (Exception e){
            Toast.makeText(this, "Giỏ hàng trống !", Toast.LENGTH_SHORT).show();
        }

        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        String price = formatter.format(Double.parseDouble(total+""))+" VNĐ";
        txtvTotal.setText(" "+price);
        return list;
    }
    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    private void initDatabase() {
        //gọi database
        myDatabase = new MyDatabase(CartActivity.this, StringUtil.DB_NAME,null, 1);
    }
    private void initButton() {
        btnMuahang.setOnClickListener(this);
        btnThanhtoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnThanhtoan:
                if (list.size() > 0){
                    showFromThanhToan();
                }else {
                    Toast.makeText(this, "Giỏ hàng của bạn trống !", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnMuahang:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
        }

    }
    public void UpdateQuantityCart(ProductCart productCart, String action){
        int quantity = 0;
        cursor = myDatabase.selectData("select * from "+TABLE_NAME+" where id="+productCart.getId());
        if (action.equals("minus")){
            if (cursor.moveToNext()){
                quantity = (cursor.getInt(4) - 1);
                if (quantity == 0){
                    deleteCart(productCart);
                    return;
                }else if(quantity > 0) {
                    total  -= cursor.getInt(2);
                    productCart.setQuantity(quantity);
                }
            }
        }
        if (action.equals("plus")){
            if (cursor.moveToNext()){
                quantity = (cursor.getInt(4) + 1);
                total +=  cursor.getInt(2);
                productCart.setQuantity(quantity);
            }
        }
        ContentValues cv = new ContentValues();
        cv.put(QUANTITY_FIELD, quantity);
        String whereClause = "id = ?";
        String[] whereArgs = {productCart.getId()+""};
        int chek = myDatabase.update(TABLE_NAME, cv, whereClause, whereArgs);
        if (chek == 1){
            DecimalFormat formatter = new DecimalFormat("###,###,##0");
            String price = formatter.format(Double.parseDouble(total+""))+" VNĐ";
            txtvTotal.setText(" "+price);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this, "Lỗi giỏ hàng !", Toast.LENGTH_LONG).show();
        }

    }
    private void deleteCart(ProductCart productCart) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
        builder.setMessage("Xóa "+productCart.getName()+" khỏi giỏ hàng ?");
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String whereClause = ID_FIELD+" = ?";
                String[] whereArgs = {productCart.getId()+""};
                int check = myDatabase.delete(TABLE_NAME, whereClause, whereArgs);
                if (check > 0){
                    total  -= productCart.getPrice();
                    list.remove(productCart);
                    DecimalFormat formatter = new DecimalFormat("###,###,##0");
                    String price = formatter.format(Double.parseDouble(total+""))+" VNĐ";
                    txtvTotal.setText(" "+price);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(CartActivity.this, "Lỗi giỏ hàng !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }
    private void showFromThanhToan() {
        Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_bill_pay);

        TextView txtvErrName = dialog.findViewById(R.id.txtvErrName);
        TextView txtvErrEmail = dialog.findViewById(R.id.txtvErrEmail);
        TextView txtvErrPhone = dialog.findViewById(R.id.txtvErrPhone);

        EditText edtName = dialog.findViewById(R.id.edtName);
        EditText edtEMail = dialog.findViewById(R.id.edtEMail);
        EditText edtPhone = dialog.findViewById(R.id.edtPhone);

        Button btnOK = dialog.findViewById(R.id.btnOK);
        Button btnCannel = dialog.findViewById(R.id.btnCannel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEMail.getText().toString();
                String phone = edtPhone.getText().toString();

                int check = 0;
                if (name.equals("")){
                    txtvErrName.setVisibility(View.VISIBLE);
                    check = 1;
                }else {
                    txtvErrName.setVisibility(View.GONE);
                    check = 0;
                }

                if (email.equals("")){
                    txtvErrEmail.setVisibility(View.VISIBLE);
                    check = 1;
                }else {
                    txtvErrEmail.setVisibility(View.GONE);
                    check = 0;
                }

                if (phone.equals("")){
                    txtvErrPhone.setVisibility(View.VISIBLE);
                    check = 1;
                }else {
                    txtvErrPhone.setVisibility(View.GONE);
                    check = 0;
                }

                if (check == 0){
                    Toast.makeText(CartActivity.this, "Thanh toán thành công !", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    sendOnChannel();
                }

            }
        });
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void sendOnChannel() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, com.example.sellingapp08.util.Notification.CHANNEL_ID)
                .setSmallIcon(R.drawable.information_icon)
                .setColor(0x169AB9)
                .setContentTitle("Giao dịch thành công !")
                .setContentText("Đơn hàng của bạn đã được xác nhận, chúng tôi sẽ liên hệ lại với bạn sớm nhất ! xin cảm ơn.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setDefaults(Notification.DEFAULT_SOUND)

                //set link nhạc onlie
//                .setSound()

                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        int notificationid = 1;
        this.notificationManagerCompat.notify(notificationid, notification);
    }
    private void initNotification() {
        this.notificationManagerCompat = NotificationManagerCompat.from(this);
    }
}