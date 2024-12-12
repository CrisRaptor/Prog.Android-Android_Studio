package com.example.desmontajedelordenador.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desmontajedelordenador.R;

import java.util.ArrayList;

public class Adapt extends BaseAdapter {

    private ArrayList<Data> data;
    private Context context;

    public Adapt(ArrayList<Data> data, Context context) {
        super();
        this.data = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View element = LayoutInflater.from(context).inflate(R.layout.element, parent, false);
        final ImageView imagen = element.findViewById(R.id.image);
        imagen.setImageResource(data.get(position).getImagen());
        final TextView nombre = element.findViewById(R.id.name);
        nombre.setText(data.get(position).getNombre());

        // Ajustar el span en función de la posición
        if (nombre.equals("info-1") || nombre.equals("info-8")) {
            view.setVisibility(View.GONE);
        } 
        return element;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}