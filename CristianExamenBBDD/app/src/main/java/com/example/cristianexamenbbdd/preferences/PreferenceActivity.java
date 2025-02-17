package com.example.cristianexamenbbdd.preferences;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.cristianexamenbbdd.R;

public class PreferenceActivity extends android.preference.PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
