package com.example.sellingapp08.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellingapp08.Interface.ItemClickListener;
import com.example.sellingapp08.R;
import com.example.sellingapp08.activity.ProductDetailActivity;
import com.example.sellingapp08.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductByCategoryIdAdapter extends RecyclerView.Adapter<ProductByCategoryIdAdapter.ViewHolder> {
    Context context;
    int layout;
    List<Product> list;

    public ProductByCategoryIdAdapter(Context context, int layout, List<Product> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = list.get(position);
        Picasso.get().load(p.getAvatar())
                .placeholder(R.drawable.loadding)
                .error(R.drawable.nopicture)
                .into(holder.imgAvatar);
        holder.txtvName.setText(p.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtvPrice.setText("Giá: "+decimalFormat.format(p.getPrice())+" VND");
        holder.txtvDescription.setText(p.getDescription());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
//                    Intent intent = new Intent(context, ProductDetailActivity.class);
//                    context.startActivity(intent);
                    //Toast.makeText(context, "Long", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("productDetail", p);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ItemClickListener itemClickListener;

        ImageView imgAvatar;
        TextView txtvName;
        TextView txtvPrice;
        TextView txtvDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            txtvName = itemView.findViewById(R.id.txtvName);
            txtvPrice = itemView.findViewById(R.id.txtvPrice);
            txtvDescription = itemView.findViewById(R.id.txtvDescription);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);
            return true;
        }
    }

}
