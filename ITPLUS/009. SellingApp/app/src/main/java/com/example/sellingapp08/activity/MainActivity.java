package com.example.sellingapp08.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sellingapp08.R;
import com.example.sellingapp08.adapter.CategoryAdapter;
import com.example.sellingapp08.adapter.ProductAdapter;
import com.example.sellingapp08.database.MyDatabase;
import com.example.sellingapp08.model.Category;
import com.example.sellingapp08.model.Product;
import com.example.sellingapp08.util.Slider;
import com.example.sellingapp08.util.StringUtil;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import android.graphics.drawable.Drawable;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView rvLastestProduct;
    NavigationView navigationView;
    ListView lvCategory;
    CategoryAdapter categoryAdapter;
    List<Category> listCategory;
    List<Slider> sliderList;
    List<Product> productList;
    ProductAdapter productAdapter;
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
        initViewFliper();
        initGetLastestProduct();

//        initFindInCategory();
    }

//    private void initFindInCategory() {
//        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Category category = listCategory.get(i);
////                Toast.makeText(MainActivity.this, ""+ category.getName(), Toast.LENGTH_SHORT).show();
//                if (category.getName().equals("Lien he")){
//                    Intent intent = new Intent(MainActivity.this, ContactActivity.class);
//                    startActivity(intent);
//                } if (category.getName().equals("Thong tin")){
//                    Intent intent = new Intent(MainActivity.this, InformationActivity.class);
//                    startActivity(intent);
//                }
//
//            }
//        });
//    }

    private void initGetLastestProduct() {
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(MainActivity.this, R.layout.line_lastest_product, productList);
        rvLastestProduct.setHasFixedSize(true);
        rvLastestProduct.setAdapter(productAdapter);
        rvLastestProduct.setLayoutManager(new GridLayoutManager(this, 2));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_PRODUCT,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject lastestProduct = response.getJSONObject(i);
                            productList.add(new Product(lastestProduct.getInt("id"),
                                lastestProduct.getString("name"),
                                lastestProduct.getInt("price"),
                                lastestProduct.getString("avatar"),
                                lastestProduct.getString("description"),
                                lastestProduct.getInt("categoryid")));
                    }
                    productAdapter.notifyDataSetChanged();
                } catch (Exception e){
                    e.printStackTrace();
                }
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject lastestProduct = response.getJSONObject(i);
//                        productList.add(new Product(lastestProduct.getInt("id"),
//                                lastestProduct.getString("name"),
//                                lastestProduct.getInt("price"),
//                                lastestProduct.getString("avatar"),
//                                lastestProduct.getString("description"),
//                                lastestProduct.getInt("categoryid")));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                productAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void initViewFliper() {
//        List<String> sliderList = new ArrayList<>();
//        sliderList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/T_m_i_m_khai_tr_ng_-_Laptop_ASUS.png");
//        sliderList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/595x100iPhone-12-v2.png");
//        ImageView imageView;
        getAllSlider();

//        for (int i = 0; i < sliderList.size(); i++) {
//            imageView = new ImageView(this);
//            Picasso.get().load(sliderList.get(i).getUrl()).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewFlipper.addView(imageView);
//            viewFlipper.setFlipInterval(2000);
//            viewFlipper.setAutoStart(true);
//        }
    }
    private void getAllSlider(){
        sliderList = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_SLIDER, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                sliderList.add(new Slider(jsonObject.getInt("id"), jsonObject.getString("url"),
                                        jsonObject.getString("categoryid"), jsonObject.getString("description")));

                            }
                            ImageView imageView;
                            for (int i = 0; i < sliderList.size(); i++) {
                                imageView = new ImageView(MainActivity.this);
                                Picasso.get().load(sliderList.get(i).getUrl()).into(imageView);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                viewFlipper.addView(imageView);
                                viewFlipper.setFlipInterval(5000);
                                viewFlipper.setAutoStart(true);
                            }
                        } catch (JSONException e) {

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
//        Toast.makeText(MainActivity.this, ""+ sliderList.size(), Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    private void initLeftMenu() {
        listCategory = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(MainActivity.this, R.layout.line_catagory, listCategory);
        lvCategory.setAdapter(categoryAdapter);
//        listCategory.add(0, new Category(0, "Trang chu", "http://162.168.10.36/api/image/home.jpg"));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_GATEGORY,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Category cateContact = null;
                            Category cateInfor = null;
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                if (jsonObject.getInt("id") == 2){
                                    cateContact = new Category(jsonObject.getInt("id"),
                                            jsonObject.getString("name"),
                                            jsonObject.getString("avatar"));
                                } else if (jsonObject.getInt("id") == 3){
                                    cateInfor = new Category(jsonObject.getInt("id"),
                                            jsonObject.getString("name"),
                                            jsonObject.getString("avatar"));
                                } else {
                                    listCategory.add(new Category(jsonObject.getInt("id"),
                                            jsonObject.getString("name"),
                                            jsonObject.getString("avatar")));
                                }
//                                listCategory.add(new Category(jsonObject.getInt("id"), jsonObject.getString("name"),
//                                        jsonObject.getString("avatar")));
                            }
//                            listCategory.add(new Category(1001, "Lien he", "http://162.168.10.36/api/image/information.jpg"));
//                            listCategory.add(new Category(1002, "Thong tin", "http://162.168.10.36/api/image/phone.jpg"));
                            listCategory.add(cateContact);
                            listCategory.add(cateInfor);
                            categoryAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (listCategory.get(i).getId()){
                    case 1:
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.putExtra("category", listCategory.get(i));
                    startActivity(intent);
                    break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, ContactActivity.class);
                        intent2.putExtra("category", listCategory.get(i));
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, InformationActivity.class);
                        intent3.putExtra("category", listCategory.get(i));
                        startActivity(intent3);
                        break;
                    default:
                        Intent intent4 = new Intent(MainActivity.this, ProductActivity.class);
                        intent4.putExtra("category", listCategory.get(i));
                        startActivity(intent4);
                        break;
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
            public void onClick(View view) {
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
}