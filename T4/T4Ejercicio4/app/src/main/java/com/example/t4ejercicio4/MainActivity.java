package com.example.t4ejercicio4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarSpinner();

        final ImageButton image = findViewById(R.id.imagen);
        final Spinner spinner = findViewById(R.id.spinner);
        final LinearLayout layoutParent = findViewById(R.id.parent);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(spinner.getSelectedItemPosition() == 0) {
                            Intent intent = new Intent(MainActivity.this, ActivityTruco.class);
                            startActivity(intent);
                        }

                        if(spinner.getSelectedItemPosition() == 1) {
                            Intent intent = new Intent(MainActivity.this, ActivityTrato.class);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Bundle extras = getIntent().getExtras();
        String informacion = extras != null ? extras.getString("informacion") : null;

        if(Objects.equals(informacion, "fantasmas")) {
            image.setImageResource(R.drawable.fantasma);
        } else if (Objects.equals(informacion, "calabazas")){
            image.setImageResource(R.drawable.calabaza);
        }
        informacion = extras != null ? extras.getString("truco") : null;

        if(Objects.equals(informacion, "truco")) {
            Drawable fondo = ContextCompat.getDrawable(this, R.drawable.fondo6);
            layoutParent.setBackground(fondo);
        }
    }

    private void cargarSpinner() {
        final Spinner spinnerTrucoTrato = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.data, android.R.layout.simple_spinner_item);
        spinnerTrucoTrato.setAdapter(adapter);
    }
}