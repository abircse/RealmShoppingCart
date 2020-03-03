package com.coxtunes.androidnewshoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.coxtunes.androidnewshoppingcart.adapter.FoodAdapter;
import com.coxtunes.androidnewshoppingcart.cart.Cart;
import com.coxtunes.androidnewshoppingcart.model.Food;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<Food> foodList;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        foodList = new ArrayList<>();
        adapter = new FoodAdapter(this, foodList);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        foodList.add(new Food("Burger",12.54));
        foodList.add(new Food("Sandwitch",10.25));
        foodList.add(new Food("Pizza",30.00));
        foodList.add(new Food("Matton Curry",12.11));
        foodList.add(new Food("Fried Rice",09.54));
        foodList.add(new Food("Sharma",12.54));

    }

    public void gotocart(View view) {

        startActivity(new Intent(MainActivity.this, Cart.class));
    }
}
