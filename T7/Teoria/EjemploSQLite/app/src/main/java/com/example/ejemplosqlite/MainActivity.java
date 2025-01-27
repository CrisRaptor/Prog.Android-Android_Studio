package com.example.ejemplosqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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

        //Abrimos la base de adtos en modo escritura
        UsuarioSQLite usuarios = new UsuarioSQLite(this,"DBUsuarios",null,1);
        SQLiteDatabase db = usuarios.getWritableDatabase();

        //Comprobamos que se ha abierto correctamente la base de datos
        if (db != null){
            //Insertamos 5 usuarios
            String usuario;
            for (int i = 1; i<=5; i++){
                usuario = "Usuario " + i;
                db.execSQL("INSERT INTO Usuarios(codigo,nombre)" +
                        "VALUES ("+1+",'"+usuarios+"')");

            }
            db.close();
        }
    }
}