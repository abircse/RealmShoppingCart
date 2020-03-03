package com.coxtunes.androidnewshoppingcart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coxtunes.androidnewshoppingcart.R;
import com.coxtunes.androidnewshoppingcart.activity.FoodDetails;
import com.coxtunes.androidnewshoppingcart.model.Food;

import java.util.List;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private Context context;
    private List<Food> foodList;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_food, viewGroup, false);
        return new FoodAdapter.MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, final int i) {

        holder.foodname.setText(foodList.get(i).getFoodName());
        holder.foodprice.setText("$ "+ foodList.get(i).getFoodPrice());

    }

    @Override
    public int getItemCount() {

        if (foodList == null) {
            return 0;
        }
        return foodList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodname,foodprice;

        MyViewHolder(View view) {
            super(view);
            foodname=view.findViewById(R.id.textView);
            foodprice=view.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position= getAdapterPosition();
                    Food food= foodList.get(position);
                    Intent i= new Intent(context, FoodDetails.class);
                    i.putExtra("food",food);
                    context.startActivity(i);

                }
            });

        }
    }


}
