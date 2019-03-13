package com.example.listview;

public class ContactMedel{
    private String name;
    private String phoneNumber;
    private String address;
    private String city;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ContactMedel() {
    }

    public ContactMedel(String name, String phoneNumber, String address, String city) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
    }
}
