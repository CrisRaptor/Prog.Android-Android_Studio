package com.example.ejemplociclovida;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Definimos el metodo de callback onCreate de la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Usamos la clase Toast que muestra durante 2 segundos un mensaje pequeño al usuario
        Toast.makeText(this, "Se ejecuta el metodo onCreate", Toast.LENGTH_SHORT).show();
    }

    //Defino el metodo de llamada onPause de la actividad
    @Override
    protected void onPause() {
        super.onPause();
        
        //Aqui deberiamos guardar la informacion para las siguiente sesion
        Toast.makeText(this, "Se ejecuta el metodo onPause", Toast.LENGTH_SHORT).show();
    }

    //Defino el metodo de llamada onRestart de la actividad
    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(this, "Se ejecuta el metodo onRestart", Toast.LENGTH_SHORT).show();
    }

    //Defino el metodo de llamada onResume de la actividad
    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "Se ejecuta el metodo onResume", Toast.LENGTH_SHORT).show();
    }

    //Defino el metodo de llamada onStart de la actividad
    @Override
    protected void onStart() {
        super.onStart();

        //Aqui deberiamos leer los datos de la ultima sesion para continuar la actividad  donde la dejo el usuario
        Toast.makeText(this, "Se ejecuta el metodo onStart", Toast.LENGTH_SHORT).show();
    }

    //Defino el metodo de llamada onDestroy de la actividad
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Si no hay suficiente memoria, android destruye la actividad sin ejecutar el método
        Toast.makeText(this, "Se ejecuta el metodo onDestroy", Toast.LENGTH_SHORT).show();
    }

    //Defino el metodo de llamada onStop de la actividad
    @Override
    protected void onStop() {
        super.onStop();

        //Si no hay suficiente memoria, android destruye la actividad sin ejecutar el método
        Toast.makeText(this, "Se ejecuta el metodo onStop", Toast.LENGTH_SHORT).show();
    }
}