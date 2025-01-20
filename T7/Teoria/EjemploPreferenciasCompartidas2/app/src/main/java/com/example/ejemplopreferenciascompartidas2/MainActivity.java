package com.example.ejemplopreferenciascompartidas2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        final Button btnGuardar = findViewById(R.id.btnGuardar),btnCargar = findViewById(R.id.btnCargar),btnBorrar = findViewById(R.id.btnBorrar);
        final EditText etNombre = findViewById(R.id.etNombre), etCorreo = findViewById(R.id.etCorreo);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("nombre",etNombre.getText().toString());
                editor.putString("email",etCorreo.getText().toString());
                editor.commit();
            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                String nombre = prefs.getString("nombre",""),correo = prefs.getString("email","");

                etNombre.setText(nombre);
                etCorreo.setText(correo);
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNombre.setText("");
                etCorreo.setText("");
            }
        });
    }
}