package com.timotiushanselkenny.uas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.timotiushanselkenny.uas.Product;
import com.timotiushanselkenny.uas.R;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView price;
        TextView id;
        ImageView image;
    }

    public ProductAdapter(Context context, ArrayList<Product> users) {
        super(context, R.layout.product_list, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.product_list, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvProductName);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tvProductPrice);
            viewHolder.id = (TextView) convertView.findViewById(R.id.tvProductId);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imProductImage);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.name.setText(user.getName());
        viewHolder.price.setText(user.getPrice().toString());
        viewHolder.id.setText(user.getId().toString());
        String imageUri = user.getImg().toString();
        Picasso.with(this.getContext()).load(imageUri).into(viewHolder.image);
        // Return the completed view to render on screen
        return convertView;
    }
}