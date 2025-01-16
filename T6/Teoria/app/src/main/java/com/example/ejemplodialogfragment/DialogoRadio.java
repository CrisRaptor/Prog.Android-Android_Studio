package com.example.ejemplodialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoRadio extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final String[] items = {"Español", "Inglés", "Francés", "Alemán"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Seleccion Radio")
                .setSingleChoiceItems(items, -1,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("Dialogos", "Opcion radio elegida: "+items[i]);
                    }
                });
        return builder.create();
    }
}
