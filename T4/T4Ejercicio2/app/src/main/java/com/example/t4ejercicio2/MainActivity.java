package com.example.t4ejercicio2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText user = findViewById(R.id.userEt);
        EditText passwd = findViewById(R.id.passwordEt);
        Button accessBtn = findViewById(R.id.accessBtn);

        //Muestra tras 3 segundos
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setVisibility(View.VISIBLE);
                passwd.setVisibility(View.VISIBLE);
                accessBtn.setVisibility(View.VISIBLE);
            }
        }, 3000);

        //Boton de acceso
        accessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "";
                if (user.getText().length() > 0 && passwd.getText().length() > 0) {
                    message = "Hola " + user.getText() + ". Accediendo a la app";
                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    intent.putExtra("usuario", user.getText().toString());
                    startActivity(intent);

                } else {
                    message = "Introduce usuario y clave";
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}