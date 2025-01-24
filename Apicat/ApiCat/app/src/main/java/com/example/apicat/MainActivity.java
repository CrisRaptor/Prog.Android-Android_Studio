package com.example.apicat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apicat.cat.Cat;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnNewCat = findViewById(R.id.btnNewCat);
        btnNewCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BufferedImage imagen = null;
//                try {
//                    imagen = ImageIO.read(new URL(APICat.CatRequest()[0].getUrl()));
//                    icon = new StretchIcon(imagen);
//                } catch (IOException ex) {
//                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                PictureLabel.setIcon(icon);
            }
        });
    }

    public static Cat[] CatRequest() {
        Cat[] cats = null;
        URL url = null;
        HttpURLConnection urlConnection = null;
        StringBuilder result;
        try {
            url = new URL("https://api.thecatapi.com/v1/images/search");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(false);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestProperty("x-api-key", "live_RmaYgkgOBovkDzRYUHklMIL5bqh38JV2FcQBvVqSKvca4rFwy0flDdUJMLMvl3Uy");

            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.d("NetworkConnect", "result: " + result.toString());

            //retObj = new JSONObject(result.toString());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        return cats;
    }
}