package com.nc.chien.listviewnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvFruit;
    List<Fruit> list;
    FruitAdapter fruitAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }

    private void init() {
        list = new ArrayList<>();
        list.add(new Fruit(R.drawable.cam,"Cam","Hoa quả chất lượng cao "));
        list.add(new Fruit(R.drawable.chanh,"Chanh","Chanh chất lượng cao "));
        list.add(new Fruit(R.drawable.le,"Lê","Lê chất lượng cao "));
        list.add(new Fruit(R.drawable.man,"Mận","Mận chất lượng cao "));
        list.add(new Fruit(R.drawable.tao,"Táo","Táo Trung Quốc chất lượng cao"));
        list.add(new Fruit(R.drawable.nho,"Nho","Nho Mỹ chất lượng cao "));
        list.add(new Fruit(R.drawable.saurieng,"Sầu riêng","Sầu riêng chất lượng cao "));
        fruitAdapter = new FruitAdapter(MainActivity.this,R.layout.activity_fruit,list);
        lvFruit.setAdapter(fruitAdapter);

    }
    private void mapping(){
        lvFruit=findViewById(R.id.lvFruit);

    }
}