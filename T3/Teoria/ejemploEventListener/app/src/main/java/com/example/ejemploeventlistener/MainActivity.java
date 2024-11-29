package com.example.ejemploeventlistener;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get layout elements
        TextView label1 = findViewById(R.id.lbl1);
        TextView label2 = findViewById(R.id.lbl2);
        TextView label3 = findViewById(R.id.lbl3);
        TextView label4 = findViewById(R.id.lbl4);
        EditText text = findViewById(R.id.et);

        //Event listener "addTextChange"
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                label1.setText("Text before change: " + charSequence.toString());
                label3.setText("Text before change: " + charSequence.toString() + "\nStart " + start + " | Count " + count + " | After " + after);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                label2.setText("Text changed: " + charSequence.toString());
                label4.setText("Text before change: " + charSequence.toString() + "\nStart " + start + " | Count " + count + " | After " + after);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    public void onClick(View view){
        final TextView label = findViewById(R.id.lbl5);
        int id = view.getId();

        if (id == R.id.btnAccept){
            label.setText("Accept");
        } else if (id == R.id.btnCancel){
            label.setText("Cancel");
        }
    }
}