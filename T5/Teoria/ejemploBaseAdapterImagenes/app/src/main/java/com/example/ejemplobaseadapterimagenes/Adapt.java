package com.example.ejemplobaseadapterimagenes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View element = LayoutInflater.from(context).inflate(R.layout.element, parent,false);
        final ImageView icon = element.findViewById(R.id.icon);
        icon.setImageResource(data.get(position).getIcon());
        final TextView text = element.findViewById(R.id.text);
        text.setText(data.get(position).getText());
        return element;
    }
}
