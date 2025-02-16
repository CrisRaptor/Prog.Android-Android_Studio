package com.example.t7ejercicio4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PreferenciasActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_preferencias);

        getSupportFragmentManager().beginTransaction().replace(R.id.main, new Preferencias()).commit();
    }
}