package com.example.t4ejercicio4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTruco extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_truco);

        final TextView txtPreparate =  findViewById(R.id.txtTruco);

        txtPreparate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityTruco.this, MainActivity.class);
                intent.putExtra("truco", "truco");
                startActivity(intent);
            }
        });
    }
}
