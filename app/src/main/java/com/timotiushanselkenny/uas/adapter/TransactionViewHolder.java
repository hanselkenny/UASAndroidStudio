package com.timotiushanselkenny.uas.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timotiushanselkenny.uas.R;


public class TransactionViewHolder extends RecyclerView.ViewHolder {

    private TextView viewPrice;
    private TextView viewName;
    private TextView viewDescription;
    private ImageView imageView;
    public TransactionViewHolder(@NonNull View itemView) {
        super(itemView);
        viewPrice = itemView.findViewById(R.id.tvOrderProductPrice);
        viewName = itemView.findViewById(R.id.tvOrderProductName);
        viewDescription = itemView.findViewById(R.id.tvOrderProductDescription);
        imageView = itemView.findViewById(R.id.imOrderImage);
    }

    public TextView getViewPrice(){
        return viewPrice;
    }
    public TextView getViewName(){
        return viewName;
    }
    public TextView getViewDescription(){
        return viewDescription;
    }
    public ImageView getImage(){
        return imageView;
    }
}