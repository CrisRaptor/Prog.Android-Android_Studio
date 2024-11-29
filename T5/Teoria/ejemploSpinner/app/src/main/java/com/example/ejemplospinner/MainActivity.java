package com.example.ejemplospinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
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

        //Instancia el listado
        final Spinner list = findViewById(R.id.spinner);

        //Crea la fuente de datos
        final String[] datos = {
                "Opcion 1","Opcion 2","Opcion 3","Opcion 4","Opcion 5"
        };

        //Crea el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);

        //Asigna el adaptador
        list.setAdapter(adapter);

        //Añade listener
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity.this, "Has hecho clic en "+adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

                //Toast.makeText(MainActivity.this, "Has hecho clic en "+adapterView.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}