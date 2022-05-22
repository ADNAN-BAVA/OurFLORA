package com.example.ourflora.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourflora.Plantdetails;
import com.example.ourflora.ProductDetails;
import com.example.ourflora.R;
import com.example.ourflora.model.Products;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Products> productsList;

    public ProductAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plant_row_item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.prodImage.setImageResource(productsList.get(position).getImageUrl());
        holder.prodname.setText(productsList.get(position).getProductName());
        holder.prodcountry.setText(productsList.get(position).getProductCountry());
        holder.prodprice.setText(productsList.get(position).getProductPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Plantdetails.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView prodImage;
        TextView prodname,prodcountry,prodprice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImage = itemView.findViewById(R.id.prodimg);
            prodname = itemView.findViewById(R.id.prod_name);
            prodcountry = itemView.findViewById(R.id.prod_country);
            prodprice = itemView.findViewById(R.id.prod_price);
        }
    }

}
