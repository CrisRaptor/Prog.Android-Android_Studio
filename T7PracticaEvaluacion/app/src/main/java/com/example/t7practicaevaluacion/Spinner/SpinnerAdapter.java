package com.example.t7practicaevaluacion.Spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.t7practicaevaluacion.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private List<Avatar> contacts;
    private Context context;
    private int layout;

    public SpinnerAdapter(List<Avatar> contacts, Context context, int layout) {
        this.contacts = contacts;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View elemento = LayoutInflater.from(context).inflate(layout, viewGroup, false);

        final ImageView imagen = elemento.findViewById(R.id.spinnerPicture);
        imagen.setImageResource(contacts.get(i).getAvatar());

        return elemento;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}
