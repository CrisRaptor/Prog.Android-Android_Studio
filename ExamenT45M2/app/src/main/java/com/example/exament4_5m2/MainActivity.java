package com.example.exament4_5m2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageView telefono = findViewById(R.id.imagenTelefono);
        telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:666666666"));
                startActivity(intent);
            }
        });

        final ImageView correo = findViewById(R.id.imagenCorreo);
        correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"anime");
                intent.putExtra(Intent.EXTRA_TEXT,"Me gustaría ampliar información");
                intent.putExtra(Intent.EXTRA_EMAIL, new
                        String[]{"correo@gmail.com"});
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MainActivity.this,ActivityVerInfo.class);
        intent.putExtra("eleccion",item.getTitle());
        startActivity(intent);
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final TextView etiqueta = findViewById(R.id.etiqueta);

        int opcion = item.getItemId();
        if (opcion == R.id.MnOp1) { //Peliculas
            etiqueta.setText(item.getTitle().toString());
            resetVisibility();
            return true;
        } else if (opcion == R.id.MnOp2) { //Series
            etiqueta.setText(item.getTitle().toString());
            findViewById(R.id.list).setVisibility(View.GONE);

            //Hacer visible el Spinner y Grid
            findViewById(R.id.spinner).setVisibility(View.VISIBLE);
            findViewById(R.id.grid).setVisibility(View.VISIBLE);

            //Cargar el spinner
            final Spinner spinner = findViewById(R.id.spinner);
            String[] datos = {"Shonen", "Shojo"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);

            //Asigna el adaptador
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    cargarSeries(adapterView.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            return true;
        } else if (opcion == R.id.MnOp3) { //Manga
            etiqueta.setText(item.getTitle().toString());
            resetVisibility();
            return true;
        } else if (opcion == R.id.MnOp1_1) { //Studio Ghibli
            final ListView list = findViewById(R.id.list);
            list.setVisibility(View.VISIBLE);

            //Crea la fuente de datos
            final String[] datos = {"Mi vecino Totoro", "El castillo ambulante", "El viaje de Chihiro", "Susurros del corazón", "Haru en el reino de los gatos"};

            //Crea el adaptador
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);

            //Asigna el adaptador
            list.setAdapter(adapter);

            /*
            Menu de contexto NO FINALIZADO pero funcional
            (falta llevar el nombre de la pelicula al ActivityVerInfo,
            y registrar solo las 2 primeras)
            */
            registerForContextMenu(list);
            return true;
        } else if (opcion == R.id.MnOp1_2) { //Clamp
            //Abre la web de Clamp
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://mubi.com/es/cast/clamp"));
            startActivity(intent);
            return true;
        } else if (opcion == R.id.MnOp3_1 || opcion == R.id.MnOp3_2) { //Generos de manga
            //Comienza la actividad ActivityManga con un extra mostrando el género seleccionado
            Intent intent = new Intent(MainActivity.this, ActivityManga.class);
            intent.putExtra("eleccion", (opcion == R.id.MnOp3_1) ? "Shonen" : "Shojo");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Oculta todos los listados
    private void resetVisibility() {
        findViewById(R.id.spinner).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.grid).setVisibility(View.GONE);
    }

    //Carga el GridView con nombres de series segun el genero
    private void cargarSeries(String genero) {
        //Cargar el grid
        final GridView grid = findViewById(R.id.grid);
        if (genero.equals("Shonen")) {
            //Crea la fuente de datos
            String[] datos = {"Guardianes de la noche", "Naruto", "Ataque de los titanes", "Los siete pecados capitales", "Desaparecido"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
            //Asigna el adaptador
            grid.setAdapter(adapter);
        } else if (genero.equals("Shojo")) {
            //Crea la fuente de datos
            String[] datos = {"Sakura", "Fruit Basket"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
            //Asigna el adaptador
            grid.setAdapter(adapter);
        }
    }
}