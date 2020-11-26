package com.example.sellingapp08.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sellingapp08.R;
import com.example.sellingapp08.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Category> list;

    public CategoryAdapter(Context context, int layout, List<Category> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }
    class ViewHolder{
        ImageView imgIcon;
        TextView txtvName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = LayoutInflater.from(context).inflate(layout, null);
            viewHolder.imgIcon = view.findViewById(R.id.imgIcon);
            viewHolder.txtvName = view.findViewById(R.id.txtvName);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        Category category = (Category) getItem(i);
        viewHolder.txtvName.setText(category.getName());
        Picasso.get().load(category.getAvatar())
                .placeholder(R.drawable.loadding)
                .error(R.drawable.nopicture)
                .into(viewHolder.imgIcon);

        return view;
    }
}
