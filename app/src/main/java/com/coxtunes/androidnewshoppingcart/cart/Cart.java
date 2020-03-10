package com.coxtunes.androidnewshoppingcart.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coxtunes.androidnewshoppingcart.MainActivity;
import com.coxtunes.androidnewshoppingcart.R;
import com.coxtunes.androidnewshoppingcart.adapter.CartAdapter;
import com.coxtunes.androidnewshoppingcart.model.Food;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Cart extends AppCompatActivity {

    private CartAdapter cartAdapter;
    private List<Food> cartfoodlist;
    private RecyclerView cartrecyclerview;
    private Realm realm;
    private TextView noitemtext;
    public TextView Total;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        realm = Realm.getDefaultInstance();
        noitemtext = findViewById(R.id.warning);
        Total = findViewById(R.id.totalPrice);
        cartrecyclerview = findViewById(R.id.cartrecyclerview);
        cartrecyclerview.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartrecyclerview.setLayoutManager(layoutManager);
        cartfoodlist = new ArrayList<>();
        // Set list data by realm data
        cartfoodlist = realm.where(Food.class).findAll();
        cartAdapter = new CartAdapter(this, cartfoodlist);
        cartrecyclerview.setAdapter(cartAdapter);

        totalPrice = CartAdapter.total;
        Total.setText("Total: " + totalPrice);

        // call for alltime Update price
        //calculateTotalPrice();


        // Delete Cart item callback
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, double total) {

                final RealmResults<Food> realmResults= realm.where(Food.class).equalTo("id",id).findAll();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realmResults.deleteAllFromRealm();
                        //alculateTotalPrice();
                    }
                });
                cartAdapter.notifyDataSetChanged();
            }
        });

    }

    private void calculateTotalPrice() {

        List<Food> foods= realm.where(Food.class).findAll();

        if (foods.isEmpty())
        {
            noitemtext.setVisibility(View.VISIBLE);
        }

        totalPrice = 0;
        for(Food b: foods){
            totalPrice+= b.getFoodPrice()*b.getFoodquantity();
        }

        Total.setText("Total: " + totalPrice);
    }

    public void gotolist(View view) {
        startActivity(new Intent(Cart.this, MainActivity.class));
    }
}
