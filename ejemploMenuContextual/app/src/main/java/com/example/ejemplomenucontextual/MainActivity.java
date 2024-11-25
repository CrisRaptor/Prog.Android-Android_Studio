package com.example.ejemplomenucontextual;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView etiqueta = findViewById(R.id.etiqueta);
        registerForContextMenu(etiqueta);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int opcion = item.getItemId();
        if (opcion == R.id.MnOp1||opcion == R.id.MnOp2||opcion == R.id.MnOp3){
            Toast.makeText(this, "Menu "+item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        } else if(opcion == R.id.MnOp2_1||opcion == R.id.MnOp2_2){
            Toast.makeText(this, "Submenu "+item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}