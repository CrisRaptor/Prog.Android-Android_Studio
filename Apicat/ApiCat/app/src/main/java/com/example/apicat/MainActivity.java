package com.example.apicat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int catIndex = 0;
    Cat[] cats = null;
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

        final TextView datoId = findViewById(R.id.datoId);
        final TextView datoRazas = findViewById(R.id.datoRazas);


        Context context = this;
        cats = CatRequest(connection);
        Log.i("cats","cats:  "+ Arrays.toString(cats));

        btnNewCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("siguiente gato","catindex "+catIndex);
                if (cats != null && cats[catIndex] != null){
                    Log.i("siguiente gato","cat: "+cats[catIndex].toString());

                    Cat cat = cats[catIndex];
                    if (catIndex < cats.length-1){
                        catIndex++;
                    } else {
                        cats = CatRequest(connection);
                        catIndex = 0;
                    }

                    imagenGato.setImageBitmap(getBitmapfromUrl(cat.getUrl()));

                    datoId.setText(cat.getId().toString());

                    if (!cat.getBreeds().isEmpty()){
                        StringBuilder razas = new StringBuilder();
                        ArrayList<BreedData> data = new ArrayList<>();
                        for (Breed breed: cat.getBreeds()) {
                            data.add(new BreedData(breed.getName(), breed.getTemperament(), breed.getOrigin(),
                                    breed.getDescription(), breed.getLife_span(),
                                    breed.getWeightObject().getMetric(), breed.getVetstreet_url()));
                            razas.append(breed.getName()).append(", ");
                        }
                        datoRazas.setText(razas.substring(0,razas.length()-1));
                        Adapt myAdapter = new Adapt(context, (BreedData[]) data.toArray());
                        list.setAdapter(myAdapter);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(data.get(position).getVetstreet_url()));
                                startActivity(intent);
                            }
                        });
                    } else {
                        datoRazas.setText("");
                    }
                    Log.i("Nuevo gato",cat.toString());
                } else {
                    Log.i("Nuevo gato","no encontro nuevo gato");
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
            urlConnection.setRequestProperty("x-api-key", "live_mx6QAFv6WnJwZPWpa0FoFFjEXZpyZo2zlKIxzJchUzSDWYJcnMjnPzBCVU97xyLK");
            urlConnection.setRequestProperty("limit","15");
            Log.i("catconnection","request: "+urlConnection.toString());

            urlConnection.connect();

            Log.i("catconnection","conecta");

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return urlConnection;
    }

    public Cat[] CatRequest(HttpURLConnection urlConnection) {
        Cat[] cats = null;
        StringBuilder result;
        Log.i("catrequest","empieza");
        try {

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.i("catrequest","lee: "+ result);
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

        Log.i("catrequest","devuelve: "+ Arrays.toString(cats));
        return cats;
    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
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