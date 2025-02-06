package com.example.t5menusejercicio2;

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
        getMenuInflater().inflate(R.menu.menu_calendario, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcion =item.getItemId();

        final TextView texto = findViewById(R.id.texto);

        if(opcion == R.id.MnOp1_1 || opcion == R.id.MnOp1_2 || opcion == R.id.MnOp1_3 || opcion == R.id.MnOp1_4
             || opcion == R.id.MnOp1_5 || opcion == R.id.MnOp1_6 || opcion == R.id.MnOp1_7) {
            texto.setText("PULSADO " + (item.getTitle()).toString().toUpperCase());
            return true;

        } else if(opcion == R.id.MnOp2_1 || opcion == R.id.MnOp2_2 || opcion == R.id.MnOp2_3 || opcion == R.id.MnOp2_4) {
            texto.setText("Pulsado el mes de "+ item.getTitle().toString());
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}