package com.example.ejemplosllamadas;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 0,
    PERMISSION_REQUEST_CALL_LOG = 1;
    private Button btnLlamadas;
    private TextView txtResultados;
    private LinearLayout vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLlamadas = findViewById(R.id.btnLlamadas);
        txtResultados = findViewById(R.id.lblResultados);
        vista = findViewById(R.id.vistaPrincipal);

        //Accedo al content provider publico a partir de la API 22
        btnLlamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLlamadas();
            }
        });
    }

    private void realizarLlamadas() {
        // Compruebo si los permisos para la llamada han sido concedidos
        // Elijo el Manifest de Android
        int permisoLecturaHistorial = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.READ_CALL_LOG);
        if (permisoLecturaHistorial != PackageManager.PERMISSION_GRANTED) {
            // Pido permisos
            peticionPermisos(Manifest.permission.READ_CALL_LOG,
                    new String[]{Manifest.permission.READ_CALL_LOG},
                    PERMISSION_REQUEST_CALL_LOG,
                    " a los contactos");
        } else {
            //Los permisos han sido concedidos, compruebo las llamadas
            String[] columnas = {CallLog.Calls.TYPE,CallLog.Calls.NUMBER};
            Uri llamadasUri = CallLog.Calls.CONTENT_URI;
            ContentResolver cr = getContentResolver();
            Cursor cursor = cr.query(
                    llamadasUri, columnas, null, null, null
            );
            if (cursor!=null){
                if (cursor.moveToFirst()){
                    int tipo;
                    String tipoLlamada = "", telefono;

                    int colTipo = cursor.getColumnIndex(CallLog.Calls.TYPE);
                    int colTelefono = cursor.getColumnIndex(CallLog.Calls.NUMBER);

                    txtResultados.setText("");

                    do {
                        tipo = cursor.getInt(colTipo);
                        telefono = cursor.getString(colTelefono);

                        if (tipo == CallLog.Calls.INCOMING_TYPE){
                            tipoLlamada = "ENTRADA";
                        } else if (tipo == CallLog.Calls.OUTGOING_TYPE){
                            tipoLlamada = "SALIDA";
                        } else if (tipo == CallLog.Calls.MISSED_TYPE){
                            tipoLlamada = "PERDIDA";
                        }
                        txtResultados.append(tipoLlamada+" - "+telefono+"\n");
                    } while (cursor.moveToNext());
                }
            }
        }
    }

    private void peticionPermisos(String permiso, String[] manifest, int id, String tipo) {
        // El usuario denegó el permiso al menos una vez, pero NO seleccionó "no volver a preguntar"
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,permiso)){
            Snackbar.make(vista,"Es necesario el acceso "+tipo+" para su gestión de la app",
                    Snackbar.LENGTH_INDEFINITE).setAction("ACEPTAR", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(MainActivity.this,manifest,id);
                }
            }).show();
        } else {
            //El permiso aún no ha sido solicitado o el usuario marcó "no volver a preguntar"
            ActivityCompat.requestPermissions(MainActivity.this,manifest,id);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CALL_LOG:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Snackbar.make(vista,"Permiso de lectura del historial establecido",
                            Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(vista, "Permiso de lectura de contactos denegado",
                            Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}