package com.ad0820e.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ad0820e.appbanhang.R;
import com.ad0820e.appbanhang.activity.ProductDetailActivity;
import com.ad0820e.appbanhang.inteface.ItemClickListener;
import com.ad0820e.appbanhang.model.Product;
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
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(holder.imgAvatar);
        holder.txtvName.setText(p.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtvPrice.setText(decimalFormat.format(p.getPrice()));
        holder.txtvDescription.setText(p.getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(context, "Long Click: "+p, Toast.LENGTH_SHORT).show();
                }else {
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
        ImageView imgAvatar;
        TextView txtvName, txtvPrice, txtvDescription;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            txtvName = itemView.findViewById(R.id.txtvName);
            txtvPrice = itemView.findViewById(R.id.txtvPrice);
            txtvDescription = itemView.findViewById(R.id.txtvDescription);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
