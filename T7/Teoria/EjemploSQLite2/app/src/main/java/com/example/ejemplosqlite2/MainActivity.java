package com.example.ejemplosqlite2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        final TextView consultas = findViewById(R.id.lblConsulta);
        final Button btnInsertar = findViewById(R.id.btnInsertar), btnBorrar = findViewById(R.id.btnBorrar),
                btnActualizar = findViewById(R.id.btnActualizar), btnConsultar = findViewById(R.id.btnConsultar);
        final EditText etCodigo = findViewById(R.id.etCodigo), etNombre = findViewById(R.id.etNombre);

        //Abrimos la base de datos en modo escritura
        UsuariosDB usuarios = new UsuariosDB(this,"DBUsuarios",null,1);
        SQLiteDatabase db = usuarios.getWritableDatabase();

        //Comprobamos que se ha abierto correctamente
        if (db != null){
            //Inserto datos
            btnInsertar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String cod = etCodigo.getText().toString(),
                            usuario = etNombre.getText().toString();

                    ContentValues registro = new ContentValues();
                    registro.put("codigo",cod);
                    registro.put("nombre",usuario);
                    db.insert("Usuarios",null,registro);

                    //Borrar el formulario
                    etCodigo.setText("");
                    etNombre.setText("");

                }
            });
            btnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.execSQL("DELETE FROM Usuarios");
                }
            });
            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String cod = etCodigo.getText().toString(),
                            usuario = etNombre.getText().toString();
                    String[] args = {usuario,cod};
                    db.execSQL("UPDATE Usuarios SET nombre=? WHERE codigo=?",args);
                }
            });
            btnConsultar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    consultas.setText("");
                    Cursor miCursor = db.rawQuery("SELECT codigo,nombre FROM Usuarios",null);
                    if (miCursor.moveToFirst()){
                        do {
                            String cod = miCursor.getString(0);
                            String usuario = miCursor.getString(1);

                            consultas.append(cod+" "+usuario+"\n");
                        } while (miCursor.moveToNext());
                        miCursor.close();
                    }
                }
            });
        }
    }
}