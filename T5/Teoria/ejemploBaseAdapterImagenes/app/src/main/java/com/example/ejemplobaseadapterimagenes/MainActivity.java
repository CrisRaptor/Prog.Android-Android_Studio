package com.example.ejemplobaseadapterimagenes;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo los datos a mostrar en el arrayadapter
        ArrayList<Data> data = new ArrayList<>();
        data.add(new Data(R.drawable.consola,"Consola"));
        data.add(new Data(R.drawable.ordenador_fijo,"Ordenador"));
        data.add(new Data(R.drawable.ordenador_portatil,"Portatil"));
        data.add(new Data(R.drawable.reloj,"Reloj"));
        data.add(new Data(R.drawable.smartphone,"Smartphone"));
        data.add(new Data(R.drawable.tablet,"Tablet"));
        data.add(new Data(R.drawable.tv,"Television"));


        //Recuperar el listado
        final ListView list = findViewById(R.id.list);

        //Crear adaptador perosnalizado
        Adapt myAdapter = new Adapt(data,this);
        list.setAdapter(myAdapter);

        //Crea listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (adapterView != null){
                    String selected = ((Data)adapterView.getItemAtPosition(position)).getText();
                    Toast.makeText(MainActivity.this, "Elemento seleccionado: "+selected, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}