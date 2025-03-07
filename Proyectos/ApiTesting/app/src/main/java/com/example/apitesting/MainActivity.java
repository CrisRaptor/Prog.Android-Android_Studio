package com.example.apitesting;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apitesting.entidades.Empleado;
import com.example.apitesting.entidades.EmpleadoDTO;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static TextView textView;
    static Button btnEmpleado, btnDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        textView = findViewById(R.id.output);
        btnEmpleado = findViewById(R.id.btnEmpleados);
        btnDto = findViewById(R.id.btnDto);

//        setAdb();
//        adbReverse();

        try {
            URL solicitudDto, solicitudEmpleados;
            if (true){ //if (dispositivo virtual){ url = 10.0.2.2} else {adb + localhost:8080}
                solicitudDto = new URL("http://localhost:8080/empleados/dto/9999");
                solicitudEmpleados = new URL("http://localhost:8080/empleados");
            }

            btnEmpleado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("");
                    for (Empleado empleado: RequestEmpleados(connectApi(solicitudEmpleados))) {
                        textView.append(empleado.getId()+" - "+empleado.getNombre()+", "+empleado.getPuesto()+"\n");
                    }
                }
            });
            btnDto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText(RequestEmpleadosDto(connectApi(solicitudDto)).toString());
                }
            });
            Toast.makeText(this, "App lista", Toast.LENGTH_SHORT).show();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpURLConnection connectApi(URL url) {
        HttpURLConnection urlConnection = null;
        StringBuilder result;
        Log.i("apiConnection","url: "+url.toString());
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(false);
            urlConnection.setReadTimeout(1500);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            Log.i("apiConnection","request: "+urlConnection.toString());

            urlConnection.connect();
            Log.i("apiConnection","conecta");

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return urlConnection;
    }

    public EmpleadoDTO RequestEmpleadosDto(HttpURLConnection urlConnection) {
        EmpleadoDTO empleadoDto = null;
        StringBuilder result;
        Log.i("empleadoDtoRequest","empieza");
        try {

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.i("empleadoDtoRequest","lee: "+ result);
            Gson gson = new Gson();
            empleadoDto = gson.fromJson(String.valueOf(result), EmpleadoDTO.class);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (urlConnection!=null)
                urlConnection.disconnect();
        }

        Log.i("empleadoDtoRequest","devuelve: "+ empleadoDto.toString());
        return empleadoDto;
    }

    public Empleado[] RequestEmpleados(HttpURLConnection urlConnection) {
        Empleado[] empleados = null;
        StringBuilder result;
        Log.i("empleadosRequest","empieza");
        try {

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.i("empleadosRequest","lee: "+ result);
            Gson gson = new Gson();
            empleados = gson.fromJson(String.valueOf(result), Empleado[].class);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (urlConnection!=null)
                urlConnection.disconnect();
        }
        return empleados;
    }

    private void setAdb() {
        String adbPath = getApplicationContext().getFilesDir().getAbsolutePath() + "/adb/";
        Toast.makeText(this, adbPath, Toast.LENGTH_SHORT).show();
        File adbDir = new File(getFilesDir(), "adb");
        if (!adbDir.exists()) {
            adbDir.mkdirs(); // Crea la carpeta si no existe
        }

        File adbFile = new File(adbDir, "adb.exe"); // En Windows usa "adb.exe"
        textView.setText(adbFile.toString() + ","+adbFile.exists());

        if (!adbFile.exists()) {
            try (InputStream in = getAssets().open("adb/adb.exe"); // En Windows usa "adb.exe"
                 OutputStream out = new FileOutputStream(adbFile)) {

                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                out.flush();

                // Dar permisos de ejecución en Windoes?
                if (!adbFile.setExecutable(true)) {
                    Log.e("ADB", "No se pudo hacer ejecutable el archivo ADB");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean adbReverse(){
        try {
            String adbPath = getFilesDir().getAbsolutePath() + "/adb/adb.exe"; // En Windows usa "adb.exe"

            Process process = new ProcessBuilder(adbPath, "reverse", "tcp:8080", "tcp:8080")
                    .redirectErrorStream(true)
                    .start();

            process.waitFor();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}