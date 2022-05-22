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

import com.example.ourflora.ProductDetails;
import com.example.ourflora.R;
import com.example.ourflora.model.Accessory;

import java.util.List;

public class AccessoryAdapter  extends RecyclerView.Adapter<AccessoryAdapter.AccessoryViewHolder> {

    Context context;
    List<Accessory> accessoryList;

    public AccessoryAdapter(Context context,List<Accessory> accessoryList)
    {
        this.context = context;
        this.accessoryList = accessoryList;
    }

    @NonNull
    @Override
    public AccessoryAdapter.AccessoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.accessory_item_row,parent,false);
        return new AccessoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccessoryAdapter.AccessoryViewHolder holder, int position) {
        holder.accessimg.setImageResource(accessoryList.get(position).getImageUrl());
        holder.accessname.setText(accessoryList.get(position).getAccessName());
        holder.accesssize.setText(accessoryList.get(position).getAccessSize());
        holder.accessprice.setText(accessoryList.get(position).getAccessPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProductDetails.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accessoryList.size();
    }

    public static final class AccessoryViewHolder extends RecyclerView.ViewHolder {
        ImageView accessimg;
        TextView accessname,accesssize,accessprice;
        public AccessoryViewHolder(@NonNull View itemView) {
            super(itemView);

            accessimg = itemView.findViewById(R.id.access_img);
            accessname = itemView.findViewById(R.id.access_name);
            accesssize = itemView.findViewById(R.id.access_size);
            accessprice = itemView.findViewById(R.id.access_price);
        }
    }
}
