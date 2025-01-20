package com.example.ejemplopreferenciasactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

        final Button btnPreferencias = findViewById(R.id.btnPreferencias), btnConsultar = findViewById(R.id.btnConsultar);

        btnPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,OpcionesActivity.class));
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                Log.i("Preferencias","Opcion 1:"+preferences.getBoolean("opcion1",false));
                Log.i("Preferencias","Opcion 2:"+preferences.getString("opcion2",""));
                Log.i("Preferencias","Opcion 3:"+preferences.getString("opcion3",""));
            }
        });
    }
}