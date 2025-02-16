package com.example.t7ejercicio6;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button btnGuardar = findViewById(R.id.btnGuardar),
                btnRecuperar = findViewById(R.id.btnRecuperar);
        final EditText editText = findViewById(R.id.et1);
        final TextView label = findViewById(R.id.lbl1);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter miFichero = new OutputStreamWriter(openFileOutput("fichero.txt", Context.MODE_PRIVATE));
                    miFichero.write(editText.getText().toString());
                    miFichero.close();
                } catch (FileNotFoundException e) {
                    Log.e("Fichero","Error Escritor, Archivo no encontrado");
                } catch (IOException e) {
                    Log.e("Fichero","Error al escribir");
                }
            }
        });

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BufferedReader miLector = new BufferedReader(new InputStreamReader(openFileInput("fichero.txt")));
                    String texto = miLector.readLine();
                    label.setText(texto);
                    miLector.close();
                } catch (FileNotFoundException e) {
                    Log.e("Fichero","Error Lector, Archivo no encontrado");
                } catch (IOException e) {
                    Log.e("Fichero","Error al leer");
                }
            }
        });
    }
}