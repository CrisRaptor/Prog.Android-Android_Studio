package com.example.t4ejercicio4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTrato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trato);

        final TextView txtFantasmas = findViewById(R.id.txtFantasma);
        final TextView txtCalabazas = findViewById(R.id.txtCalabazas);

        txtFantasmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityTrato.this, MainActivity.class);
                intent.putExtra("informacion", "fantasmas");
                startActivity(intent);
            }
        });

        txtCalabazas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityTrato.this, MainActivity.class);
                intent.putExtra("informacion", "calabazas");
                startActivity(intent);
            }
        });


    }
}
