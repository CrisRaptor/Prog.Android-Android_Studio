package com.example.desmontajedelordenador;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.desmontajedelordenador.adapter.Adapt;
import com.example.desmontajedelordenador.adapter.Data;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        final Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);

        //FUTURO - Crear un element antes de GridView,con una imagen y el titulo "INTRODUCCION"
        //Este apartado explica el tema de la aplicacion y te introduce a los componentes

        //Instancia el listado
        final GridView list = findViewById(R.id.grid);

        //Crea la fuente de datos
        String[] elementos = getResources().getStringArray(R.array.listaComponentes);
        ArrayList<Data> datos = new ArrayList<>();
        for (String elemento : elementos) {
            String[] partes = elemento.split(",");
            String nombreImagen = partes[0];
            String texto = partes[1];

            int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
            datos.add(new Data(idImagen, texto));
        }

        //Crea el adaptador
        Adapt miAdapter = new Adapt(datos, this);
        list.setAdapter(miAdapter);

        //Crea listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (adapterView != null) {
                    String selected = ((Data) adapterView.getItemAtPosition(position)).getNombre();
                    Toast.makeText(Inicio.this, "Componente " + selected, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
