package com.ad0820e.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ad0820e.appbanhang.R;
import com.ad0820e.appbanhang.adapter.ProductByCategoryIdAdapter;
import com.ad0820e.appbanhang.model.Category;
import com.ad0820e.appbanhang.model.Product;
import com.ad0820e.appbanhang.util.StringUtil;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

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
        Category cate = (Category) intent.getSerializableExtra("category");
        getAllProductByCategoryId(cate.getId());
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
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_PRODUCT_BY_CATEGORY_ID+"?categoryid="+id, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject obj;
                            for (int i = 0; i < response.length(); i++) {
                                obj = response.getJSONObject(i);
                                productList.add(new Product(obj.getInt("id"), obj.getString("name"),
                                        obj.getInt("price"), obj.getString("avatar"),
                                        obj.getString("description"), obj.getInt("categoryid")));
                            }
                            productByCategoryIdAdapter.notifyDataSetChanged();
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
}