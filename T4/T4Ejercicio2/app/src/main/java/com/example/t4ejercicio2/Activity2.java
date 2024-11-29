package com.example.t4ejercicio2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        setContentView(R.layout.activity2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Botones que cambian de color
        Button btn1 = findViewById(R.id.loseWeightBtn);//#f3e6f8
        Button btn2 = findViewById(R.id.getStrongerBtn);
        Button btn3 = findViewById(R.id.getFitBtn);

        //Colores
        String unselected = "#f7c1ea";
        String selected = "#f3e6f8";


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackgroundColor(Color.parseColor(selected));
                btn2.setBackgroundColor(Color.parseColor(unselected));
                btn3.setBackgroundColor(Color.parseColor(unselected));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackgroundColor(Color.parseColor(unselected));
                btn2.setBackgroundColor(Color.parseColor(selected));
                btn3.setBackgroundColor(Color.parseColor(unselected));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackgroundColor(Color.parseColor(unselected));
                btn2.setBackgroundColor(Color.parseColor(unselected));
                btn3.setBackgroundColor(Color.parseColor(selected));
            }
        });

        //Boton siguiente
        Button nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Activity2.this, "Accediendo a la siguiente pantalla", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);

            }
        });
    }
}
