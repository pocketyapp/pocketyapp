package com.example.pocketyapp;

public class Objetivo {
    private String Name;
    private int Quantity;

    public Objetivo() {
    }

    public Objetivo(String name, int quantity) {
        Name = name;
        Quantity = quantity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
