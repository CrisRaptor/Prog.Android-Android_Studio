package com.example.tema7_ej8;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    private ArrayList<DatosSpinner> datos;
    private Context context;

    public SpinnerAdapter(ArrayList<DatosSpinner> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mostrado = LayoutInflater.from(context);
        View elemento = mostrado.inflate(R.layout.spinner_item, null);

        ImageView img = (ImageView) elemento.findViewById(R.id.imgSpinner);
        img.setImageResource(datos.get(i).getImg());

        return elemento;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        }

        ImageView icono = convertView.findViewById(R.id.imgSpinner);
        DatosSpinner elemento = datos.get(position);

        if (elemento!=null){
            icono.setImageResource(elemento.getImg());
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return datos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
