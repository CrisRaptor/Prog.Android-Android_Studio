package com.example.apicat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apicat.cat.Breed;
import com.example.apicat.cat.Cat;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

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

        final Button btnNewCat = findViewById(R.id.btnNewCat);
        final ImageView imagenGato = findViewById(R.id.catImage);
        final ListView list = findViewById(R.id.listBreed);
        final HttpURLConnection connection = connectApi();

        Context context = this;

        btnNewCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat[] cats = CatRequest(connection);
                if (cats.length > 0){
                    Cat cat = cats[0];
                    imagenGato.setImageBitmap(getBitmapfromUrl(cat.getUrl()));
                    BreedData[] data = {};
                    Breed[] breeds = cat.getBreeds().toArray(new Breed[0]);
                    for (Breed breed: breeds) {

                    }
                    Adapt myAdapter = new Adapt(context,data);
                    list.setAdapter(myAdapter);
                }
            }
        });
    }

    public HttpURLConnection connectApi() {
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

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return urlConnection;
    }

        public static Cat[] CatRequest(HttpURLConnection urlConnection) {
        Cat[] cats = null;
        URL url = null;
        StringBuilder result;
        try {
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            Gson gson = new Gson();
            cats = gson.fromJson(String.valueOf(result), Cat[].class);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (urlConnection!=null)
                urlConnection.disconnect();
        }
        return cats;
    }
    public Bitmap getBitmapfromUrl(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}