package com.example.spinnercolores;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
//Brayan Velazco Martinez
public class MainActivity extends Activity {

    Spinner spinnerColores;
    EditText textoCambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerColores = findViewById(R.id.spinnercolor);
        textoCambiar = findViewById(R.id.textocambiar);

        String colores[] = {"Rojo", "Verde", "Azul"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                colores
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerColores.setAdapter(adapter);

        spinnerColores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        textoCambiar.setTextColor(Color.RED);
                        break;

                    case 1:
                        textoCambiar.setTextColor(Color.GREEN);
                        break;

                    case 2:
                        textoCambiar.setTextColor(Color.BLUE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}