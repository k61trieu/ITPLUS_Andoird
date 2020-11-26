package com.example.weatherdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NextDayActivity extends AppCompatActivity {
    List<DailyWeather> listD;
    ListView lvListOfDay;
    TextView tvCityName;
    Button btnBack;
    DailyWeatherAdapter dailyWeatherAdapter;
    public static final String API_KEY = "1f626a6d252dcb845a6d9f43efe5238d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_day);
        mapping();

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        listD = new ArrayList<>();
//        getJsonNextDay();
        dailyWeatherAdapter = new DailyWeatherAdapter(NextDayActivity.this, R.layout.activity_daily_weather, listD);
        lvListOfDay.setAdapter(dailyWeatherAdapter);
        tvCityName.setText("City/Province: " + city);
        getJsonNextDay(city);
        goBack();


    }
    private void getJsonNextDay(String city){
        String url = "http://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=1f626a6d252dcb845a6d9f43efe5238d&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject item = list.getJSONObject(i);
                                String dt = item.getString("dt");
                                Long dtL = Long.parseLong(dt);
                                SimpleDateFormat dateWeek = new SimpleDateFormat("EEEE");
                                SimpleDateFormat dateYear = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
                                Date date = new Date(dtL*1000);
                                String dateOfWeek = dateWeek.format(date);
                                String dateOfYear = dateYear.format(date);

                                JSONArray weatherArray = item.getJSONArray("weather");
                                JSONObject weatherObject = weatherArray.getJSONObject(0);
                                String status = weatherObject.getString("description");
                                String icon = weatherObject.getString("icon");
                                String urlIcon = "http://openweathermap.org/img/wn/"+icon+"@4x.png";

                                JSONObject main = item.getJSONObject("main");
                                String temp_min = main.getString("temp_min");
                                String temp_max = main.getString("temp_max");
                                String Temper = temp_min+"°C"+"-"+temp_max +"°C";

                                listD.add(new DailyWeather(dateOfWeek, dateOfYear, status, Temper, urlIcon));

                            }
                            dailyWeatherAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    private void goBack(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextDayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        lvListOfDay = findViewById(R.id.lvListOfDay);
        tvCityName = findViewById(R.id.tvCityName);
        btnBack = findViewById(R.id.btnBack);
    }
}