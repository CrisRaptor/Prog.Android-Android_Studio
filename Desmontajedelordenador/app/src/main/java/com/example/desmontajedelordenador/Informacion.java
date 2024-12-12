package com.example.desmontajedelordenador;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Informacion extends AppCompatActivity {
    private String elemento = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_contenido);

        final Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);

        Bundle extras = getIntent().getExtras();
        elemento = extras.getString("Elemento");

        if (elemento != null){
            asignarContenido();
        }
    }

    private void asignarContenido(){
        TextView titulo = findViewById(R.id.titulo);
        TextView introduccion = findViewById(R.id.introduccion);
        ImageView imagen = findViewById(R.id.imagen);
        TextView descripcion = findViewById(R.id.descripcion);
        registerForContextMenu(descripcion);

        String[] contenido;

        switch (elemento){
            case "Carcasa":
                contenido = getResources().getStringArray(R.array.carcasa_contenido);
                cargarContenido(contenido);
                break;
            case "Placa Base":
                contenido = getResources().getStringArray(R.array.placa_contenido);
                cargarContenido(contenido);
                break;
            case "Procesador":
                contenido = getResources().getStringArray(R.array.cpu_contenido);
                cargarContenido(contenido);
                break;
            case "Memoria RAM":
                contenido = getResources().getStringArray(R.array.ram_contenido);
                cargarContenido(contenido);
                break;
            case "Almacenamiento":
                contenido = getResources().getStringArray(R.array.disco_contenido);
                cargarContenido(contenido);
                break;
            case "Refrigeración":
                contenido = getResources().getStringArray(R.array.ventilacion_contenido);
                cargarContenido(contenido);
                break;
            case "Tarjeta Gráfica":
                contenido = getResources().getStringArray(R.array.grafica_contenido);
                cargarContenido(contenido);
                break;
            case "Fuente de Alimentación":
                contenido = getResources().getStringArray(R.array.alimentacion_contenido);
                cargarContenido(contenido);
                break;
            case "Autor":
                contenido = getResources().getStringArray(R.array.autor_contenido);
                cargarContenido(contenido);
                Button botonContactar = findViewById(R.id.btnContactar);
                botonContactar.setVisibility(View.VISIBLE);
                botonContactar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"cgarcha0608@g.educaand.es"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Sobre la aplicación...");
                        intent.putExtra(Intent.EXTRA_TEXT, "Buenas Cristian,");
                        startActivity(intent);
                    }
                });
                break;
            case "App":
                contenido = getResources().getStringArray(R.array.app_contenido);
                cargarContenido(contenido);
                break;
            default:
                break;
        }
    }

    private void cargarContenido(String[] contenido){
        TextView titulo = findViewById(R.id.titulo);
        TextView introduccion = findViewById(R.id.introduccion);
        ImageView imagen = findViewById(R.id.imagen);
        TextView descripcion = findViewById(R.id.descripcion);

        if (contenido.length >= 4){
            titulo.setText(contenido[0]);
            introduccion.setText(contenido[1]);
            imagen.setImageResource(getResources().getIdentifier(contenido[2], "drawable", getPackageName()));
            descripcion.setText(contenido[3]);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView titulo = findViewById(R.id.titulo);
        TextView introduccion = findViewById(R.id.introduccion);
        ImageView imagen = findViewById(R.id.imagen);
        TextView descripcion = findViewById(R.id.descripcion);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url;
        switch (elemento){
            case "Carcasa":
                url = "https://www.infootec.net/tipos-carcasas-cajas-ordenador/";
                break;
            case "Placa Base":
                url = "";
                break;
            case "Procesador":
                url = "";
                break;
            case "Memoria RAM":
                url = "";
                break;
            case "Almacenamiento":
                url = "";
                break;
            case "Refrigeración":
                url = "";
                break;
            case "Tarjeta Gráfica":
                url = "";
                break;
            case "Fuente de Alimentación":
                url = "";
                break;
            case "Autor":
                url = "";
                break;
            case "App":
                url = "";
                break;
            default:
                url = "https://github.com/CrisRaptor";
                break;
        }
        intent.setData(Uri.parse(url));
        startActivity(intent);
        return super.onContextItemSelected(item);
    }
}
