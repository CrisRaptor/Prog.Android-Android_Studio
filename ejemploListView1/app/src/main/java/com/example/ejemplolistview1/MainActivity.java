package com.example.ejemplolistview1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupera el elemento
        ListView list = findViewById(R.id.list);
        //Declara la fuente de datos (array, arraylist, o crear un archivo resources en res/values)
        final String[] datos = {
                "Opcion 1","Opcion 2","Opcion 3","Opcion 4","Opcion 5"
        };

        //Creo el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);

        //Asgina el adaptador
        list.setAdapter(adapter);

        //Creo listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //1 no adecuada, inconsistente
                //Toast.makeText(MainActivity.this, datos[position], Toast.LENGTH_SHORT).show();

                //2
                Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                //3
                Toast.makeText(MainActivity.this, adapterView.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //Recupera el elemento
        ListView list2 = findViewById(R.id.list2);
        //Declara la fuente de datos (resources en res/values)

        //Creo el adaptador
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.listValues ,android.R.layout.simple_list_item_1);

        //Asgina el adaptador
        list2.setAdapter(adapter2);

        //Creo listener
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //1 no adecuada, inconsistente
                //Toast.makeText(MainActivity.this, datos[position], Toast.LENGTH_SHORT).show();

                //2
                //Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                //3
                Toast.makeText(MainActivity.this, "Has hecho clic en "+adapterView.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}