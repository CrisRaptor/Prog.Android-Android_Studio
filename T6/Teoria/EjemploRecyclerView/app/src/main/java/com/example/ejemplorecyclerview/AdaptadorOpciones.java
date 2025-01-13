package com.example.ejemplorecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorOpciones extends RecyclerView.Adapter<OpcionViewHolder> implements View.OnClickListener {

    private ArrayList<Opciones> datos;
    private View.OnClickListener listener;

    public AdaptadorOpciones(ArrayList<Opciones> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public OpcionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.opciones, parent, false);
        itemView.setOnClickListener(this);
        OpcionViewHolder ovh = new OpcionViewHolder(itemView);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OpcionViewHolder holder, int position) {
        Opciones item = datos.get(position);
        holder.bindOpcion(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }
}

class OpcionViewHolder extends RecyclerView.ViewHolder{
    private TextView titulo,subtitulo;
    private ImageView icono;

    public OpcionViewHolder(@NonNull View itemView) {
        super(itemView);

        titulo = itemView.findViewById(R.id.titulo);
        subtitulo = itemView.findViewById(R.id.subtitulo);
        icono = itemView.findViewById(R.id.icono);
    }

    public void bindOpcion(Opciones o){
        titulo.setText(o.getTitulo());
        subtitulo.setText(o.getSubtitulo());
        icono.setImageResource(o.getIcono());

    }
}
