package com.example.apicat;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adapt extends ArrayAdapter<BreedData> {
    private BreedData[] data;

    public Adapt(@NonNull Context context, BreedData[] data) {
        super(context, R.layout.breed, data);
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View element = mostrado.inflate(R.layout.breed, parent,false);
        final TextView name = element.findViewById(R.id.name);
        name.setText(data[position].getName());
        final TextView origin = element.findViewById(R.id.origin);
        origin.setText(data[position].getOrigin());
        final TextView life_span = element.findViewById(R.id.life_span);
        life_span.setText(data[position].getLife_span());
        final TextView weight = element.findViewById(R.id.weight);
        weight.setText(data[position].getWeight());
        final TextView temperamnet = element.findViewById(R.id.temperament);
        temperamnet.setText(data[position].getTemperament());
        final TextView description = element.findViewById(R.id.description);
        description.setText(data[position].getDescription());
        return element;
    }
}
