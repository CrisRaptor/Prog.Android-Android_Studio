package com.example.desmontajedelordenador;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Valoracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valoracion);

        final Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);

        Button valorar = findViewById(R.id.btnValorar);
        valorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Valoracion.this, "Gracias por su valoración", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
