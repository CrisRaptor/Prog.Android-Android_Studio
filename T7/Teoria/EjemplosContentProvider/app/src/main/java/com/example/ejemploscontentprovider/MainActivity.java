package com.example.ejemploscontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Elementos del layout
    private Button btnInsertar, btnEliminar, btnConsultar;
    private TextView txtResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsultar = findViewById(R.id.btnConsultar);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnEliminar = findViewById(R.id.btnEliminar);
        txtResultados = findViewById(R.id.txtResultados);

        // Creo la consulta con el content resolver
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Columnas a recuperar
                String[] columnas = {
                        ClientesProvider.Clientes._ID,
                        ClientesProvider.Clientes.COL_NOMBRE,
                        ClientesProvider.Clientes.COL_TELEFONO,
                        ClientesProvider.Clientes.COL_EMAIL
                };

                Uri uri = ClientesProvider.CONTENT_URI;
                ContentResolver cr = getContentResolver();

                //Hacemos la consulta
                Cursor cursor = cr.query(
                        uri,
                        columnas, //columnas a devolver
                        null, //condicion de la consulta
                        null, //argumentos de la consulta
                        null //orden de los resultados
                );
                if (cursor != null){
                    if (cursor.moveToFirst()){
                        String nombre, telefono, email;
                        int colNombre = cursor.getColumnIndex(ClientesProvider.Clientes.COL_NOMBRE);
                        int colTelefono = cursor.getColumnIndex(ClientesProvider.Clientes.COL_TELEFONO);
                        int colEmail = cursor.getColumnIndex(ClientesProvider.Clientes.COL_EMAIL);

                        txtResultados.setText("");

                        do {
                            nombre = cursor.getString(colNombre);
                            telefono = cursor.getString(colTelefono);
                            email = cursor.getString(colEmail);
                            txtResultados.append(nombre+" - "+telefono+" - "+email+"\n");
                        } while (cursor.moveToNext());
                    }
                }
            }
        });

        //Inserto datos con el content resolver en el content provider
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues registro = new ContentValues();
                registro.put(ClientesProvider.Clientes.COL_NOMBRE,"Cliente nuevo");
                registro.put(ClientesProvider.Clientes.COL_TELEFONO,"546234789");
                registro.put(ClientesProvider.Clientes.COL_EMAIL,"distintodenull@gmail.com");

                ContentResolver cr = getContentResolver();
                cr.insert(ClientesProvider.CONTENT_URI,registro);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver cr = getContentResolver();
                cr.delete(ClientesProvider.CONTENT_URI, ClientesProvider.Clientes.COL_NOMBRE + "='Cliente nuevo'",null);
            }
        });
    }
}