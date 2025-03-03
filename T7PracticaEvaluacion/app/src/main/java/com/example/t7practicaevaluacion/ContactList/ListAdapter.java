package com.example.t7practicaevaluacion.ContactList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t7practicaevaluacion.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<Contact> contacts;
    private Context context;
    private int layout;

    public ListAdapter(List<Contact> contacts, Context context, int layout) {
        this.contacts = contacts;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View elemento = LayoutInflater.from(context).inflate(layout, viewGroup, false);

        final ImageView imagen = elemento.findViewById(R.id.elementPicture);
        imagen.setImageResource(contacts.get(i).getAvatar());

        final TextView nombre = elemento.findViewById(R.id.elementName);
        nombre.setText(contacts.get(i).getName());

        final TextView numero = elemento.findViewById(R.id.elementPhone);
        numero.setText(contacts.get(i).getPhone());


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
