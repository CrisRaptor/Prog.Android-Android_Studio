package com.example.ejemplorecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Opciones> datos;
    private RecyclerView recyclerView;
    private Button btnInsertar, btnBorrar, btnMover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperamos el recycler view
        recyclerView = findViewById(R.id.recView);

        //Indicar que el tamaño del recyclerView no cambia
        recyclerView.setHasFixedSize(true);

        //Creo los datos
        datos = new ArrayList<Opciones>();
        for (int i = 1; i<32; i++){
            if (i%2==0){
                datos.add(new Opciones("Opcion "+i, "Esta es la opcion numero "+i,R.drawable.star1));
            } else {
                datos.add(new Opciones("Opcion "+i, "Esta es la opcion numero "+i,R.drawable.star2));
            }
        }

        //Creamos el adaptador que se usa para mostrar las opciones del listados
        final AdaptadorOpciones adaptadorOpciones = new AdaptadorOpciones(datos);

        //Definimos el evento OnClick (se ha definido en adaptador)
        adaptadorOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Has hecho click en "+datos.get(recyclerView.getChildAdapterPosition(view)).getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });

        //Asigno adaptador y layout
        recyclerView.setAdapter(adaptadorOpciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Definimos los eventos onClick
        btnInsertar = findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.add(1,new Opciones("Nueva opcion","Subtitulo nueva opcion",R.drawable.star1));
                adaptadorOpciones.notifyItemInserted(1);
            }
        });

        btnBorrar = findViewById(R.id.btnBorrar);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datos.size() < 2) return;
                datos.remove(1);
                adaptadorOpciones.notifyItemRemoved(1);
            }
        });

        btnMover = findViewById(R.id.btnMover);
        btnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datos.size() < 3) return;
                Opciones aux = datos.get(1);
                datos.set(1,datos.get(2));
                datos.set(2,aux);
                adaptadorOpciones.notifyItemMoved(1,2);
            }
        });
    }
}