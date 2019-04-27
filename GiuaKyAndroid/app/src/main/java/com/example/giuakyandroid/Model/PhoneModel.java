package com.example.giuakyandroid.Model;

public class PhoneModel {
    private int Id;
    private String product_name;
    private int price;
    private String description;
    private String producer;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

}