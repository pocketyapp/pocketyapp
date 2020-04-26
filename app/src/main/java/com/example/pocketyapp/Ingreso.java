package com.example.pocketyapp;

public class Ingreso {
    private String Name;
    private int Quantity;

    public Ingreso() {
    }

    public Ingreso(String name, int quantity) {
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
