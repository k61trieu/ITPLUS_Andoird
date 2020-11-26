package com.example.sellingapp08.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sellingapp08.R;
import com.example.sellingapp08.adapter.ProductByCategoryIdAdapter;
import com.example.sellingapp08.model.Category;
import com.example.sellingapp08.model.Product;
import com.example.sellingapp08.util.StringUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    ProductByCategoryIdAdapter productByCategoryIdAdapter;
    List<Product> productList;
    Toolbar tbProduct;
    RecyclerView rcvProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mapping();
        Intent intent = getIntent();
        Category category= (Category) intent.getSerializableExtra("category");
        getAllProductByCategoryId(category.getId());
    }

    private void mapping() {
        tbProduct = findViewById(R.id.tbProduct);
        rcvProduct = findViewById(R.id.rcvProduct);
    }

    private void getAllProductByCategoryId(int id) {
        productList = new ArrayList<>();
        productByCategoryIdAdapter = new ProductByCategoryIdAdapter(this, R.layout.line_product, productList);
        rcvProduct.setAdapter(productByCategoryIdAdapter);
        rcvProduct.setHasFixedSize(true);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                StringUtil.URL_GET_PRODUCT_BY_CATEGORYID, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                int id = object.getInt("id");
                                String name = object.getString("name");
                                int price = object.getInt("price");
                                String avatar = object.getString("avatar");
                                String description = object.getString("description");
                                int categoryId = object.getInt("categoryid");
                                productList.add(new Product(id, name, price, avatar, description, categoryId));

                            }
                            productByCategoryIdAdapter.notifyDataSetChanged();
                        } catch (Exception e){}
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}