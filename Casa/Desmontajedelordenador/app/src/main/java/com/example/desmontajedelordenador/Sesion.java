package com.example.desmontajedelordenador;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Sesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);

        EditText usuario = findViewById(R.id.user);
        TextView mensaje = findViewById(R.id.mensaje);
        ImageView info = findViewById(R.id.info);
        Button iniciar = findViewById(R.id.iniciar_sesion);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guia = "Escribe un usuario en el campo central.";
                mensaje.setTextColor(getResources().getColor(R.color.advise));
                mensaje.setText(guia);
            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String error = "";
                mensaje.setTextColor(getResources().getColor(R.color.error));
                if (usuario.getText().length() < 7){
                    error += "El usuario debe tener 8 o más caracteres.";
                    mensaje.setText(error);

                }
                if (error.equals("")){
                    Intent intent = new Intent(Sesion.this, Inicio.class);
                    intent.putExtra("Usuario",mensaje.getText());
                    startActivity(intent);
                }
            }
        });
    }
}
