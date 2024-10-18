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
        setup13();
    }

    //Script layout 11 (Actividad 10)
    protected void setup11() {
        setContentView(R.layout.layout11);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    //Script layout 12 (Actividad 11)
    protected void setup12() {
        setContentView(R.layout.layout12);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Script layout 13 (Actividad 12)
    protected void setup13() {
        setContentView(R.layout.layout13);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClick(View view){
        final TextView label = findViewById(R.id.lbl);
        final GridLayout grid = findViewById(R.id.grifLy);
        final RadioGroup genreRadioGroup = findViewById(R.id.genreRadioGroup);
        final RadioGroup sportRadioGroup = findViewById(R.id.sportRadioGroup);
        int id = view.getId();

        if (id == R.id.acceptBtn){
            label.setVisibility(View.VISIBLE);
            label.setText("Tus aficiones son:");
            for (int i = 0; i < grid.getChildCount();i++){
                CheckBox checkBox = (CheckBox) grid.getChildAt(i);
                if (checkBox.isChecked()){
                    label.setText(label.getText()+"\n"+checkBox.getText());
                }
            }
        } else if (id == R.id.resetBtn){
            for (int i = 0; i < grid.getChildCount();i++){
                ((CheckBox) grid.getChildAt(i)).setChecked(false);
            }
            genreRadioGroup.clearCheck();
            sportRadioGroup.clearCheck();
        }
    }
}