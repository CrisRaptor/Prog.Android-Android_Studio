package com.example.ejemplocomunicacionactividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CondicionesDeUso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones_de_uso);

        final TextView lblMensaje = findViewById(R.id.lblMensaje);
        Bundle extras = getIntent().getExtras();
        String usuario = extras.getString("usuario");

        String mensaje = "Hola " + usuario + ". ¿Aceptas las condiciones de uso?";
        lblMensaje.setText(mensaje);

    }

    public void onClick(View view) {
        Intent intent = new Intent();

        int id = view.getId();
        if (id == R.id.btnAceptar) {
            intent.putExtra("boton_pulsado", "Aceptar");
            setResult(RESULT_OK, intent);
        } else if (id == R.id.btnRechazar) {
            intent.putExtra("boton_pulsado", "Rechazar");
            setResult(RESULT_OK, intent);
        } else if (id == R.id.btnCancelar) {
            intent.putExtra("boton_pulsado", "Cancelar");
            setResult(RESULT_CANCELED, intent);
        }
        finish();

    }
}