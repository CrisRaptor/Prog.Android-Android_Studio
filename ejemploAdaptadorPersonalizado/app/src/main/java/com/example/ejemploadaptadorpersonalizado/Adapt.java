package com.example.ejemploadaptadorpersonalizado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adapt extends ArrayAdapter<Data> {
    private Data[] data;

    public Adapt(@NonNull Context context, Data[] data) {
        super(context, R.layout.element, data);
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View element = mostrado.inflate(R.layout.element, parent,false);
        final TextView text1 = element.findViewById(R.id.text1);
        text1.setText(data[position].getText1());
        final TextView text2 = element.findViewById(R.id.text2);
        text2.setText(data[position].getText2());

        return element;
    }
}
