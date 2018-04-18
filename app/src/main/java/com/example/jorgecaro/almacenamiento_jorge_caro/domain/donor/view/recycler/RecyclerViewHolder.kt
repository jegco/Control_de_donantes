package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

import com.example.jorgecaro.almacenamiento_jorge_caro.R

/**
 * Created by jorge_caro on 3/15/18.
 */

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val nombre: TextView
    val id: TextView
    val edad: TextView
    val sangre: TextView
    val tipo: TextView
    val estatura: TextView
    val peso: TextView
    val editar: ImageButton
    val eliminar: ImageButton

    init {
        nombre = itemView.findViewById<View>(R.id.name) as TextView
        id = itemView.findViewById<View>(R.id.id) as TextView
        edad = itemView.findViewById<View>(R.id.age) as TextView
        sangre = itemView.findViewById<View>(R.id.bloodType) as TextView
        tipo = itemView.findViewById<View>(R.id.rh) as TextView
        estatura = itemView.findViewById<View>(R.id.heigth) as TextView
        peso = itemView.findViewById<View>(R.id.weigth) as TextView
        editar = itemView.findViewById<View>(R.id.editar) as ImageButton
        eliminar = itemView.findViewById<View>(R.id.eliminar) as ImageButton
    }
}