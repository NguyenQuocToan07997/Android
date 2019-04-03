package com.example.model;

import java.util.ArrayList;

public class Product {

    private String name;
    private int price;
    private String weight;
//    private boolean isPayed;

    public Product(String name, int price, String weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
//        this.isPayed = isPayed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

//    public boolean isPayed() {
//        return isPayed;
//    }
//
//    public void setPayed(boolean payed) {
//        isPayed = payed;
//    }
}
