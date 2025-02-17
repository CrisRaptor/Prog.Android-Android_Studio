package com.example.cristianexamenbbdd;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cristianexamenbbdd.database.BaseDatos;
import com.example.cristianexamenbbdd.list.Adapter;
import com.example.cristianexamenbbdd.list.Data;
import com.example.cristianexamenbbdd.preferences.PreferenceActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Data dato_seleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recuperamos los elementos
        final Button btnFavoritos = findViewById(R.id.btnFavoritos),
                btnCancelarFavorito = findViewById(R.id.btnCancelarFavorito),
                btnEliminar = findViewById(R.id.btnEliminar),
                btnCancelarEliminar = findViewById(R.id.btnCancelarEliminar);
        final LinearLayout layoutFavoritos = findViewById(R.id.layoutFavoritos),
                layoutEliminarFavoritos = findViewById(R.id.layoutEliminarFavoritos);
        final ListView lista_tendencias = findViewById(R.id.lista_tendencias),
                lista_personal = findViewById(R.id.lista_personal);

        //Inicializamos la base de datos
        BaseDatos baseDatos = new BaseDatos(this, "DBPeliculas", null, 1);
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        borrarValoresBBDD(db);
        inicializarBBDD(db);

        //Inicializamos el adaptador
        Data[] datos = leerBBDD(db);
        Adapter adapterTendencias = new Adapter(this, datos);
        lista_tendencias.setAdapter(adapterTendencias);
        lista_tendencias.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                layoutFavoritos.setVisibility(VISIBLE);
                dato_seleccionado = (Data) parent.getItemAtPosition(position);
            }
        });

        Data[] datosFav = new Data[0];
        Adapter adapterFav = new Adapter(this, datosFav);
        lista_personal.setAdapter(adapterFav);
        lista_personal.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                layoutEliminarFavoritos.setVisibility(VISIBLE);
                dato_seleccionado = (Data) parent.getItemAtPosition(position);
            }
        });

        //Añadimos eventos a los botones
        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Añadir Favorito", Toast.LENGTH_SHORT).show();
                añadirFavBBDD(db, dato_seleccionado);
                Adapter adapter_tendencias = new Adapter(MainActivity.this, leerBBDD(db));
                lista_tendencias.setAdapter(adapterTendencias);
                Adapter adapterFav = new Adapter(MainActivity.this, leerFavoritosBBDD(db));
                lista_personal.setAdapter(adapterTendencias);

            }
        });
        btnCancelarFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Cancelar Favorito", Toast.LENGTH_SHORT).show();
                layoutFavoritos.setVisibility(GONE);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "btnBorrar", Toast.LENGTH_SHORT).show();
                eliminarFavBBDD(db, dato_seleccionado);
                Adapter adapter_tendencias = new Adapter(MainActivity.this, leerBBDD(db));
                lista_tendencias.setAdapter(adapterTendencias);
                Adapter adapterFav = new Adapter(MainActivity.this, leerFavoritosBBDD(db));
                lista_personal.setAdapter(adapterTendencias);
            }
        });
        btnCancelarEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "btnCancBorrar", Toast.LENGTH_SHORT).show();
                layoutEliminarFavoritos.setVisibility(GONE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcion = item.getItemId();
        if(opcion == R.id.MnOp1) {
            startActivity(new Intent(MainActivity.this, PreferenceActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private Data[] leerBBDD(SQLiteDatabase db){
        Data[] datos = null;
        String titulo;
        int id, imagen;
        Cursor c = db.rawQuery("SELECT * FROM Peliculas", null);
        if (c.getCount() > 0) {
            ArrayList<Data> dataArrayList = new ArrayList<>();
            c.moveToFirst();
            do {
                id = c.getInt(0);
                titulo = c.getString(1);
                imagen = c.getInt(2);
                dataArrayList.add(new Data(id,imagen,titulo));
            } while (c.moveToNext());
            datos = dataArrayList.toArray(new Data[0]);
            c.close();
        }
        return datos;
    }

    private Data[] leerFavoritosBBDD(SQLiteDatabase db){
        Data[] datos = null;
        String titulo;
        int id, imagen;
        Cursor c = db.rawQuery("SELECT * FROM Peliculas WHERE lista = 1", null);
        if (c.getCount() > 0) {
            ArrayList<Data> dataArrayList = new ArrayList<>();
            c.moveToFirst();
            do {
                id = c.getInt(0);
                imagen = c.getInt(1);
                titulo = c.getString(2);
                dataArrayList.add(new Data(id,imagen,titulo));
            } while (c.moveToNext());
            datos = dataArrayList.toArray(new Data[0]);
            c.close();
        }
        return datos;
    }

    private void inicializarBBDD(SQLiteDatabase db){
        insertarPeliculaBBDD(db, R.drawable.alquimia, "Alquimia",0);
        insertarPeliculaBBDD(db, R.drawable.breaking, "Breaking Bad",0);
        insertarPeliculaBBDD(db, R.drawable.broadchurch, "Broadchurch",0);
        insertarPeliculaBBDD(db, R.drawable.erased, "Erased",0);
        insertarPeliculaBBDD(db, R.drawable.hill, "The Haunting",0);
        insertarPeliculaBBDD(db, R.drawable.howl, "Howl's Moving Castle",0);
        insertarPeliculaBBDD(db, R.drawable.lucifer, "Lucifer",0);
        insertarPeliculaBBDD(db, R.drawable.stranger, "Strager Things",0);
        insertarPeliculaBBDD(db, R.drawable.supernatural, "Supernatural",0);
        insertarPeliculaBBDD(db, R.drawable.titanes, "Attack on Titan",0);
    }

    private void insertarPeliculaBBDD(SQLiteDatabase db, int imagen, String nombre, int listado){
        ContentValues values = new ContentValues();
        values.put("foto",imagen);
        values.put("nombre",nombre);
        values.put("lista",listado);
        db.insert("Peliculas", null, values);
    }

    private void borrarValoresBBDD(SQLiteDatabase db){
        db.delete("Peliculas","",null);
    }

    private void añadirFavBBDD(SQLiteDatabase db, Data dato){
        ContentValues values = new ContentValues();
        values.put("id", dato.getId());
        values.put("lista",1);
        db.update("Peliculas", values, "id=" + dato.getId(), null);
    }

    private void eliminarFavBBDD(SQLiteDatabase db, Data dato){
        ContentValues values = new ContentValues();
        values.put("id", dato.getId());
        values.put("lista",0);
        db.update("Peliculas", values, "id=" + dato.getId(), null);
    }
}