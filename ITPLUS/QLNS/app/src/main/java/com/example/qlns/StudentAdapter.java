package com.example.qlns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter  extends BaseAdapter
{
private Context context;
int layout;
private List<Student> list;

    public StudentAdapter(Context context, int layout, List<Student> list) {
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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
class viewHolder{
        TextView txtvHoTen,txtvNgaySinh, txtvDiachi;
        ImageView imgEdit,imgDelete;


}
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        viewHolder holder= null;
        if (view==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= layoutInflater.inflate(layout,null);
            holder.txtvHoTen= view.findViewById(R.id.txtvHoTen);
            holder.txtvNgaySinh= view.findViewById(R.id.txtvNgaySinh);
            holder.txtvDiachi= view.findViewById(R.id.txtvDiachi);
            holder.imgEdit= view.findViewById(R.id.imgEdit);
            holder.imgDelete= view.findViewById(R.id.imgDelete);
            view.setTag(holder);
        }else {
           holder=(viewHolder) view.getTag();
        }


        Student student= list.get(i);
        holder.txtvHoTen.setText(student.getHoten());
        holder.txtvNgaySinh.setText(student.getNgaysinh());
        holder.txtvDiachi.setText(student.getDiachi());



        return view;
    }
}
