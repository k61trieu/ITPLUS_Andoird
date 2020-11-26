package com.example.weatherdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String API_KEY = "1f626a6d252dcb845a6d9f43efe5238d";
    EditText edtProvince;
    Button btnOK, btnNextDay;
    TextView tvProvice, txtNation, txtTemper, txtStatus, txtCloud, txtHumidity, txtWind, txtCurentTemper;
    ImageView imgWeatherIcon, imgCloud, imgHumidity, imgWind;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        getJsonWeather("hanoi");
        getCurrentDay();
        goToNextDay();



    }
    private void getCurrentDay(){
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edtProvince.getText().toString();
                getJsonWeather(city);
            }
        });
    }
    private void goToNextDay(){
        btnNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtProvince.getText().toString().equals("")){
                    city = "hanoi";
                } else {
                    city = edtProvince.getText().toString();
                }
                Intent intent = new Intent(MainActivity.this, NextDayActivity.class);
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });
    }
    public void getJsonWeather(String city){
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+API_KEY+"&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String name = response.getString("name");
                            tvProvice.setText("City/Province: "+ name);

                            JSONObject sys = response.getJSONObject("sys");
                            String country = sys.getString("country");
                            txtNation.setText("Country: "+country);

                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            String icon = weatherObject.getString("icon");//Icon
                            String urlIcon = "http://openweathermap.org/img/wn/"+icon+"@4x.png";
                            Picasso.get().load(urlIcon).into(imgWeatherIcon);
                            String temperState = weatherObject.getString("main");
                            txtStatus.setText(temperState);

                            JSONObject main = response.getJSONObject("main");
                            String temp = main.getString("temp");
                            String humidity = main.getString("humidity");
                            txtTemper.setText(temp + "°C");
                            txtHumidity.setText(humidity + "%");

                            JSONObject clouds = response.getJSONObject("clouds");
                            String all = clouds.getString("all");
                            txtCloud.setText(all + "%");

                            JSONObject wind = response.getJSONObject("wind");
                            String speed = wind.getString("speed");
                            txtWind.setText(speed + "m/s");

                            String dt = response.getString("dt");
                            Long dtL = Long.parseLong(dt);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yyyy, HH:mm:ss");
                            Date date = new Date(dtL*1000);
                            String currentTime = dateFormat.format(date);
                            txtCurentTemper.setText(currentTime);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Toast.makeText(MainActivity.this, "" + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void mapping() {
        edtProvince = findViewById(R.id.edtProvince);
        btnOK = findViewById(R.id.btnOK);
        btnNextDay = findViewById(R.id.btnNextDay);
        tvProvice = findViewById(R.id.tvProvice);
        txtNation = findViewById(R.id.txtNation);
        txtTemper = findViewById(R.id.txtTemper);
        txtStatus = findViewById(R.id.txtStatus);
        txtCloud = findViewById(R.id.txtCloud);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtWind = findViewById(R.id.txtWind);
        txtCurentTemper = findViewById(R.id.txtCurentTemper);
        imgWeatherIcon = findViewById(R.id.imgWeatherIcon);
        imgCloud = findViewById(R.id.imgCloud);
        imgHumidity = findViewById(R.id.imgHumidity);
        imgWind = findViewById(R.id.imgWind);
    }
}