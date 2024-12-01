package com.example.t5ejercicio7;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar tool = findViewById(R.id.toolBar);
        tool.setTitle("T5E7");
        setSupportActionBar(tool);

        //Creo los datos a mostrar en el arrayadapter
        ArrayList<Data> data = new ArrayList<>();
        data.add(new Data(R.drawable.buho,"Buho"));
        data.add(new Data(R.drawable.cachorros,"Cachorros"));
        data.add(new Data(R.drawable.cerdo,"Cerdo"));
        data.add(new Data(R.drawable.jirafas,"Jirafas"));
        data.add(new Data(R.drawable.lobo,"Lobo"));
        data.add(new Data(R.drawable.potro,"Potro"));
        data.add(new Data(R.drawable.tigre,"Tigre"));
        data.add(new Data(R.drawable.tortuga,"Tortuga"));
        data.add(new Data(R.drawable.tucan,"Tucan"));

        //Recuperar el listado
        final GridView list = findViewById(R.id.grid);

        //Crear adaptador personalizado
        Adapt myAdapter = new Adapt(data,this);
        list.setAdapter(myAdapter);

        //Crea listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (adapterView != null){
                    String selected = ((Data)adapterView.getItemAtPosition(position)).getNombre();
                    Toast.makeText(MainActivity.this, "Animal: "+selected, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}