package com.example.t5ejercicio5;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo los datos a mostrar en el arrayadapter
        Data[] data = {
                new Data("Juan Amigo","644330931"),
                new Data("Alberto Dominguez","655421153"),
                new Data("Alejandro Fernandez","601374832"),
                new Data("Tio Miguel","685449231"),
                new Data("Lucia Lopez","600658483"),
        };

        //Recuperar el listado
        final ListView list = findViewById(R.id.list);

        //Incorporar cabezera (opcional)
        View cabecera = getLayoutInflater().inflate(R.layout.cabecera,null);
        list.addHeaderView(cabecera);

        //Crear adaptador personalizado
        Adapt myAdapter = new Adapt(this,data);
        list.setAdapter(myAdapter);

        //Crea listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selected1 = ((Data)adapterView.getItemAtPosition(position)).getNombre();
                String selected2 = ((Data)adapterView.getItemAtPosition(position)).getNumero();
                Toast.makeText(MainActivity.this, "Nombre de contacto: "+selected1, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Numero de telefono: "+selected2, Toast.LENGTH_SHORT).show();

            }
        });
    }
}