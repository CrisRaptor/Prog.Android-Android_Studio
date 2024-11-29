package com.example.t4ejercicio3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar tool = findViewById(R.id.toolBar);
        setSupportActionBar(tool);

        final Button btnMensaje = findViewById(R.id.btnMensaje);
        final Button btnWeb = findViewById(R.id.btnWeb);
        final Button btnLlamar = findViewById(R.id.btnLlamar);
        final Button btnMapa = findViewById(R.id.btnMapa);
        final Button btnFoto = findViewById(R.id.btnFoto);
        final Button btnCorreo = findViewById(R.id.btnCorreo);
        final Button btnStreetView = findViewById(R.id.btnStreetView);

        //Listener de todos los botones
        btnMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandar_mensaje(view);
            }
        });
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirPagina(view);
            }
        });
        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamarTelefono(view);
            }
        });
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verMapa(view);
            }
        });
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFoto(view);
            }
        });
        btnCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandarCorreo(view);
            }
        });
        btnStreetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                streetView(view);
            }
        });
    }

    //Metodos aportados por el ejercicio
    public void mandar_mensaje(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Hola buenas que tal estamos amor");
        startActivity(intent);
    }
    public void abrirPagina(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Alejandro1203"));
        startActivity(intent);
    }
    public void llamarTelefono(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:911"));
        startActivity(intent);
    }
    public void verMapa(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:378.695804,-4.443456?z=18"));
        startActivity(intent);
    }
    public void tomarFoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
    public void mandarCorreo(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"asunto");
        intent.putExtra(Intent.EXTRA_TEXT,"texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"afergom1203@g.educaand.es"});
        startActivity(intent);
    }
    public void streetView(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=138.996333, -0.14564596&cbp=0,250,0,0,0"));

        //cbll=latitud,longitud&cbp=0,azimut,0,zoom,altura
        startActivity(intent);
    }
}
