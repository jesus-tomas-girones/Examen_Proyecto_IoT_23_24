package com.example.examen_proyecto_iot_23_24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //1.JAVA a)
    static long FINAL = 24;
    static long INICIO[]   = {0,     2,     5,     9,     15,    20,    21,    23};
    static String NOMBRE[] = {"Rosa","Juan","Rosa","Pere","Rosa","Juan","Pere","Juan"};
    static double SUELDO[] = {0.22,  0.19,  0.2,   0.18,  0.2,   0.19,  0.2,   0.3};

    //3. ESCRITURA DE DATOS
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. JAVA c)
        List<TurnoControlador> turnos = TurnoControlador.creaListaTurnos(INICIO, NOMBRE, SUELDO);
        Log.d("examen", turnos.toString());

        //2. ALGORITMO a)

        Map<String, Double> sueldoMinimo = new HashMap<>();
        for (TurnoControlador t : turnos) {
            if (sueldoMinimo.get(t.getNombre()) == null ||
                sueldoMinimo.get(t.getNombre()) > t.getSueldo()) {
                  sueldoMinimo.put(t.getNombre(), t.getSueldo());
            }
        }
        Log.d("examen", "Sueldo Mínimo: " + sueldoMinimo);

        //Alternativa

        Map<String, Double> sueldoMin = new HashMap<>();
        for (int i = 0; i < turnos.size(); i++) {
            if (sueldoMin.containsKey(turnos.get(i).getNombre())) {
                if (sueldoMin.get(turnos.get(i).getNombre()) > turnos.get(i).getSueldo()) {
                    sueldoMin.put(turnos.get(i).getNombre(), turnos.get(i).getSueldo());
                }
            } else {
                sueldoMin.put(turnos.get(i).getNombre(), turnos.get(i).getSueldo());
            }
        }
        Log.d("examen", "Sueldo Mínimo (alternativa): " + sueldoMin);

        //2. ALGORITMO b)
        double costeDia = 0;
        for (int i = 0; i < turnos.size(); i++) {
            long fin = FINAL;
            if (i < turnos.size() - 1)
                fin = turnos.get(i + 1).getInicio();
            long periodo = fin - turnos.get(i).getInicio();
            costeDia += periodo * turnos.get(i).getSueldo();
        }
        Log.d("examen", "Coste Jornada:" + costeDia);

        //Otra alternativa
        long costeJornada = 0L;
        for (int i = 0; i < turnos.size() - 1; i++) {
            long tiempo = turnos.get(i + 1).getInicio() - turnos.get(i).getInicio();
            costeJornada += tiempo * turnos.get(i).getSueldoEnEuros();
        }
        costeJornada += (FINAL - turnos.get(turnos.size() - 1).getInicio()) * turnos.get(turnos.size() - 1).getSueldoEnEuros();
        Log.d("examen", "Coste Jornada (alternativo): " + costeJornada);

        //3. ESCRITURA DE DATOS
        int contador = 0;
        for(TurnoControlador turno:turnos){
            db.collection("turnos").document(Integer.toString(contador)).set(turno);
            contador += 1;
        }
    }
}