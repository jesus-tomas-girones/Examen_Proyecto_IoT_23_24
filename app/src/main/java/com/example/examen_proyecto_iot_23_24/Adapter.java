package com.example.examen_proyecto_iot_23_24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

//4. RECICLERVIEW
public class Adapter extends
        FirestoreRecyclerAdapter<TurnoControlador, Adapter.ViewHolder> {

    public Adapter(@NonNull FirestoreRecyclerOptions<TurnoControlador> options) {
        super(options);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView inicio;
        public final TextView nombre;
        public final TextView sueldo;
        public ViewHolder(View itemView) {
            super(itemView);
            this.inicio = itemView.findViewById(R.id.tvInicio);
            this.nombre = itemView.findViewById(R.id.tvNombre);
            this.sueldo = itemView.findViewById(R.id.tvSueldo);
        }
    }

    @Override public ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull TurnoControlador lectura) {
        holder.inicio.setText(""+lectura.getInicio());
        holder.nombre.setText(""+lectura.getNombre());
        holder.sueldo.setText(""+lectura.getSueldo());
    }
}