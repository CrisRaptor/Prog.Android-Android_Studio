package com.example.ejemplomenucontextuallista;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupera el listado
        list = findViewById(R.id.list);

        //Crea los datos
        ArrayList<String> data = new ArrayList<>();
        data.add("Element 1");
        data.add("Element 2");
        data.add("Element 3");

        //Carga los datos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);
        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        //Obtengo el elemento del menu listado pulsado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        //Le pongo al menu contextual un titulo
        menu.setHeaderTitle(list.getAdapter().getItem(info.position).toString().toUpperCase());

        //Compruebo el elemento pulsado para ver que menu inflo
        switch (info.position){
            case 0:
                inflater.inflate(R.menu.menu_c1, menu);
                break;
            case 1:
                inflater.inflate(R.menu.menu_c2, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        String texto;
        int opcion = item.getItemId();
        if (opcion == R.id.Mn1Op1||opcion == R.id.Mn1Op2||opcion == R.id.Mn1Op3){
            Toast.makeText(this, "Menu 1 "+item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        } else if(opcion == R.id.Mn2Op2){
            Toast.makeText(this, "Menu 2 "+item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}