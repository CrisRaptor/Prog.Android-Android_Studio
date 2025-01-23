package com.example.ejemplolecturasd;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

        // ESTE PROGRAMA SOLO HASTA API 29 (sin incluir)
        // PARA QUE FUNCIONE EN TODAS LAS APIS, EJECUTAR CON LAS LINEAS COMENTADAS
        boolean sdDisponilbe = false;
        boolean sdAccesoEscritura = false;

        //Comprobar el estado de la memoria SD
        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponilbe = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponilbe = true;
        }
        if (sdDisponilbe && sdAccesoEscritura) {
            try { //Escribo en la tarjeta SD
                //Obtengo la ruta del directorio raiz
//                File ruta_sd = Environment.getExternalStorageDirectory();
                File ruta_sd = getExternalFilesDir(null);
                File f = new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
                writer.write("Texto de prueba");
                writer.close();
            } catch (FileNotFoundException e) {
                Log.i("Ficheros","ERROR, Fichero no encontrado");
            } catch (IOException e) {
                Log.i("Ficheros","ERROR Escritura, no se pudo escribir el fichero");
            }

            try { //Leo los datos que acabo de escribir
                //Obtengo la ruta del directorio raiz
//                File ruta_sd = Environment.getExternalStorageDirectory();
                File ruta_sd = getExternalFilesDir(null);

                File f = new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String linea = reader.readLine();
                Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
                reader.close();
            } catch (FileNotFoundException e) {
                Log.i("Ficheros","ERROR, Fichero no encontrado");
            } catch (IOException e) {
                Log.i("Ficheros","ERROR Lectura, no se pudo leer el fichero");
            }
        }

    }
}