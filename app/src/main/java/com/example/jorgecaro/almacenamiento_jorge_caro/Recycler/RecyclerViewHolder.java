package com.example.jorgecaro.almacenamiento_jorge_caro.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView nombre,id,edad,sangre,tipo,estatura,peso;
    private ImageButton editar,eliminar;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
    }
}
