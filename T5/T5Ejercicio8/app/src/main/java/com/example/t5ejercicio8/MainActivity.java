package com.example.t5ejercicio8;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo los datos a mostrar en el arrayadapter
        ArrayList<Data> data = new ArrayList<>();
        data.add(new Data(false, R.drawable.tv, "Television"));
        data.add(new Data(false, R.drawable.smartphone, "Teléfono móvil"));
        data.add(new Data(false, R.drawable.tablet, "Tablet"));
        data.add(new Data(false, R.drawable.ordenador_fijo, "Ordenador fijo"));
        data.add(new Data(false, R.drawable.ordenador_portatil, "Ordenador portátil"));
        data.add(new Data(false, R.drawable.reloj, "Reloj"));

        //Recuperar el listado
        final ListView list = findViewById(R.id.list);

        //Crear adaptador personalizado
        Adapt myAdapter = new Adapt(data, this);
        list.setAdapter(myAdapter);

        //Recupera el boton
        final Button aceptar = findViewById(R.id.aceptar);

        //Añade el listener
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje;
                //Registra los dispositivos marcados
                List<String> dispositivos = new ArrayList<>();
                for (Data dato: myAdapter.getData()) {
                    if (dato.isCheck()){
                        dispositivos.add(dato.getNombre().toLowerCase());
                    }
                }
                //Crea el mensaje
                if (dispositivos.isEmpty()) {
                    mensaje = "No has seleccionado ninguna opción";
                } else {

                    mensaje = "Para navegar por internet utilizas " + parseLista(dispositivos);
                }
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Crea una sintaxis apropiada con los elementos de una lista
    private String parseLista(List<String> lista) {
        String elementos = "";
        for (int pos = 0; pos < lista.size(); pos++) {
            if (pos != 0) {
                if (pos == lista.size() - 1) {
                    elementos += " y ";
                } else {
                    elementos += ", ";
                }
            }
            switch (lista.get(pos)) {
                case "television":
                    elementos += "la television";
                    break;
                case "teléfono móvil":
                    elementos += "el teléfono móvil";
                    break;
                case "tablet":
                    elementos += "la tablet";
                    break;
                case "ordenador fijo":
                    elementos += "el ordenador fijo";
                    break;
                case "ordenador portátil":
                    elementos += "el ordenador portátil";
                    break;
                case "reloj":
                    elementos += "el reloj";
                    break;
            }
        }

        return elementos;
    }
}