package com.coxtunes.androidnewshoppingcart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coxtunes.androidnewshoppingcart.R;
import com.coxtunes.androidnewshoppingcart.cart.Cart;
import com.coxtunes.androidnewshoppingcart.model.Food;

import java.util.List;

import io.realm.Realm;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private List<Food> foodList;
    private int minteger = 1;
    private Realm realm;
    public static double total = 0;

    // FOR DELETE
    OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int id, double total);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public CartAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_food_cart, viewGroup, false);
        return new CartAdapter.MyViewHolder(itemView, foodList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.MyViewHolder holder, final int i) {

        holder.foodname.setText(foodList.get(i).getFoodName());
        holder.foodprice.setText("$ " + foodList.get(i).getFoodPrice());
        holder.quantity.setText(String.valueOf(foodList.get(i).getFoodquantity()));

        holder.addqtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minteger = minteger + 1;
                holder.quantity.setText("" + minteger);
                total = Double.valueOf(foodList.get(i).getFoodPrice()) * minteger;
                holder.foodprice.setText("$"+total);
            }
        });

        holder.removeqtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (minteger != 1) {
                    minteger = minteger - 1;
                    holder.quantity.setText("" + minteger);
                    total = Double.valueOf(foodList.get(i).getFoodPrice()) * minteger;
                    holder.foodprice.setText("$"+total);
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        if (foodList == null) {
            return 0;
        }
        return foodList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodname, foodprice;
        ImageView delete;
        Button addqtn, removeqtn;
        TextView quantity;

        MyViewHolder(View view, final List<Food> foodList) {
            super(view);
            foodname = view.findViewById(R.id.textView);
            foodprice = view.findViewById(R.id.textView2);
            addqtn = view.findViewById(R.id.addqtn);
            removeqtn = view.findViewById(R.id.removeqtn);
            quantity = view.findViewById(R.id.totalqtn);
            delete = view.findViewById(R.id.imageView);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int id = foodList.get(getAdapterPosition()).getId();
                    mListener.OnItemClick(id, total);
                }
            });
        }
    }

}
