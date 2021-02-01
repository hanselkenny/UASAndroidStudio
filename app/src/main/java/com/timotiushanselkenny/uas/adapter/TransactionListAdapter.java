package com.timotiushanselkenny.uas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.timotiushanselkenny.uas.R;
import com.timotiushanselkenny.uas.database.ProductTransaction;

import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder> {
    private Context mContext;
    private List<ProductTransaction> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int editQuantity[];

    // data is passed into the constructor
    public TransactionListAdapter(Context context, List<ProductTransaction> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext=context;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.frame_orderview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myNameView.setText(mData.get(position).getName());
        holder.myPriceView.setText(mData.get(position).getPrice());
        holder.myDescriptionView.setText(mData.get(position).getDescription());
        holder.mEditText.setText(mData.get(position).getQuantity());
        holder.myIdView.setText(String.valueOf(mData.get(position).getId()));
        String imageUri = mData.get(position).getImage().toString();
        Picasso.with(mContext).load(imageUri).into(holder.myImageView);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myPriceView;
        TextView myNameView;
        TextView myDescriptionView;
        TextView myIdView;
        TextView myUpdateView;
        ImageView myImageView;
        public EditText mEditText;

        ViewHolder(View itemView) {
            super(itemView);
            this.myPriceView=itemView.findViewById(R.id.tvOrderProductPrice);
            this.myNameView=itemView.findViewById(R.id.tvOrderProductName);
            this.myDescriptionView=itemView.findViewById(R.id.tvOrderProductDescription);
            this.mEditText=itemView.findViewById(R.id.etEditOrderQuantity);
            this.myIdView=itemView.findViewById(R.id.tvOrderId);
            this.myUpdateView=itemView.findViewById(R.id.buttonIncrease);
            this.myImageView=itemView.findViewById(R.id.imOrderImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemDecrease(view, getAdapterPosition());

        }
//        ViewHolder(View itemView) {
//            super(itemView);
//            myPriceView = itemView.findViewById(R.id.tvOrderProductPrice);
//            set = itemView.findViewById(R.id.buttonSetQuantity);
//            //myQuantityView = itemView.findViewById(R.id.orderQuantityDrink);
//            myDescriptionView = itemView.findViewById(R.id.tvOrderProductDescription);
//            itemView.setOnClickListener(this);
//        }
//        @Override
//        public void onClick(View view) {
//            if (mAddClickListener != null) mAddClickListener.onItemAdd(view, getAdapterPosition());
//        }

    }
//    private class MyCustomEditTextListener implements TextWatcher {
//        private int position;
//
//        public void updatePosition(int position) {
//            this.position = position;
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//            // no op
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
////            if(!charSequence.toString().isEmpty()){
////                editQuantity[position]=Integer.parseInt(charSequence.toString());
////            }
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    }
    // convenience method for getting data at click position
    public ProductTransaction getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemAdd(View view, int position);
        void onItemDecrease(View view, int position);
    }
}