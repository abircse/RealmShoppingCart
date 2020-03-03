package com.coxtunes.androidnewshoppingcart.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.coxtunes.androidnewshoppingcart.MainActivity;
import com.coxtunes.androidnewshoppingcart.R;
import com.coxtunes.androidnewshoppingcart.adapter.CartAdapter;
import com.coxtunes.androidnewshoppingcart.model.Food;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Cart extends AppCompatActivity {

    private CartAdapter cartAdapter;
    private List<Food> cartfoodlist;
    private RecyclerView cartrecyclerview;
    private Realm realm;
    private TextView noitemtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        realm = Realm.getDefaultInstance();
        noitemtext = findViewById(R.id.warning);
        cartrecyclerview = findViewById(R.id.cartrecyclerview);
        cartrecyclerview.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        cartrecyclerview.setLayoutManager(layoutManager);
        cartfoodlist = new ArrayList<>();

        // Set list data by realm data
        cartfoodlist = realm.where(Food.class).findAll();
        cartAdapter = new CartAdapter(this,cartfoodlist);
        cartrecyclerview.setAdapter(cartAdapter);

        // Check food exit in list or not
        if (cartfoodlist.isEmpty())
        {
            noitemtext.setVisibility(View.VISIBLE);
        }
        else
        {
            noitemtext.setVisibility(View.GONE);
        }

        // Delete Cart item callback
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id) {

                Toast.makeText(Cart.this, "Clicked on delete button at position"+id, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void gotolist(View view) {
        startActivity(new Intent(Cart.this, MainActivity.class));
    }
}
