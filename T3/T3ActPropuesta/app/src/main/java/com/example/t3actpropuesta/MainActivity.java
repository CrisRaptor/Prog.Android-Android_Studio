package com.example.t3actpropuesta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = findViewById(R.id.btnAceptar);
        EditText nameEt = findViewById(R.id.nameEt);
        EditText dateEt = findViewById(R.id.dateEt);
        TextView result = findViewById(R.id.resultLbl);
        CheckBox check = findViewById(R.id.check);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";
                boolean hayDatos = true;
                if (nameEt.getText().toString().equals("")){
                    text += "No has escrito el nombre. ";
                    hayDatos = false;
                }
                if (dateEt.getText().toString().equals("")){
                    text += "No has escrito la fecha de nacimiento. ";
                    hayDatos = false;
                }
                if (hayDatos){
                    text += "¡Hola "+nameEt.getText().toString()+ "! Has nacido el "+dateEt.getText().toString()+".";
                    if(check.isChecked()){
                        text += "Se ha creado un recordatorio.";
                    }
                }
                result.setText(text);
            }
        });
    }
}