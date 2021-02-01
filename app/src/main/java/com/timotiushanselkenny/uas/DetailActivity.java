package com.timotiushanselkenny.uas;

import android.app.Activity;
import android.os.Bundle;

public class DetailActivity extends Activity {
    public static final String EXTRA_PRODUCT_ID="id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ProductDetailFragment productDetailFragment = (ProductDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag);
        int productId = (int) getIntent().getExtras().get(EXTRA_PRODUCT_ID);
        productDetailFragment.setId(productId);
    }

}
