package com.ad0820e.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ad0820e.appbanhang.R;
import com.ad0820e.appbanhang.adapter.CategoryAdapter;
import com.ad0820e.appbanhang.adapter.ProductAdapter;
import com.ad0820e.appbanhang.database.MyDatabase;
import com.ad0820e.appbanhang.model.Category;
import com.ad0820e.appbanhang.model.Product;
import com.ad0820e.appbanhang.model.Slider;
import com.ad0820e.appbanhang.util.StringUtil;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView rvLastestProduct;
    NavigationView navigationView;
    ListView lvCategory;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    List<Slider> sliderList;

    ProductAdapter productAdapter;
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }

    private void init() {
        initDatabase();
        initActionBar();
        initLeftMenu();
        initViewFlipper();
        getLastestProduct();
    }
    //Tạo databae
    private void initDatabase() {
        //gọi database
        MyDatabase myDatabase = new MyDatabase(MainActivity.this, StringUtil.DB_NAME,null, 1);
        //tạo table
        String url_creadte = "create table if not exists "+StringUtil.TABLE_NAME+" " +
                "("+StringUtil.ID_FIELD+" integer, "+StringUtil.NAME_FIELD+" text, "+
                StringUtil.PRICE_FIELD+" integer, "+StringUtil.AVATAR_FIELD+" text, "+
                StringUtil.QUANTITY_FIELD+" integer, "+StringUtil.CATEGORYID_FIELD+" integer)";
        myDatabase.creadteTable(url_creadte);
    }

    private void getLastestProduct() {
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, R.layout.line_lastest_product, productList);
        rvLastestProduct.setAdapter(productAdapter);
        rvLastestProduct.setHasFixedSize(true);
        rvLastestProduct.setLayoutManager(new GridLayoutManager(this, 2));

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_LASTEST_PRODUCT, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject obj;
                            for (int i = 0; i < response.length(); i++) {
                                obj = response.getJSONObject(i);
                                productList.add(new Product(obj.getInt("id"),
                                        obj.getString("name"), obj.getInt("price"),
                                        obj.getString("avatar"),obj.getString("description"),
                                        obj.getInt("categoryid")));
                            }
                            productAdapter.notifyDataSetChanged();
                        }catch (Exception ex){
                            
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(arrayRequest);
    }

    private void initViewFlipper() {
        sliderList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_SLIDER, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                sliderList.add(new Slider(jsonObject.getInt("id"), jsonObject.getString("url"), jsonObject.getInt("categoryid"), jsonObject.getString("description")));
                            }
                            ImageView imageView;
                            for (int i = 0; i < sliderList.size(); i++) {
                                imageView = new ImageView(MainActivity.this);
                                Picasso.get().load(sliderList.get(i).getUrl()).into(imageView);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                viewFlipper.addView(imageView);
                                viewFlipper.setFlipInterval(2000);
                                viewFlipper.setAutoStart(true);
                            }
                        }catch (Exception ex){
                        }
                        //sliderList = sliderList;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(arrayRequest);

    }
    private void getAllSlider() {
//         sliderList = new ArrayList<>();
//        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_SLIDER, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                sliderList.add(new Slider(jsonObject.getInt("id"), jsonObject.getString("url"), jsonObject.getInt("categoryid"), jsonObject.getString("description")));
//                            }
//                        }catch (Exception ex){
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(arrayRequest);
    }

    private void initLeftMenu() {
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, R.layout.line_category, categoryList);
        lvCategory.setAdapter(categoryAdapter);
        //categoryList.add(0, new Category(0, "Trang Chủ", "http://"+StringUtil.HOST+"/itplus/ad0820e/api/image/home.png"));
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_CATEGORY, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Category cateContact = null, cateInfor = null;
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                if (jsonObject.getInt("id") == 2){
                                    cateContact = new Category(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("avatar"));
                                }else if (jsonObject.getInt("id") == 3){
                                    cateInfor = new Category(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("avatar"));
                                } else {
                                    categoryList.add(new Category(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("avatar")));
                                }
                            }
                            categoryList.add(cateContact);
                            categoryList.add(cateInfor);
                            //categoryList.add(new Category(10001, "Liên Hệ", "http://"+StringUtil.HOST+"/itplus/ad0820e/api/image/contact.png"));
                            //categoryList.add(new Category(10002, "Thông Tin", "http://"+StringUtil.HOST+"/itplus/ad0820e/api/image/personalinfor.jpg"));
                            categoryAdapter.notifyDataSetChanged();
                        }catch (Exception ex){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(arrayRequest);

        //khởi tạo sự kiện cho listview
        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (categoryList.get(position).getId()){
                    case 1:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.putExtra("category", categoryList.get(position));
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, ContactActivity.class);
                        intent2.putExtra("category", categoryList.get(position));
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, InforActivity.class);
                        intent3.putExtra("category", categoryList.get(position));
                        startActivity(intent3);
                        break;
                    default:
                        Intent i = new Intent(MainActivity.this, ProductActivity.class);
                        i.putExtra("category", categoryList.get(position));
                        startActivity(i);
                }
            }
        });
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void mapping() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewFlipper);
        rvLastestProduct = findViewById(R.id.rvLastestProduct);
        navigationView = findViewById(R.id.navigationView);
        lvCategory = findViewById(R.id.lvCategory);
    }
}