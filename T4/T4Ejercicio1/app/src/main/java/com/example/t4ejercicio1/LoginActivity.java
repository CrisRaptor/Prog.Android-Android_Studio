package com.example.t4ejercicio1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Actividad Propuesta");

        Button btnAceptar = findViewById(R.id.btnAceptar);
        Button btnValorar = findViewById(R.id.btnValorar);
        EditText nameEt = findViewById(R.id.nameEt);
        EditText dateEt = findViewById(R.id.dateEt);
        TextView result = findViewById(R.id.resultLbl);
        CheckBox check = findViewById(R.id.check);

        Intent intent = new Intent(LoginActivity.this, ReviewActivity.class);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";
                boolean hayDatos = true;
                if (nameEt.getText().toString().isEmpty()){
                    text += "No has escrito el nombre. ";
                    hayDatos = false;
                }
                if (dateEt.getText().toString().isEmpty()){
                    text += "No has escrito la fecha de nacimiento. ";
                    hayDatos = false;
                }
                if (hayDatos){
                    text += "¡Hola "+nameEt.getText().toString()+ "! Has nacido el "+dateEt.getText().toString()+".";
                    if(check.isChecked()){
                        text += "Se ha creado un recordatorio.";
                    }
                    btnValorar.setVisibility(View.VISIBLE);
                    btnValorar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            intent.putExtra("usuario",nameEt.getText().toString());
                            startActivity(intent);
                        }
                    });
                }
                result.setText(text);
            }
        });

    }
}