package com.example.t5ejercicio8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

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
        View element = LayoutInflater.from(context).inflate(R.layout.element,parent,false);
        final ImageView image = element.findViewById(R.id.image);
        image.setImageResource(data.get(position).getImagen());
        final CheckBox checkBox = element.findViewById(R.id.check);
        checkBox.setChecked(data.get(position).isCheck());
        final TextView nombre = element.findViewById(R.id.name);
        nombre.setText(data.get(position).getNombre());

        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.get(position).setCheck(!data.get(position).isCheck());
                checkBox.setChecked(data.get(position).isCheck());
            }
        });
        return element;
    }

    public ArrayList<Data> getData() {
        return data;
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