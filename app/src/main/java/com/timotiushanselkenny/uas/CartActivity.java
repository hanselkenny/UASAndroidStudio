package com.timotiushanselkenny.uas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timotiushanselkenny.uas.adapter.TransactionListAdapter;
import com.timotiushanselkenny.uas.database.DatabaseHandler;
import com.timotiushanselkenny.uas.database.ProductTransaction;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements TransactionListAdapter.ItemClickListener{
    private RecyclerView recyclerView;
    private TransactionListAdapter drinkListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = this.findViewById(R.id.order_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Create adapter passing in the sample user data
        DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);
        List<ProductTransaction> productTransactions = new ArrayList<>();
        try {
            productTransactions = databaseHelper.getAllRecord();
        }catch (Exception e)
        {
            Log.e("exception", "Error while trying to add transaction to database");
        }
        drinkListAdapter = new TransactionListAdapter(this, productTransactions);
        drinkListAdapter.setClickListener(this);
        recyclerView.setAdapter(drinkListAdapter);

        Double totalPrice=0.0;
        for (ProductTransaction product :productTransactions) {
            totalPrice = totalPrice + Double.parseDouble(product.getPrice()) * Double.parseDouble(product.getQuantity());
        }
        final TextView textView = (TextView)findViewById(R.id.tvTotalPrice);
        textView.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onItemDecrease(View view, int position) {
        EditText jumlah = (EditText) view.findViewById(R.id.etEditOrderQuantity);
        TextView id = (TextView) view.findViewById(R.id.tvOrderId);
        Integer quantity = Integer.parseInt(jumlah.getText().toString());
        DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);
        ProductTransaction productTransaction = new ProductTransaction();
        Integer totalDifference=0;
        try {
            productTransaction = databaseHelper.getProduct(Integer.parseInt(id.getText().toString()));
            totalDifference=Integer.parseInt(productTransaction.getQuantity());
            if(quantity>0){
                productTransaction.setmQuantity(quantity);
                databaseHelper.updateProduct(productTransaction);
            }else{
                databaseHelper.deleteModel(productTransaction);
            }
            final TextView textView = (TextView)findViewById(R.id.tvTotalPrice);
            Double totalPrice=Double.parseDouble(textView.getText().toString());
            int differenceQuantity = quantity-totalDifference;
            differenceQuantity=differenceQuantity<0?differenceQuantity*-1:differenceQuantity;
            totalPrice=totalPrice+(differenceQuantity*Double.parseDouble(productTransaction.getPrice().toString()));
            textView.setText(String.valueOf(totalPrice));
            Toast.makeText(getApplicationContext(), "Data has been saved", Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Log.e("exception", "Error while trying to add transaction to database");
        }
    }
}