package com.example.ejemploscontentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClientesBBDD extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Clientes(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT," +
            "telefono TEXT," +
            "email TEXT)";

    public ClientesBBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos la  tabla
        db.execSQL(sqlCreate);

        // Por simplicidad del ejemplo, insertamos directamente clientes
        for (int i=1;i<10;i++){
            //Generamo datos
            String nombre = "Cliente"+i,telefono = "900-123-00"+i,email = "email"+i+"@mail.com";

            //Insertamos los datos en la tabla clientes
            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("telefono",telefono);
            registro.put("email",email);
            db.insert("Clientes",null,registro);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL(sqlCreate);
    }
}
