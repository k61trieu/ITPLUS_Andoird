package com.example.qlvattu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class VatTuAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<VatTu> list;

    public VatTuAdapter(Context context, int layout, List<VatTu> list) {
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

    class ViewHodeler{
        TextView txtvName;
        TextView txtvDesc;
        ImageView imgAvatar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if ()
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);
        TextView txtvName = convertView.findViewById(R.id.txtvName);
        TextView txtvDesc = convertView.findViewById(R.id.txtvDesc);
        ImageView imgAvatar = convertView.findViewById(R.id.imgAvatar);
        VatTu vatTu = list.get(position);
        txtvName.setText(vatTu.getName());
        txtvDesc.setText(vatTu.getDesc());
        Bitmap bitmap = BitmapFactory.decodeByteArray(vatTu.getAvatar(), 0, vatTu.getAvatar().length);
        imgAvatar.setImageBitmap(bitmap);
        return convertView;
    }
}
