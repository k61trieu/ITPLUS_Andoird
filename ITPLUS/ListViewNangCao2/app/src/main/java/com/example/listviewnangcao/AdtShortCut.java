package com.example.listviewnangcao;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class AdtShortCut extends BaseAdapter {
    Context context;
    Layout layout;
    List<ShortCut> list;

    public AdtShortCut(AdtGridview context, Layout layout, List<ShortCut> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    public AdtShortCut(AdtGridview context, int activity_shortcut, List<ShortCut> list) {

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
        convertView = inflater.inflate((XmlPullParser) layout,null);
        ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
        TextView txtApp = convertView.findViewById(R.id.txtApp);
        ShortCut shortCut = list.get(position);
        imgIcon.setImageResource(shortCut.getAvatar());
        txtApp.setText(shortCut.getName());
        return convertView;
    }
}
