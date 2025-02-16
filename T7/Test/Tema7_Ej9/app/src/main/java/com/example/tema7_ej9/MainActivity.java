package com.example.tema7_ej9;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputNombre = findViewById(R.id.inputNombre);
        EditText inputApellido = findViewById(R.id.inputApellido);
        EditText inputCodigo = findViewById(R.id.inputCodigo);

        Button btnActualizar = findViewById(R.id.btnActualizar);
        Button btnBorrar = findViewById(R.id.btnBorrar);
        Button btnActBor = findViewById(R.id.btnActBor);
        Button btnConsultar = findViewById(R.id.btnConsultar);
        Button btnInsertar = findViewById(R.id.btnInsertar);

        TextView txtConsulta = findViewById(R.id.txtConsulta);

        LinearLayout lytCodigo = findViewById(R.id.lytCodigo);

        UsuariosSQLiteHelper usuariosBBDD = new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usuariosBBDD.getWritableDatabase();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputNombre.getText().toString().isEmpty() && !inputApellido.getText().toString().isEmpty()) {
                    if (db != null) {
                        ContentValues values = new ContentValues();
                        values.put("nombre", inputNombre.getText().toString());
                        values.put("apellidos", inputApellido.getText().toString());
                        long insert = db.insert("Usuarios", null, values);

                        if (insert == -1) {
                            Log.e("SQLite", "Error al insertar");
                        } else {
                            Log.d("SQLite", "Registro insertado con ID: " + insert);
                        }

                        inputNombre.setText("");
                        inputApellido.setText("");

                        Toast.makeText(MainActivity.this, "Se han insertado los datos correctamente", Toast.LENGTH_SHORT).show();
                        
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtConsulta.setText("");
                Cursor c = db.rawQuery("SELECT * FROM Usuarios", null);

                if (c.getCount() > 0) { // Verifica si hay datos en el cursor
                    c.moveToFirst(); // Mover al primer resultado

                    do {
                        String codigo = c.getString(0);
                        String nombre = c.getString(1);
                        String apellido = c.getString(2);
                        txtConsulta.append(codigo + " - " +nombre + " " + apellido + "\n");

                    } while (c.moveToNext());

                } else {
                    txtConsulta.setText("No hay datos en la tabla.\n");
                }

                c.close();
                Toast.makeText(MainActivity.this, "Se ha realizado la consulta exitosamente", Toast.LENGTH_SHORT).show();
            }
        });

        btnActBor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lytCodigo.getVisibility() == View.GONE) {
                    lytCodigo.setVisibility(View.VISIBLE);

                } else {
                    lytCodigo.setVisibility(View.GONE);

                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer codigo = Integer.parseInt(inputCodigo.getText().toString());

                if (codigo.toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes introducir un código", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues values = new ContentValues();
                values.put("codigo", codigo);
                db.delete("Usuarios", "codigo=" + codigo, null);

                inputCodigo.setText("");

                Toast.makeText(MainActivity.this, "El borrado se ha realizado exitosamente", Toast.LENGTH_SHORT).show();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer codigo = Integer.parseInt(inputCodigo.getText().toString());

                if (codigo.toString().isEmpty() || inputNombre.getText().toString().isEmpty() || inputApellido.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues values = new ContentValues();
                values.put("codigo", codigo);
                values.put("nombre", inputNombre.getText().toString());
                values.put("apellidos", inputApellido.getText().toString());
                db.update("Usuarios", values, "codigo=" + codigo, null);

                inputCodigo.setText("");
                inputNombre.setText("");
                inputApellido.setText("");

                Toast.makeText(MainActivity.this, "El registro ha sido actualizado con exito", Toast.LENGTH_SHORT).show();
            }
        });
    }
}