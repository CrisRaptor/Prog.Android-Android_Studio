package com.example.t7ejercicio7;

import android.os.Bundle;
import android.os.Environment;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
                if (checkSDDisponible() && checkSDDisponibleEscritura()) {
                    try {
                        File ruta_sd = getExternalFilesDir(null);
                        File f = new File(ruta_sd.getAbsolutePath(), "texto.txt");
                        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
                        writer.write(editText.getText().toString());
                        writer.close();
                    } catch (FileNotFoundException e) {
                        Log.i("Ficheros", "ERROR, Fichero no encontrado");
                    } catch (IOException e) {
                        Log.i("Ficheros", "ERROR Escritura, no se pudo escribir el fichero");
                    }
                }
            }
        });
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSDDisponible() && checkSDDisponibleEscritura()) {
                    try {
                        File ruta_sd = getExternalFilesDir(null);
                        File f = new File(ruta_sd.getAbsolutePath(), "texto.txt");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                        String linea = reader.readLine();
                        label.setText(linea);
                        reader.close();
                    } catch (FileNotFoundException e) {
                        Log.i("Ficheros", "ERROR, Fichero no encontrado");
                    } catch (IOException e) {
                        Log.i("Ficheros", "ERROR Lectura, no se pudo leer el fichero");
                    }
                }
            }
        });
    }

    private boolean checkSDDisponible() {
        boolean sdDisponilbe = false;
        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED) || estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponilbe = true;
        }
        return sdDisponilbe;
    }

    private boolean checkSDDisponibleEscritura() {
        boolean sdAccesoEscritura = false;
        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdAccesoEscritura = true;
        }
        return sdAccesoEscritura;
    }
}