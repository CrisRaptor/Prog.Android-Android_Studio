package com.example.t7practicaevaluacion.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactDB extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Contactos(" +
            "clave INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT, telefono TEXT, avatar INTEGER)";

    public ContactDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Contactos");
        db.execSQL(sqlCreate);
    }
}
