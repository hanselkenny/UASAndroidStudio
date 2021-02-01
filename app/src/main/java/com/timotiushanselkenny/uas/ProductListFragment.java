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
        View view = inflater.inflate(R.layout.fragment_product_list,container,false);

        return view;
//        String[] arrNames = new String[Product.arrProducts.length];
//        View view = inflater.inflate(R.layout.product_list, container, false);
//        for(int i=0; i<arrNames.length; i++) {
//            arrNames[i] = Product.arrProducts[i].Name;
//        }
//        recyclerView = this.findViewById(R.id.drink_recycler_view);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        drinks = Drink.createDrinkList(200);
//        // Create adapter passing in the sample user data
//        drinkListAdapter = new DrinkListAdapter(this, drinks);
//        drinkListAdapter.setClickListener(this);
//        view.setAdapter(drinkListAdapter);
//        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(inflater.getContext(), android.R.layout.simple_list_item_1, arrNames);
//        setListAdapter(adapter);
//        return inflater.inflate(R.layout.product_list, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        if(products.isEmpty()){
//            products.add(new Product("The Power of HABIT", "The Power of HABIT: Why We Do What We Do in Life and Business. A young woman walks into a laboratory. Over the past two years, she has transformed almost every aspect of her life. She has quit smoking, run a marathon, and been promoted at work. The patterns inside her brain, neurologists discover, have fundamentally changed.",
//                    "business","https://images-na.ssl-images-amazon.com/images/I/51ejXdSceNL._AA300_.jpg","Charles Duhigg","hardcover",16.33,1)) ;
//            products.add( new Product("The Power of HABIT 2", "The Power of HABIT: Why We Do What We Do in Life and Business. A young woman walks into a laboratory. Over the past two years, she has transformed almost every aspect of her life. She has quit smoking, run a marathon, and been promoted at work. The patterns inside her brain, neurologists discover, have fundamentally changed.",
//                    "business","https://images-na.ssl-images-amazon.com/images/I/51ejXdSceNL._AA300_.jpg","Charles Duhigg","hardcover",16.33,2));
//        }
        // Create adapter passing in the sample user data
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

//        View rootView = getView();
//        recyclerView = rootView.findViewById(R.id.list);
//        recyclerView.s(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));

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
