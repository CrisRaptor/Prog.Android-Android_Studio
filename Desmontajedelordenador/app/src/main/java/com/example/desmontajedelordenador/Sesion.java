package com.example.desmontajedelordenador;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Sesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);

        ImageView info = findViewById(R.id.info);
        String guia = "Escribe un usuario en el campo central.\nEl usuario te identificará al usar la aplicación.";
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Sesion.this, guia, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
