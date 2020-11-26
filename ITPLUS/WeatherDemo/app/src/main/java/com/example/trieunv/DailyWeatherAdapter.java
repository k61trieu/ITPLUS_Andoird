package com.example.trieunv;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trieunv.DailyWeather;
import com.example.trieunv.R;
import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class DailyWeatherAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<DailyWeather> list;

    public DailyWeatherAdapter(Context context, int layout, List<DailyWeather> list) {
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
    class ViewHolder{
        TextView tvDayOfWeek;
        TextView tvDayOfYear;
        TextView tvStatus;
        TextView tvTemper;
        ImageView imgWeatherIcon;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.tvDayOfWeek = view.findViewById(R.id.tvDayOfWeek);
            viewHolder.tvDayOfYear = view.findViewById(R.id.tvDayOfYear);
            viewHolder.tvStatus = view.findViewById(R.id.tvStatus);
            viewHolder.imgWeatherIcon = view.findViewById(R.id.imgWeatherIcon);
            viewHolder.tvTemper = view.findViewById(R.id.tvTemper);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DailyWeather dailyWeather = list.get(i);
        viewHolder.tvDayOfWeek.setText(dailyWeather.getDayOfWeek());
        viewHolder.tvDayOfYear.setText(dailyWeather.getDayOfYear());
        viewHolder.tvStatus.setText(dailyWeather.getStatus());
        Picasso.get().load(dailyWeather.getWeatherIcon()).into(viewHolder.imgWeatherIcon);
        //viewHolder.imgWeatherIcon.setImageResource(Integer.parseInt(dailyWeather.getWeatherIcon()));
        viewHolder.tvTemper.setText(dailyWeather.getTemper());

        return view;
    }
}
