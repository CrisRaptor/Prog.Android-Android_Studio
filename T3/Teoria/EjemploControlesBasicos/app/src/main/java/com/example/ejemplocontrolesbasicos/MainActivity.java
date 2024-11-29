package com.example.ejemplocontrolesbasicos;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final ImageView img2 = (ImageView) findViewById(R.id.img2);
        img2.setImageResource(R.drawable.feliz);
        String[] opciones = {"Opcion 1","Opcion 2","Opcion 3","Opcion 4"};
        AutoCompleteTextView textoLeido = findViewById(R.id.acText);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,opciones);
        textoLeido.setAdapter(adaptador);
        MultiAutoCompleteTextView textoLeido2 = findViewById(R.id.macText);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,opciones);
        textoLeido2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        textoLeido2.setAdapter(adaptador2);


//////////////////////////////////////////////////////////////////////////////
//        setContentView(R.layout.activity_main);
//
//        //Buscamos el id de las etiquetas
//        final TextView etiq1 = (TextView) findViewById(R.id.lbl1);
//        final TextView etiq7 = (TextView) findViewById(R.id.lbl7);
//
//        String texto = etiq1.getText().toString();
//        etiq7.setText(texto + " copiado de lbl1");
//
//        //Botones
//        //Buscamos la etiqueta del boton
//        final TextView etiqBtn = (TextView) findViewById(R.id.lblBtn);
//        //Creamos el manejador de eventos
//        final Button miBoton = (Button) findViewById(R.id.btn1);
//
//        miBoton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                etiqBtn.setText("Pulsado boton simple");
//            }
//        });
//
//        //Recupero el id de ToggleButton y crea el manejador de evenetos
//        final ToggleButton miToggleButton = (ToggleButton) findViewById(R.id.toggleBtn);
//        miToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if (isChecked){
//                    etiqBtn.setText("Boton pulsado");
//                } else {
//                    etiqBtn.setText("Boton no pulsado");
//                }
//            }
//        });
//
//        //Recupero el id de los botones con imagenes y crea el manejador de eventos
//        final ImageButton miImageButton1 = (ImageButton) findViewById(R.id.imgBtn1);
//        miImageButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setContentView(R.layout.layout);
//            }
//        });
//
//        final ImageButton miImageButton2 = (ImageButton) findViewById(R.id.imgBtn2);
//        miImageButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final ImageButton imgBtn = (ImageButton) findViewById(R.id.imgBtn1);
//                imgBtn.setImageResource(R.drawable.feliz);
//            }
//        });
    }
}