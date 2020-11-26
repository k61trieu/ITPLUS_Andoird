package com.nc.chien.listviewnangcao;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Fruit> list;

    public FruitAdapter(Context context, int layout, List<Fruit> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        ImageView imgAvatar = convertView.findViewById(R.id.imgAvatar);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtDesc = convertView.findViewById(R.id.txtDesc);
        Fruit fruit = list.get(position);
        imgAvatar.setImageResource(fruit.getAvatar());
        txtName.setText(fruit.getName());
        txtDesc.setText(fruit.getDesc());
        return convertView;
    }
}
