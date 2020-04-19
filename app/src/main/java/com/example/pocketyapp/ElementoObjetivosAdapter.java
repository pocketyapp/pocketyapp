package com.example.pocketyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
        viewHolder.textCantidad.setText(String.valueOf(objetivo.getQuantity()));

        return convertView;
    }
}
