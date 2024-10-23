package com.example.t3ejercicios;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Altera estas variables para elegir el ejerecicio a mostrar (1-6 y 9-13)
        //Ejercicio 7 y 8 No tienen layout, son preguntas respondidas en el PDF
        int ejercicio = 12;
        int apartadoEj1 = 1; //Este se utiliza en el ejercicio 1, tiene 4 apartados (1-4)

        switch (ejercicio) { //Switch de ejercicios, default -> ejercicio 1
            case 2: //Ejercicio 2
                setup(R.layout.layout2);
                break;
            case 3: //Ejercicio 3
                setup(R.layout.layout3);
                break;
            case 4: //Ejercicio 4
                setup(R.layout.layout4);
                break;
            case 5: //Ejercicio 5
                setup(R.layout.layout5);
                break;
            case 6: //Ejercicio 6
                setup(R.layout.layout6);
                break;
            case 9: //Ejercicio 9
                setup(R.layout.layout9);
                break;
            case 10: //Ejercicio 10
                setup10();
                break;
            case 11: //Ejercicio 11
                setup11();
                break;
            case 12: // Ejercicio 12
                setup12();
                break;
            case 13: // Ejercicio 13
                setup(R.layout.layout13);
                break;
            default: //Ejercicio 1
                switch (apartadoEj1) { //Switch de apartados de ejercicio 1, default -> ejercicio 1 apartado 1
                    default: //Ejercicio 1 - parte 1
                        setup(R.layout.layout1_1);
                        break;
                    case 2: //Ejercicio 1 - parte 2
                        setup(R.layout.layout1_2);
                        break;
                    case 3: //Ejercicio 1 - parte 3
                        setup(R.layout.layout1_3);
                        break;
                    case 4: //Ejercicio 1 - parte 4
                        setup(R.layout.layout1_4);
                        break;
                }
                break;
        }

    }

    //Inicia un layout
    protected void setup(int layout) {
        setContentView(layout);

    }

    //Script layout 10 (Actividad 10)
    protected void setup10() {
        setContentView(R.layout.layout10);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Boton Reset
        final Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText name = findViewById(R.id.name);
                name.setText("");
                final EditText key = findViewById(R.id.key);
                key.setText("");
            }
        });
    }

    //Script layout 11 (Actividad 11)
    protected void setup11() {
        setContentView(R.layout.layout11);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Script layout 12 (Actividad 12)
    protected void setup12() {
        setContentView(R.layout.layout12);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Actividad 12, Método onClick para los botones Aceptar y Resetear
    public void onClick(View view) {
        final TextView label = findViewById(R.id.lbl);
        final GridLayout grid = findViewById(R.id.grifLy);
        final RadioGroup genreRadioGroup = findViewById(R.id.genreRadioGroup);
        final RadioGroup sportRadioGroup = findViewById(R.id.sportRadioGroup);
        int id = view.getId();

        if (id == R.id.acceptBtn) {
            label.setVisibility(View.VISIBLE);
            label.setText("Tus aficiones son:");
            for (int i = 0; i < grid.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) grid.getChildAt(i);
                if (checkBox.isChecked()) {
                    label.setText(label.getText() + "\n" + checkBox.getText());
                }
            }
        } else if (id == R.id.resetBtn) {
            for (int i = 0; i < grid.getChildCount(); i++) {
                ((CheckBox) grid.getChildAt(i)).setChecked(false);
            }
            genreRadioGroup.clearCheck();
            sportRadioGroup.clearCheck();
        }
    }
}