package com.coxtunes.androidnewshoppingcart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coxtunes.androidnewshoppingcart.R;
import com.coxtunes.androidnewshoppingcart.model.Food;

import io.realm.Realm;

public class FoodDetails extends AppCompatActivity {

    private Food foodmodel;
    private TextView foodName,foodPrice;
    private Button addToCartBtn;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        realm= Realm.getDefaultInstance();
        foodName = findViewById(R.id.textView3);
        foodPrice = findViewById(R.id.textView4);
        addToCartBtn = findViewById(R.id.button);
        foodmodel = (Food) getIntent().getSerializableExtra("food");
        if (foodmodel != null) {
            foodName.setText(foodmodel.getFoodName());
            foodPrice.setText(String.valueOf(foodmodel.getFoodPrice()));
        }

        // Add food to cart in realm
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Auto Primary Key Generate using own logic
                Number autogenId = realm.where(Food.class).max("id");
                int nextid;
                if (autogenId == null)
                {
                    nextid = 1;
                }
                else
                {
                    nextid = autogenId.intValue() + 1;
                }
                Log.d("debug",String.valueOf(nextid));
                foodmodel.setId(nextid);
                ////////////////////End logic ///////////////////////

                // Add to cart
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Food Food = realm.where(Food.class).equalTo("foodName", foodmodel.getFoodName()).findFirst();
                        if (Food != null) {
                            Toast.makeText(getApplicationContext(), "This food already Added to Cart", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Food food = realm.createObject(Food.class,foodmodel.getId());
                            food.setFoodName(foodmodel.getFoodName());
                            food.setFoodPrice(foodmodel.getFoodPrice());
                            food.setFoodquantity(1);
                            Toast.makeText(FoodDetails.this, "Food Added to Cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
