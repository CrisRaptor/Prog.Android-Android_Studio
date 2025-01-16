package com.example.ejemplodialogfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager FM = getSupportFragmentManager();
        //FragmentTransaction FT = FM.beginTransaction();

        final Button btnAlerta = findViewById(R.id.btnAlerta);
        btnAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoAlerta dialogoAlerta = new DialogoAlerta();
                dialogoAlerta.show(FM,"tagAlerta");
            }
        });

        final Button btnConfirmacion = findViewById(R.id.btnConfirmacion);
        btnConfirmacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoConfirmacion dialogoConfirmacion = new DialogoConfirmacion();
                dialogoConfirmacion.show(FM,"tagConfirmacion");
            }
        });
        final Button btnSeleccion = findViewById(R.id.btnSeleccion);
        btnSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoSeleccion dialogoSeleccion = new DialogoSeleccion();
                dialogoSeleccion.show(FM,"tagSeleccion");
            }
        });
        final Button btnRadio = findViewById(R.id.btnRadio);
        btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoRadio dialogoRadio = new DialogoRadio();
                dialogoRadio.show(FM,"tagSeleccionRadio");
            }
        });
        final Button btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoCheck dialogoCheck = new DialogoCheck();
                dialogoCheck.show(FM,"tagSeleccionCheck");
            }
        });
        final Button btnPerso = findViewById(R.id.btnPerso);
        btnPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoPerso dialogoPerso = new DialogoPerso();
                dialogoPerso.show(FM,"tagPerso");
            }
        });
    }
}