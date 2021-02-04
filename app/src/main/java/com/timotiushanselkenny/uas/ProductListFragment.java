package com.timotiushanselkenny.uas;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.timotiushanselkenny.uas.adapter.ProductAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListFragment extends ListFragment {
    ArrayList<Product> products = new ArrayList<>();
    EzCommerceAPIEndpointInterface mApiInterface;
    private ListView recyclerView;
    private TextView viewId;
    private ProductAdapter productAdapter;
    public interface ListItemListener {
        void onItemClick(int id);
    }

    ListItemListener listener;

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity() instanceof  ListItemListener) {
            listener = (ListItemListener) getActivity();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Context a= this.getContext();
        mApiInterface = EzEcommerceAPIClient.getClient().create(EzCommerceAPIEndpointInterface.class);
        Call<Products> kontakCall = mApiInterface.getBook();
        kontakCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products>
                    response) {
                ArrayList<Product> productPrivate = response.body().getProducts();
                productAdapter = new ProductAdapter(a, productPrivate);
                setListAdapter(productAdapter);
                Log.d("Retrofit Get", "Jumlah data product: " +
                        String.valueOf(productPrivate.size()));
            }
            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        viewId = (TextView) v.findViewById(R.id.tvProductId);
        Integer Id = Integer.parseInt(viewId.getText().toString());
        if(listener != null) {
            listener.onItemClick(Id);
        }
    }
}
