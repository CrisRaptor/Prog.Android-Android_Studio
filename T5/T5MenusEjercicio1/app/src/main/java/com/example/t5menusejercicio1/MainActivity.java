package com.example.t5menusejercicio1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_semanal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcion = item.getItemId();

        final TextView texto = findViewById(R.id.texto);

        if(opcion == R.id.MnOp1 || opcion == R.id.MnOp2 || opcion == R.id.MnOp3 || opcion == R.id.MnOp4
                || opcion == R.id.MnOp5 || opcion == R.id.MnOp6 || opcion == R.id.MnOp7) {
            texto.setText("PULSADO " + (item.getTitle()).toString().toUpperCase());
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}