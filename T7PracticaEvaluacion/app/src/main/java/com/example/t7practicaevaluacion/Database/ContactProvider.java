package com.example.t7practicaevaluacion.Database;

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

public class ContactProvider  extends ContentProvider {

    private static final String AUTHORITY = "com.example.t7practicaevaluacion",
                                URI = "content://"+AUTHORITY+"/contactos";
    public static Uri CONTENT_URI = Uri.parse(URI);

    private static final int CONTACT = 1,
                            CONTACT_ID = 2;
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, "contactos", CONTACT);
        URI_MATCHER.addURI(AUTHORITY, "contactos/#", CONTACT_ID);
    }

    public static final class Contacts implements BaseColumns {

        private Contacts() {}
        public static final String
                COL_NAME = "nombre",
                COL_PHONE = "telefono",
                COL_AVATAR = "avatar";
    }

    private ContactDB contactDB;
    private static final String DB_NAME = "DBContacto",
            TABLE_CONTACTS = "Contactos";
    private static final int BD_VERSION = 1;

    @Override
    public boolean onCreate() {
        contactDB = new ContactDB(getContext(), DB_NAME, null, BD_VERSION);
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case CONTACT:
                return "vnd.android.cursor.dir/vnd.iesbelen.contacto";
            case CONTACT_ID:
                return "vnd.android.cursor.item/vnd.iesbelen.contacto";
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String where = selection;
        if (URI_MATCHER.match(uri) == CONTACT_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = contactDB.getWritableDatabase();
        return db.query(TABLE_CONTACTS, projection, where, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long regId = 1;
        SQLiteDatabase db = contactDB.getWritableDatabase();
        regId = db.insert(TABLE_CONTACTS, null, values);
        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
        return newUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int cont;
        String where = selection;
        if (URI_MATCHER.match(uri) == CONTACT_ID) {
            where = "_id" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = contactDB.getWritableDatabase();
        cont = db.delete(TABLE_CONTACTS, where,selectionArgs);
        return cont;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int cont;
        String where = selection;
        if (URI_MATCHER.match(uri) == CONTACT_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = contactDB.getWritableDatabase();
        cont = db.update(TABLE_CONTACTS, values, where, selectionArgs);
        return cont;
    }
}
