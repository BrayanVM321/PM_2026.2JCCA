package com.example.miapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
//Brayan Velazco Martinez
public class Calculadora extends Activity implements View.OnClickListener {

    Button[] btnDigitos = new Button[10];

    Button btnSuma, btnResta, btnMultiplica, btnDivide, btnPunto, btnIgual;

    EditText pantalla;

    double op1, op2, res;

    boolean pintarPunto = true;

    String operacion = "";

    @Override
    protected void onCreate(Bundle b) {

        super.onCreate(b);

        // PANEL PRINCIPAL

        LinearLayout panelPrincipal = new LinearLayout(this);

        panelPrincipal.setOrientation(LinearLayout.VERTICAL);

        panelPrincipal.setBackgroundColor(Color.BLACK);

        panelPrincipal.setPadding(15,15,15,15);

        // TITULO

        TextView titulo = new TextView(this);

        titulo.setText("CALCULADORA");

        titulo.setTextColor(Color.WHITE);

        titulo.setTextSize(28);

        titulo.setGravity(Gravity.CENTER);

        titulo.setPadding(0,20,0,20);

        // PANTALLA

        pantalla = new EditText(this);

        pantalla.setTextColor(Color.WHITE);

        pantalla.setBackgroundColor(Color.DKGRAY);

        pantalla.setTextSize(38);

        pantalla.setEnabled(false);

        pantalla.setGravity(Gravity.END);

        pantalla.setPadding(20,50,20,50);

        // PANEL CONTROLES

        LinearLayout panelControles = new LinearLayout(this);

        panelControles.setOrientation(LinearLayout.HORIZONTAL);

        // PANEL NUMEROS

        LinearLayout panelNumeros = new LinearLayout(this);

        panelNumeros.setOrientation(LinearLayout.VERTICAL);

        panelNumeros.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        3
                )
        );

        // PANEL OPERACIONES

        LinearLayout panelOperaciones = new LinearLayout(this);

        panelOperaciones.setOrientation(LinearLayout.VERTICAL);

        panelOperaciones.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        // FILAS

        LinearLayout fila1 = crearFila();

        LinearLayout fila2 = crearFila();

        LinearLayout fila3 = crearFila();

        LinearLayout fila4 = crearFila();

        // BOTONES NUMEROS

        for (int i = 0; i <= 9; i++) {

            btnDigitos[i] = crearBoton(String.valueOf(i));

            switch (i) {

                case 7:
                case 8:
                case 9:
                    fila1.addView(btnDigitos[i]);
                    break;

                case 4:
                case 5:
                case 6:
                    fila2.addView(btnDigitos[i]);
                    break;

                case 1:
                case 2:
                case 3:
                    fila3.addView(btnDigitos[i]);
                    break;

                case 0:
                    fila4.addView(btnDigitos[i]);
                    break;
            }
        }

        // BOTON PUNTO

        btnPunto = crearBoton(".");

        // BOTON IGUAL

        btnIgual = crearBoton("=");

        fila4.addView(btnPunto);

        fila4.addView(btnIgual);

        // BOTONES OPERACIONES

        btnSuma = crearBotonOperacion("+");

        btnResta = crearBotonOperacion("-");

        btnMultiplica = crearBotonOperacion("*");

        btnDivide = crearBotonOperacion("/");

        // AGREGAR FILAS

        panelNumeros.addView(fila1);

        panelNumeros.addView(fila2);

        panelNumeros.addView(fila3);

        panelNumeros.addView(fila4);

        // AGREGAR OPERACIONES

        panelOperaciones.addView(btnSuma);

        panelOperaciones.addView(btnResta);

        panelOperaciones.addView(btnMultiplica);

        panelOperaciones.addView(btnDivide);

        // AGREGAR PANELES

        panelControles.addView(panelNumeros);

        panelControles.addView(panelOperaciones);

        // AGREGAR TODO

        panelPrincipal.addView(titulo);

        panelPrincipal.addView(pantalla);

        panelPrincipal.addView(panelControles);

        setContentView(panelPrincipal);
    }

    // CREAR FILAS

    public LinearLayout crearFila(){

        LinearLayout fila = new LinearLayout(this);

        fila.setOrientation(LinearLayout.HORIZONTAL);

        return fila;
    }

    // CREAR BOTONES NORMALES

    public Button crearBoton(String texto){

        Button btn = new Button(this);

        btn.setText(texto);

        btn.setTextSize(20);

        btn.setOnClickListener(this);

        LinearLayout.LayoutParams parametros =
                new LinearLayout.LayoutParams(
                        0,
                        180,
                        1
                );

        parametros.setMargins(5,5,5,5);

        btn.setLayoutParams(parametros);

        return btn;
    }

    // CREAR BOTONES OPERACIONES

    public Button crearBotonOperacion(String texto){

        Button btn = new Button(this);

        btn.setText(texto);

        btn.setTextSize(20);

        btn.setOnClickListener(this);

        LinearLayout.LayoutParams parametros =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        180
                );

        parametros.setMargins(5,5,5,5);

        btn.setLayoutParams(parametros);

        return btn;
    }

    @Override
    public void onClick(View v) {

        // NUMEROS

        for (int i = 0; i <= 9; i++) {

            if (v.equals(btnDigitos[i])) {

                pantalla.setText(pantalla.getText() + "" + i);
            }
        }

        // PUNTO

        if (v.equals(btnPunto)) {

            if (pintarPunto) {

                pantalla.setText(pantalla.getText() + ".");

                pintarPunto = false;
            }
        }

        // OPERACIONES

        if (v.equals(btnSuma)) {

            guardarOperacion("+");
        }

        if (v.equals(btnResta)) {

            guardarOperacion("-");
        }

        if (v.equals(btnMultiplica)) {

            guardarOperacion("*");
        }

        if (v.equals(btnDivide)) {

            guardarOperacion("/");
        }

        // IGUAL

        if (v.equals(btnIgual)) {

            pintarPunto = true;

            op2 = Double.parseDouble(pantalla.getText() + "");

            switch (operacion) {

                case "+":
                    res = op1 + op2;
                    break;

                case "-":
                    res = op1 - op2;
                    break;

                case "*":
                    res = op1 * op2;
                    break;

                case "/":
                    res = op1 / op2;
                    break;
            }

            pantalla.setText(String.valueOf(res));
        }
    }

    // GUARDAR OPERACION

    public void guardarOperacion(String op){

        pintarPunto = true;

        op1 = Double.parseDouble(pantalla.getText() + "");

        operacion = op;

        pantalla.setText("");
    }
}