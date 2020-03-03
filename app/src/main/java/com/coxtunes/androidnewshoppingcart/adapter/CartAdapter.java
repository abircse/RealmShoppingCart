package com.coxtunes.androidnewshoppingcart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coxtunes.androidnewshoppingcart.R;
import com.coxtunes.androidnewshoppingcart.activity.FoodDetails;
import com.coxtunes.androidnewshoppingcart.model.Food;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private List<Food> foodList;

    OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int id);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }

    public CartAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_food_cart, viewGroup, false);
        return new CartAdapter.MyViewHolder(itemView,foodList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, final int i) {

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
        ImageView delete;

        MyViewHolder(View view,final List<Food> foodList) {
            super(view);
            foodname=view.findViewById(R.id.textView);
            foodprice=view.findViewById(R.id.textView2);
            delete = view.findViewById(R.id.imageView);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int id= foodList.get(getAdapterPosition()).getId();
                    mListener.OnItemClick(id);
                }
            });

        }
    }


}
