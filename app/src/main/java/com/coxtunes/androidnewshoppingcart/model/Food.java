package com.coxtunes.androidnewshoppingcart.model;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Food extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;

    private String foodName;
    private double foodPrice;

    public Food() {
    }

    public Food(String foodName, double foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
}
