package com.timotiushanselkenny.uas;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.timotiushanselkenny.uas.database.DatabaseHandler;
import com.timotiushanselkenny.uas.database.ProductTransaction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvPrice;
    private Button btnOrder;
    EzCommerceAPIEndpointInterface mApiInterface;
    private int id;
    private String image;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.containsKey("productId")) {
            id = savedInstanceState.getInt("productId");
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.product_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        final View rootView = getView();
        tvName = rootView.findViewById(R.id.tvName);
        tvDescription = rootView.findViewById(R.id.tvDescription);
        tvPrice = rootView.findViewById(R.id.tvPrice);
        btnOrder = rootView.findViewById(R.id.btnBuy);
        mApiInterface = EzEcommerceAPIClient.getClient().create(EzCommerceAPIEndpointInterface.class);
        Call<Products> productCall = mApiInterface.getBookDetail(String.valueOf(id));
        productCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products>
                    response) {
                int statusCode = response.code();
//                String a = response.body().getName();
                Product product = response.body().getProducts().get(0);
                image = product.getImg().toString();
                tvName.setText(product.getName());
                tvDescription.setText(product.getDescription());
                tvPrice.setText(product.getPrice().toString());
//                Log.d("Retrofit Get", "Product name " +
//                        product.getName());
            }
            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View view) {
                // Code here executes on main thread after user presses button
                TextView Description = (TextView) rootView.findViewById(R.id.tvDescription);
                TextView Name = (TextView) rootView.findViewById(R.id.tvName);
                TextView Price = (TextView) rootView.findViewById(R.id.tvPrice);
                String Prices = Price.getText().toString();
                ProductTransaction productTransaction = new ProductTransaction(Name.getText().toString(),Description.getText().toString(),Double.parseDouble(Prices),1,image);
                DatabaseHandler databaseHelper = DatabaseHandler.getInstance(getContext());
                try {
                    databaseHelper.addRecord(productTransaction);
                    Toast.makeText(getContext(), "Data has been saved", Toast.LENGTH_LONG).show();
                }catch (Exception e)
                {
                    Log.e("exception", "Error while trying to add transaction to database");
                }
            }
        });

//        Product workout = Product;
//
//        tvName.setText(workout.getName());
//        tvDescription.setText(workout.getDescription());

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("productId", id);
    }
}
