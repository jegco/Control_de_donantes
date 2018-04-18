package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view

import android.support.v7.app.AlertDialog
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputLayout
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import com.example.jorgecaro.almacenamiento_jorge_caro.R
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorPresenter
import com.example.jorgecaro.almacenamiento_jorge_caro.model.Donor
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App
import javax.inject.Inject

/**
 * Created by jorge_caro on 3/15/18.
 */

class DonorDialog(private val context: Context,
                  private val inflater: LayoutInflater) {

    @Inject lateinit var presenter: DonorPresenter

    init {

    }

    fun edit(donante: Donor): AlertDialog {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AppTheme))
        builder.setTitle("Editar parqueo")
        val layout = inflater.inflate(R.layout.fragment_template_donor, null) as LinearLayout
        builder.setView(layout)
        val til_id: TextInputLayout
        val til_nombre_donante: TextInputLayout
        val til_apellido_usuario: TextInputLayout
        val til_edad: TextInputLayout
        val til_estatura: TextInputLayout
        val til_peso: TextInputLayout
        val til_sangre: Spinner
        val til_tipo: Spinner
        til_id = layout.findViewById<TextInputLayout>(R.id.til_id)
        til_nombre_donante = layout.findViewById<TextInputLayout>(R.id.til_name)
        til_apellido_usuario = layout.findViewById<TextInputLayout>(R.id.til_last_name)
        til_edad = layout.findViewById<TextInputLayout>(R.id.til_age)
        til_estatura = layout.findViewById<TextInputLayout>(R.id.til_height)
        til_peso = layout.findViewById<TextInputLayout>(R.id.til_weight)
        til_sangre = layout.findViewById<Spinner>(R.id.bloodType)
        til_tipo = layout.findViewById<Spinner>(R.id.rh)
        til_id.editText!!.setText(donante.id.toString() + "")
        til_id.editText!!.isEnabled = false
        til_nombre_donante.editText!!.setText(donante.name)
        til_apellido_usuario.editText!!.setText(donante.lastName)
        til_edad.editText!!.setText(donante.age.toString() + "")
        til_estatura.editText!!.setText(donante.height.toString() + "")
        til_peso.editText!!.setText(donante.weight.toString() + "")
        var adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_item, context.resources
                .getStringArray(R.array.bloodType))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        til_sangre.adapter = adapter
        val pos_sangre = adapter.getPosition(donante.bloodType)
        til_sangre.setSelection(pos_sangre)
        adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_item, context.resources
                .getStringArray(R.array.rh))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        til_tipo.adapter = adapter
        val pos_tipo = adapter.getPosition(donante.rh)
        til_tipo.setSelection(pos_tipo)
        builder.setPositiveButton("Aceptar") { dialog, which ->
            if (presenter.validateFields(
                    til_nombre_donante.editText!!.text.toString(),
                    til_apellido_usuario.editText!!.text.toString(),
                    til_edad.editText!!.text.toString(),
                    til_estatura.editText!!.text.toString(),
                    til_peso.editText!!.text.toString())) {

                val donor = Donor(til_nombre_donante.editText!!.text.toString(), Integer.parseInt(til_id.editText!!.text.toString()), til_sangre.selectedItem.toString(), til_tipo.selectedItem.toString(), til_apellido_usuario.editText!!.text.toString(), Integer.parseInt(til_edad.editText!!.text.toString()), Integer.parseInt(til_peso.editText!!.text.toString()), Integer.parseInt(til_estatura.editText!!.text.toString()))

                dialog.dismiss()
                presenter.editDonor(donor)

            } else if (til_nombre_donante.editText!!.text.toString() == "" ||
                    til_id.editText!!.text.toString() == "" ||
                    til_edad.editText!!.text.toString() == "" ||
                    til_apellido_usuario.editText!!.text.toString() == "" ||
                    til_estatura.editText!!.text.toString() == "" ||
                    til_peso.editText!!.text.toString() == "") {

                til_nombre_donante.error = context.resources.getString(R.string.llenar)
                til_edad.error = context.resources.getString(R.string.llenar)
                til_id.error = context.resources.getString(R.string.llenar)
                til_estatura.error = context.resources.getString(R.string.llenar)
                til_peso.error = context.resources.getString(R.string.llenar)
                til_apellido_usuario.error = context.resources.getString(R.string.llenar)
            }
        }
        builder.setNegativeButton("Cancelar") { dialog, which -> dialog.cancel() }
        return builder.create()
    }

    fun delete(donor: Donor): AlertDialog {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AppTheme))
        builder.setTitle("Confirmacion")
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val matricula = TextView(context)
        matricula.text = "Desea eliminar al donor?"
        layout.addView(matricula)
        builder.setView(layout)
        builder.setPositiveButton("si") { dialog, which ->
            dialog.cancel()
            presenter.deleteDonor(donor)
        }

        builder.setNegativeButton("no") { dialog, which -> dialog.cancel() }
        return builder.create()
    }

    fun add(): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Registrar donor")
        val layoutInflater = LayoutInflater.from(context)
        val layout: ConstraintLayout = layoutInflater.inflate(R.layout.fragment_template_donor, null) as ConstraintLayout
        builder.setView(layout)

        val id: TextInputLayout
        val nombre: TextInputLayout
        val apellido: TextInputLayout
        val edad: TextInputLayout
        val estatura: TextInputLayout
        val peso: TextInputLayout
        val sangre: Spinner
        val tipo: Spinner

        id = layout.findViewById<TextInputLayout>(R.id.til_id)
        nombre = layout.findViewById<TextInputLayout>(R.id.til_name)
        apellido = layout.findViewById<TextInputLayout>(R.id.til_last_name)
        edad = layout.findViewById<TextInputLayout>(R.id.til_age)
        estatura = layout.findViewById<TextInputLayout>(R.id.til_height)
        peso = layout.findViewById<TextInputLayout>(R.id.til_weight)
        sangre = layout.findViewById<Spinner>(R.id.bloodType)
        tipo = layout.findViewById<Spinner>(R.id.rh)

        var adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_item,
                context.resources.getStringArray(R.array.bloodType))

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sangre.adapter = adapter

        adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_item,
                context.resources.getStringArray(R.array.rh))

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipo.adapter = adapter

        builder.setPositiveButton("Registrar") { dialog, which ->
            if (presenter.validateFields(
                    nombre.editText!!.text.toString(),
                    apellido.editText!!.text.toString(),
                    edad.editText!!.text.toString(),
                    estatura.editText!!.text.toString(),
                    peso.editText!!.text.toString())) {

                val donor = Donor(nombre.editText!!.text.toString(), Integer.parseInt(id.editText!!.text.toString()), sangre.selectedItem.toString(), tipo.selectedItem.toString(), apellido.editText!!.text.toString(), Integer.parseInt(edad.editText!!.text.toString()), Integer.parseInt(peso.editText!!.text.toString()), Integer.parseInt(estatura.editText!!.text.toString()))

                presenter.addDonor(donor)
                dialog.dismiss()

            } else if (nombre.editText!!.text.toString() == "" ||
                    id.editText!!.text.toString() == "" ||
                    edad.editText!!.text.toString() == "" ||
                    apellido.editText!!.text.toString() == "" ||
                    estatura.editText!!.text.toString() == "" ||
                    peso.editText!!.text.toString() == "") {

                nombre.error = context.resources.getString(R.string.llenar)
                edad.error = context.resources.getString(R.string.llenar)
                id.error = context.resources.getString(R.string.llenar)
                estatura.error = context.resources.getString(R.string.llenar)
                peso.error = context.resources.getString(R.string.llenar)
                apellido.error = context.resources.getString(R.string.llenar)
            }
        }
        builder.setNegativeButton("Cancelar") { dialog, which -> dialog.dismiss() }
        return builder.create()
    }
}
