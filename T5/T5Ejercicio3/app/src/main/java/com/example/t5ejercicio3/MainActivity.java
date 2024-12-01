package com.example.t5ejercicio3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

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

        //Recupera el elemento
        final GridView grid = findViewById(R.id.grid);
        TextView texto = findViewById(R.id.txtSeleccionado);

        //Crea la fuente de datos
        final String[] datos = getResources().getStringArray(R.array.listSeries);

        //Crea el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);

        //Asigna el adaptador
        grid.setAdapter(adapter);

        //Creo listener
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                texto.setText(adapterView.getItemAtPosition(position).toString());
            }
        });
    }
}