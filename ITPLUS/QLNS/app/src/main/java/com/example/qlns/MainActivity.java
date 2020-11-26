package com.example.qlns;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Student> studentList;
    StudentAdapter adapter=null;
    ListView lvStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStudent = findViewById(R.id.lvStudent);
        studentList = new ArrayList<>();
        lvStudent.setAdapter(adapter);
        adapter = new StudentAdapter(MainActivity.this, R.layout.activity_line_student, studentList);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "http://192.168.0.9/itplus/getdata.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject object = response.getJSONObject(i);
                                studentList.add(new Student(object.getInt("id"), object.getString("hoTen"), object.getString("ngaySinh"), object.getString("diaChi")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}


