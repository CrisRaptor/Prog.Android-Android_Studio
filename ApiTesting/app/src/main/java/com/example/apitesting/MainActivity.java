package com.example.apitesting;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

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

        final TextView textView = findViewById(R.id.output);
        final Button btnEmpleado = findViewById(R.id.btnEmpleados);
        final Button btnDto = findViewById(R.id.btnDto);

        try {
            URL solicitudDto = new URL("http://172.16.60.247:8080/empleados/dto/9999");
            URL solicitudEmpleados = new URL("http://172.16.60.247:8080/empleados");

            btnEmpleado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText(Arrays.toString(RequestEmpleados(connectApi(solicitudEmpleados))));
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

        Log.i("empleadosRequest","devuelve: "+ Arrays.toString(empleados));
        return empleados;
    }
}