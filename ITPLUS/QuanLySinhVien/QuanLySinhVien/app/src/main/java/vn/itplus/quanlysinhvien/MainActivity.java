package vn.itplus.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    ListView lvSV;
    List<SinhVien> list;
    SinhVienAdapter sinhVienAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lvSV = findViewById(R.id.lvSV);
        list = new ArrayList<SinhVien>();
        sinhVienAdapter = new SinhVienAdapter(R.layout.sinhvien, MainActivity.this, list);
        lvSV.setAdapter(sinhVienAdapter);
        readArrayRequest();
    }

    private void readArrayRequest() {

        String url = "http://192.168.43.124/QLSV/select.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                int id = object.getInt("id");
                                String hoten = object.getString("hoten");
                                String diachi = object.getString("diachi");
                                String ngaysinh = object.getString("ngaysinh");
                                SinhVien sv = new SinhVien(id,hoten,ngaysinh,diachi);
                                list.add(sv);
                                Log.d("chiennc",""+list.size());
                                sinhVienAdapter.notifyDataSetChanged();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.toString();
                        Log.d("ncchien",""+error.toString());
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addClick(MenuItem item) {
        Intent i = new Intent(this,AddSinhVien.class);
        startActivity(i);
    }
}