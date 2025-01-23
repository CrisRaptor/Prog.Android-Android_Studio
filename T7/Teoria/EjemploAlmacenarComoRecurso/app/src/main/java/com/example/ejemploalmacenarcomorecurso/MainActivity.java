package com.example.ejemploalmacenarcomorecurso;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream ficheroRaw = getResources().openRawResource(R.raw.fichero);
            BufferedReader in = new BufferedReader(new InputStreamReader(ficheroRaw));
            String linea = in.readLine();

            Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
            ficheroRaw.close();
        } catch (IOException e) {
            Log.i("Fichero","Error al leer");
        }
    }
}