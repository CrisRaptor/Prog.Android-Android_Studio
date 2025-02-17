package com.example.cristianexamenbbdd.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cristianexamenbbdd.R;

public class Adapter extends ArrayAdapter<Data> {
        private Data[] data;

        public Adapter(@NonNull Context context, Data[] data) {
            super(context, R.layout.elemento, data);
            this.data = data;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater mostrado = LayoutInflater.from(getContext());
            View element = mostrado.inflate(R.layout.elemento, parent,false);
            final ImageView imagen = element.findViewById(R.id.portada);
            imagen.setImageResource(data[position].getImg());
            final TextView text2 = element.findViewById(R.id.titulo);
            text2.setText(data[position].getTitulo());

            return element;
        }
}
