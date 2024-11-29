package com.example.controlesbasicos2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

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
        setContentView(R.layout.activity_main);
        //Ejemplo de spinner creando los datos desde Java
        Spinner spinner = findViewById(R.id.mySpinner);
        String[] values = {"Fact 1","Fact 2","Fact 3","Fact 4"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,values));

        TextView text = findViewById(R.id.txt1);
        String value = spinner.getSelectedItem().toString();
        text.setText(value);

        //Manejador de eventos
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int pos, long id) {
                //Forma 1
//                final String line = (String) adapter.getItemAtPosition(pos);
                //Forma 2
                final String line = adapter.getSelectedItem().toString();

                text.setText(line);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner example, values created with XML
        Spinner spinner2 = findViewById(R.id.mySpinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.values, android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adaptador, View view, int pos, long id) {
                //Forma 1
//                final String cadena = (String) adaptador.getItemAtPosition(pos);
                //Forma 2
                final String cadena = adaptador.getSelectedItem().toString();

                text.setText(cadena);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Listener for checkbox
        TextView text2 = findViewById(R.id.lbl1);
        CheckBox checkBox = findViewById(R.id.myCheck);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean pressed) {
                text2.setText((pressed)?"Selected":"Unselected");
            }
        });

        //Radio Buttons
        RadioGroup group = findViewById(R.id.group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                final RadioButton rb = findViewById(checkedId);
                text2.setText(rb.getText().toString());
            }
        });

        //Switch
        Switch sw = findViewById(R.id.mySwitch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean pressed) {
                text2.setText((pressed)?"Switched":"Unswitched");
            }
        });

        RatingBar ratingBar = findViewById(R.id.rate);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                text2.setText("New value: "+value);
            }
        });
        TextView text3 = findViewById(R.id.text3);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                text3.setAlpha(value/60f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                text2.setText("Changing Transparencies");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text2.setText("Transparencies changed");
            }
        });
    }
}