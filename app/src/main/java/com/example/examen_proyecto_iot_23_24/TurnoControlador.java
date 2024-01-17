package com.example.examen_proyecto_iot_23_24;

import java.util.ArrayList;
import java.util.List;

// 1. JAVA b)
public class TurnoControlador {
    private long inicio;
    private String nombre;
    private double sueldo;

    public TurnoControlador() {
    }

    public TurnoControlador(long inicio, String nombre, double sueldo) {
        this.inicio = inicio;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public long getInicio() {
        return inicio;
    }

    public void setInicio(long inicio) {
        this.inicio = inicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "inicio=" + inicio +
                ", nombre='" + nombre + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }

    public int getSueldoEnEuros() {
        return (int) (sueldo * 1000);
    }

    // 1. JAVA c)
    public static List<TurnoControlador> creaListaTurnos(long inicio[], String nombre[], double sueldo[]) {
        List<TurnoControlador> listOut = new ArrayList<>();
        for (int i = 0; i < inicio.length; i++) {
            listOut.add(new TurnoControlador(inicio[i], nombre[i], sueldo[i]));
        }
        return listOut;
    }
}

