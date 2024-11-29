package com.example.t4ejercicio1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Actividad Propuesta");

        Bundle extras = getIntent().getExtras();
        String nombre = ((extras != null) ? extras.getString("usuario") : "");

        TextView txtTitulo = findViewById(R.id.txtPorfavor);
        txtTitulo.setText("Por favor, " + nombre + " valore la aplicación");

        Intent intent = new Intent(ReviewActivity.this, LoginActivity.class);

        Button btnValorar = findViewById(R.id.btnValorar);
        btnValorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReviewActivity.this, "Gracias por su valoracion", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler(getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                }, 3000);
            }
        });

    }
}