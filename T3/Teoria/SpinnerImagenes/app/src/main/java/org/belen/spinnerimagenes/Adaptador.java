package org.belen.spinnerimagenes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Datos> {

    private ArrayList<Datos> datos;

    public Adaptador(Context context, ArrayList<Datos> datos){
        super(context,R.layout.elemento,datos);
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elemento,parent,false);

        ImageView icono = elemento.findViewById(R.id.icono);
        icono.setImageResource(datos.get(position).getIcono());

        return elemento;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.elemento,parent,false);
        }

        ImageView icono = convertView.findViewById(R.id.icono);
        Datos elemento = getItem(position);

        if (elemento!=null){
            icono.setImageResource(elemento.getIcono());
        }

        return convertView;
    }
}
