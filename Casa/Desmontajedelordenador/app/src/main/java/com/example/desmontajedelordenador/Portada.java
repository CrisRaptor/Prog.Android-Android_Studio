package com.example.desmontajedelordenador;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Portada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);

        Handler handler = new Handler();
        Intent intent = new Intent(Portada.this, Sesion.class);

        //Buscar si existe un usuario (Proximamente)

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 2000);
    }
}