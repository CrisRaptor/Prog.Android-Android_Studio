package com.example.ejemploscontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ClientesProvider extends ContentProvider {

    //Definicion de la uri
    private static final String AUTHORITY = "com.example.ejemploscontentprovider",
            URI = "content://"+AUTHORITY+"/clientes";
    public static final Uri CONTENT_URI = Uri.parse(URI);

    //Definimos el objeto UriMatcher
    private static final int CLIENTE = 1,//Acceso genérico a tabla,
    CLIENTE_ID = 2; //Acceso a una fila
    private static final UriMatcher URI_MATCHER;

    //Inicializamos el UriMatcher
    static  {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY,"clientes",CLIENTE);
        URI_MATCHER.addURI(AUTHORITY,"clientes/#",CLIENTE_ID);
    }

    //Clase interna para declarar las constantes de columna
    public static final class Clientes implements BaseColumns{
        private Clientes() {
        }

        public static final String COL_NOMBRE = "nombre",
                COL_TELEFONO = "telefono", COL_EMAIL = "email";
    }

    //Bases de datos
    private ClientesBBDD clientesBBDD;
    private static final String BD_NOMBRE = "DBCliente",
            TABLA_CLIENTES = "Clientes";
    private static final int BD_VERSION = 1;

    @Override
    public boolean onCreate() {
        clientesBBDD = new ClientesBBDD(getContext(),BD_NOMBRE,null,BD_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //Si la consulta es a un id concreto, construimos le where
        String where = selection;
        if(URI_MATCHER.match(uri) == CLIENTE_ID){
            where = "_id="+uri.getLastPathSegment();
        }

        SQLiteDatabase db = clientesBBDD.getReadableDatabase();

        Cursor cursor = db.query(TABLA_CLIENTES,projection,where,selectionArgs,null,null,sortOrder);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);

        switch (match){
            case CLIENTE:
                return "vnd.android.cursor.dir/vnd.ejemplo.cliente";
            case CLIENTE_ID:
                return "vnd.android.cursor.item/vnd.ejemplo.cliente";
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long regid;
        SQLiteDatabase db = clientesBBDD.getWritableDatabase();
        regid = db.insert(TABLA_CLIENTES,null,values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI,regid);

        return newUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int cont;
        //Si es un borrado a un id concreto, construimos el where
        String where = selection;
        if(URI_MATCHER.match(uri) == CLIENTE_ID){
            where = "_id="+uri.getLastPathSegment();
        }

        SQLiteDatabase db = clientesBBDD.getWritableDatabase();

        cont = db.delete(TABLA_CLIENTES,where,selectionArgs);

        return cont;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int cont;
        //Si es un borrado a un id concreto, construimos el where
        String where = selection;
        if(URI_MATCHER.match(uri) == CLIENTE_ID){
            where = "_id="+uri.getLastPathSegment();
        }

        SQLiteDatabase db = clientesBBDD.getWritableDatabase();

        cont = db.update(TABLA_CLIENTES,values,where,selectionArgs);

        return cont;
    }
}
