package com.example.jorgecaro.almacenamiento_jorge_caro.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jorgecaro.almacenamiento_jorge_caro.R;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView nombre,id,edad,sangre,tipo,estatura,peso;
    private ImageButton editar,eliminar;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.nombre);
        id = (TextView) itemView.findViewById(R.id.id);
        edad = (TextView) itemView.findViewById(R.id.edad);
        sangre = (TextView) itemView.findViewById(R.id.sangre);
        tipo = (TextView) itemView.findViewById(R.id.tipo);
        estatura = (TextView) itemView.findViewById(R.id.estatura);
        peso = (TextView) itemView.findViewById(R.id.peso);
        editar = (ImageButton) itemView.findViewById(R.id.editar);
        eliminar = (ImageButton) itemView.findViewById(R.id.eliminar);
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getId() {
        return id;
    }

    public TextView getEdad() {
        return edad;
    }

    public TextView getSangre() {
        return sangre;
    }

    public TextView getTipo() {
        return tipo;
    }

    public TextView getEstatura() {
        return estatura;
    }

    public TextView getPeso() {
        return peso;
    }

    public ImageButton getEditar() {
        return editar;
    }

    public ImageButton getEliminar() {
        return eliminar;
    }
}
