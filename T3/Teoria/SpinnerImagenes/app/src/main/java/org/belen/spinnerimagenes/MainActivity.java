package org.belen.spinnerimagenes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner miSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miSpinner = findViewById(R.id.spinner);

        ArrayList<Datos> datos = new ArrayList<Datos>();
        datos.add(new Datos(R.drawable.spiderman));
        datos.add(new Datos(R.drawable.batman));
        datos.add(new Datos(R.drawable.wonderwoman));
        datos.add(new Datos(R.drawable.capi));
        datos.add(new Datos(R.drawable.furia));
        datos.add(new Datos(R.drawable.thor));
        datos.add(new Datos(R.drawable.hulk));
        datos.add(new Datos(R.drawable.deadpool));
        datos.add(new Datos(R.drawable.ironman));
        datos.add(new Datos(R.drawable.lobezno));


        Adaptador adaptador = new Adaptador(this, datos);
        miSpinner.setAdapter(adaptador);

    }
}