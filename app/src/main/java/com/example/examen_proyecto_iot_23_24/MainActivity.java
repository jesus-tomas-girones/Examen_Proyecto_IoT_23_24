package com.example.examen_proyecto_iot_23_24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //1.JAVA a)
    static long FINAL = 24;
    static long INICIO[]= {0, 2, 5, 9, 15, 20, 21, 23};
    static String NOMBRE[] = {"Rosa","Juan","Rosa","Pere","Rosa","Juan","Pere","Juan"};
    //0.19 -> 190 euros por hora
    static double SUELDO[] = {0.22, 0.19, 0.2, 0.18, 0.2, 0.19, 0.2, 0.3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. JAVA c)
        List<TurnoControlador> turnos= TurnoControlador.creaListaTurnos(INICIO,NOMBRE,SUELDO);
        Log.d("examen",turnos.toString());
    }
}