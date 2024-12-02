package com.example.ejemploadaptadorpersonalizado;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo los datos a mostrar en el arrayadapter
        Data[] data = {
                new Data("Linea superior 1","Linea inferior 1"),
                new Data("Linea superior 2","Linea inferior 2"),
                new Data("Linea superior 3","Linea inferior 3"),
                new Data("Linea superior 4","Linea inferior 4"),
                new Data("Linea superior 5","Linea inferior 5"),
                new Data("Linea superior 6","Linea inferior 6")
        };

        //Recuperar el listado
        final ListView list = findViewById(R.id.list);

        //Incorporar cabezera (opcional)
        View cabecera = getLayoutInflater().inflate(R.layout.cabecera,null);
        list.addHeaderView(cabecera);

        //Crear adaptador perosnalizado
        Adapt myAdapter = new Adapt(this,data);
        list.setAdapter(myAdapter);

        //Crea listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selected1 = ((Data)adapterView.getItemAtPosition(position)).getText1();
                String selected2 = ((Data)adapterView.getItemAtPosition(position)).getText2();
                Toast.makeText(MainActivity.this, "Titulo: "+selected1, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Subtitulo: "+selected2, Toast.LENGTH_SHORT).show();

            }
        });

    }
}