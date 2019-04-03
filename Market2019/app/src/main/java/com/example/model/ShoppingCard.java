package com.example.model;

import java.util.ArrayList;
import java.util.Calendar;

public class ShoppingCard {

    private String id;
    private String cardName;
    private String date;
    private ArrayList<Product> products = new ArrayList<>();

    public ShoppingCard() {
        this.id = Calendar.getInstance().getTime().toString();
    }


    public ShoppingCard(String cardName, String date, ArrayList<Product> products) {
        this.id = Calendar.getInstance().getTime().toString();
        this.cardName = cardName;
        this.date = date;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}
