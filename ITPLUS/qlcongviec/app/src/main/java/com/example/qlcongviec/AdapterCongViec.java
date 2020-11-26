package com.example.qlcongviec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterCongViec extends BaseAdapter {
    Context context;
    int layout;
    List<String> list;
    public AdapterCongViec() {
    }

    public AdapterCongViec(Context context, int layout, List<String> list) {
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
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView =inflater.inflate(layout, null);
        TextView txtvTieuDe=convertView.findViewById(R.id.txtvTieuDe);
        txtvTieuDe.setText(list.get(position));
        return convertView;
    }
}
