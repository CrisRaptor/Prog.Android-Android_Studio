package com.example.exament4_5m2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityVerInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_info_layout);

        final TextView titulo = findViewById(R.id.tituloInfo);
        final Button boton = findViewById(R.id.botonVolver);

        Bundle extras = getIntent().getExtras();
        titulo.setText(extras.getString("eleccion"));

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityVerInfo.this, "En construcción", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
