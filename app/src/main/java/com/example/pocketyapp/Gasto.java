package com.example.pocketyapp;

import java.util.Date;

public class Gasto {
    private String Descripción;
    private int Cantidad;
    private String Fecha;

    public Gasto() {
    }

    public Gasto(String descripción, int cantidad, String fecha) {
        Descripción = descripción;
        Cantidad = cantidad;
        Fecha = fecha;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescripción(String descripción) {
        Descripción = descripción;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
