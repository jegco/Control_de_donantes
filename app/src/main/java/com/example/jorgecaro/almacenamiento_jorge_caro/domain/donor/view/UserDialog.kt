package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorPresenter

import javax.inject.Inject

/**
 * Created by jorge_caro on 3/15/18.
 */

class UserDialog(private val context: Context,
                 private val presenter: DonorPresenter) {

    fun update(): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirmacion")
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val pass = EditText(context)
        pass.hint = "Password"
        layout.addView(pass)
        val pass_n = EditText(context)
        pass_n.hint = "Nueva password"

        layout.addView(pass_n)
        builder.setView(layout)

        builder.setPositiveButton("si") { dialog, which ->
            dialog.dismiss()

            presenter.changePassword(pass.text.toString(), pass_n.text.toString())
        }

        builder.setNegativeButton("no") { dialog, which -> dialog.dismiss() }

        return builder.create()
    }

    fun delete(): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirmacion")
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        val matricula = TextView(context)
        matricula.text = "Desea eliminar al usuario?"
        layout.addView(matricula)
        builder.setView(layout)

        builder.setPositiveButton("si") { dialog, which ->
            dialog.cancel()
            presenter.deleteUser()
        }

        builder.setNegativeButton("no") { dialog, which -> dialog.cancel() }

        return builder.create()
    }

}
