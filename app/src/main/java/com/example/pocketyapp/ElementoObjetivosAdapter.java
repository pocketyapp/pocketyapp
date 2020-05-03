package com.example.pocketyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;

public class ElementoObjetivosAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Objetivo> arrayList;

    public ElementoObjetivosAdapter(Context context, int layout, ArrayList<Objetivo> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView textNombre, textCantidad;
        ImageView ivObjetivo, ivMoney;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout, null);
            viewHolder.textNombre = (TextView) convertView.findViewById(R.id.textNombre);
            viewHolder.textCantidad = (TextView) convertView.findViewById(R.id.textCantidad);
            viewHolder.ivObjetivo = (ImageView) convertView.findViewById(R.id.ivObjective);
            viewHolder.ivMoney = (ImageView) convertView.findViewById(R.id.ivMoney);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Objetivo objetivo = arrayList.get(position);

        viewHolder.textNombre.setText(objetivo.getName());
        String cantidadEURO = currencyFormatter(objetivo.getQuantity());
        viewHolder.textCantidad.setText(String.valueOf(cantidadEURO));

        return convertView;
    }

    //FORMAT NUMBER
    public String currencyFormatter(int num){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("EUR"));
        return format.format(num);
    }
}
