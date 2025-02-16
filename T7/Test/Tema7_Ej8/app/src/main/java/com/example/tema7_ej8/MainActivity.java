package com.example.tema7_ej8;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = findViewById(R.id.spinner);

        final ImageView imgCargada = findViewById(R.id.imgCargada);

        final Button btnGuardar = findViewById(R.id.btnGuardar);
        final Button btnCargar = findViewById(R.id.btnCargar);
        final Button btnBorrar = findViewById(R.id.btnBorrar);
        final Button btnAnterior = findViewById(R.id.btnAnterior);
        final Button btnSiguiente = findViewById(R.id.btnSiguiente);

        ArrayList<DatosSpinner> datos = new ArrayList<>();
        datos.add(new DatosSpinner(R.drawable.batman));
        datos.add(new DatosSpinner(R.drawable.capi));
        datos.add(new DatosSpinner(R.drawable.deadpool));
        datos.add(new DatosSpinner(R.drawable.furia));
        datos.add(new DatosSpinner(R.drawable.hulk));
        datos.add(new DatosSpinner(R.drawable.ironman));
        datos.add(new DatosSpinner(R.drawable.lobezno));
        datos.add(new DatosSpinner(R.drawable.spiderman));
        datos.add(new DatosSpinner(R.drawable.thor));
        datos.add(new DatosSpinner(R.drawable.wonderwoman));

        SpinnerAdapter adapter = new SpinnerAdapter(datos, this);
        spinner.setAdapter(adapter);

        final File rutaSd = getExternalFilesDir(null);
        final File f = new File(rutaSd.getAbsolutePath(), "imgs.txt");

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileWriter fileWriter = new FileWriter(f, true);

                    fileWriter.write(String.valueOf(spinner.getSelectedItemPosition() + "\n"));
                    fileWriter.close();

                } catch (FileNotFoundException e) {
                    Log.e("Ficheros", "Error al crear/buscar el fichero");

                } catch (IOException e) {
                    Log.e("Ficheros", "Error al escribir en el fichero");

                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileWriter fileWriter = new FileWriter(f, false); // 'false' sobrescribe el archivo
                    fileWriter.write(""); // Escribir un string vacío
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Scanner scanner = new Scanner(f);
                    String linea = scanner.nextLine();

                    if (linea != null) {
                        imgCargada.setImageResource(Integer.parseInt(linea));
                    }

                    btnSiguiente.setEnabled(true);

                    btnSiguiente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String linea;
                            btnAnterior.setEnabled(false);

                            if (scanner.hasNextLine()) {
                                linea = scanner.nextLine();
                                imgCargada.setImageResource(Integer.parseInt(linea));

                            } else {
                                btnSiguiente.setEnabled(false);
                            }

                        }
                    });


                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);

                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
            }
        });
    }
}