package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.recycler

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jorgecaro.almacenamiento_jorge_caro.R
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.EditDonorFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.model.Donor
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App
import javax.inject.Inject

/**
 * Created by jorge_caro on 3/15/18.
 */

class RecyclerViewAdapter(context: Context, private var donors: List<Donor>, private val supportFragmentManager: FragmentManager) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    @Inject lateinit var editDialog: EditDonorFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = layoutInflater.inflate(R.layout.item, parent, false)
        val viewHolder = RecyclerViewHolder(view)
        App.component.inject(this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val donor = donors[position]
        holder.nombre.text = "Nombre: " + donor.name + " " + donor.lastName
        holder.edad.text = "Edad: " + donor.age + ""
        holder.estatura.text = "Estatura: " + donor.height + ""
        holder.id.text = "Numero de identificacion: " + donor.id + ""
        holder.peso.text = "Peso: " + donor.weight + ""
        holder.sangre.text = "Tipo de sangre: " + donor.bloodType
        holder.tipo.text = donor.rh
        holder.editar.setOnClickListener {
            val arg = Bundle()
            arg.putInt("donor", donor.id)
            editDialog.arguments = arg
            editDialog.show(supportFragmentManager, "Edit") }
//        holder.eliminar.setOnClickListener { donorDialog.delete(donor).show() }
    }

    override fun getItemCount(): Int {
        return donors.size
    }
}
