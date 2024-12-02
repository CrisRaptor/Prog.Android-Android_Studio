package com.example.exament4_5m2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityManga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manga_layout);

        final ImageView imagen = findViewById(R.id.imagenManga);
        final Button boton = findViewById(R.id.botonVolver);

        Bundle extras = getIntent().getExtras();
        String eleccion = extras.getString("eleccion");
        if (eleccion.equals("Shonen")){
            imagen.setImageResource(R.drawable.kimetsu);
        } else if (eleccion.equals("Shojo")){
            imagen.setImageResource(R.drawable.furuba);
        }

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
