package com.timotiushanselkenny.uas;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ProductListFragment.ListItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemClick(int id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if(fragmentContainer!=null){
            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setId(id);

            FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
            fragmentManager
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }else{
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_PRODUCT_ID,(int)id);
            startActivity(intent);
        }
    }

    public void onClickGoToCart(View view) {
        Intent i_1 = new Intent(this, CartActivity.class);
        startActivity(i_1);
    }
}