package com.example.sellingapp08.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellingapp08.R;
import com.example.sellingapp08.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    Context context;
    int layout;
    List<Product> listProduct;



    public ProductAdapter(Context context, int layout, List<Product> listProduct) {
        this.context = context;
        this.layout = layout;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(layout, null);
        View view = LayoutInflater.from(context).inflate(layout, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = listProduct.get(position);
        Picasso.get().load(p.getAvatar())
                .placeholder(R.drawable.loadding)
                .error(R.drawable.loadding)
                .into(holder.imgProductAvatar);
        holder.txtvProducName.setText(p.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtvProductPrice.setText("Giá: " + decimalFormat.format(p.getPrice()) + " Đ");
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProductAvatar;
        TextView txtvProducName;
        TextView txtvProductPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductAvatar = itemView.findViewById(R.id.imgProductAvatar);
            txtvProducName = itemView.findViewById(R.id.txtvProducName);
            txtvProductPrice = itemView.findViewById(R.id.txtvProductPrice);
        }
    }
}

