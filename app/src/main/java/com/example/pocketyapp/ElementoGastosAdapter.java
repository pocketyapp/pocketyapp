package com.example.pocketyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pocketyapp.ElementoIngresosAdapter;
import com.example.pocketyapp.Gasto;
import com.example.pocketyapp.Ingreso;
import com.example.pocketyapp.R;

import java.util.ArrayList;

public class ElementoGastosAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private ArrayList<Gasto> arrayList;

    public ElementoGastosAdapter(Context context, int layout, ArrayList<Gasto> arrayList) {
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
        final Gasto gasto = arrayList.get(position);

        viewHolder.textDescripcion.setText(gasto.getDescripción());
        viewHolder.textCantidad.setText(String.valueOf(gasto.getCantidad()));
        viewHolder.textFecha.setText(gasto.getFecha());

        return convertView;
    }
}
