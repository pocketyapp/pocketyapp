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

public class ElementoIngresosAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Ingreso> arrayList;

    public ElementoIngresosAdapter(Context context, int layout, ArrayList<Ingreso> arrayList) {
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
        TextView textDescripcion, textCantidad, textFecha;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout, null);
            viewHolder.textDescripcion = (TextView) convertView.findViewById(R.id.tvDescripcion);
            viewHolder.textCantidad = (TextView) convertView.findViewById(R.id.tvCantidad);
            viewHolder.textFecha = (TextView) convertView.findViewById(R.id.tvFecha);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Ingreso ingreso = arrayList.get(position);

        viewHolder.textDescripcion.setText(ingreso.getDescripción());
        String cantidadEURO = currencyFormatter(ingreso.getCantidad());
        viewHolder.textCantidad.setText(String.valueOf(cantidadEURO));
        viewHolder.textFecha.setText(ingreso.getFecha());

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
