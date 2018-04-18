package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.jorgecaro.almacenamiento_jorge_caro.R
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorPresenter
import com.example.jorgecaro.almacenamiento_jorge_caro.model.Donor
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App
import kotlinx.android.synthetic.main.fragment_template_donor.*
import javax.inject.Inject

class AddDonorFragment : DialogFragment() {

    @Inject lateinit var presenter: DonorPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        App.component.inject(this)
        return inflater!!.inflate(R.layout.fragment_template_donor, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_item,
                context.resources.getStringArray(R.array.bloodType))

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bloodType.adapter = adapter

        adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_item,
                context.resources.getStringArray(R.array.rh))

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rh.adapter = adapter

        add.setOnClickListener { add() }
        cancel.setOnClickListener { dismiss() }
    }

    fun add() {
        if (presenter.validateFields(
                til_name.editText!!.text.toString(),
                til_last_name.editText!!.text.toString(),
                til_age.editText!!.text.toString(),
                til_height.editText!!.text.toString(),
                til_weight.editText!!.text.toString())) {

            val donor = Donor(til_name.editText!!.text.toString(),
                    Integer.parseInt(til_id.editText!!.text.toString()),
                    bloodType.selectedItem.toString(), rh.selectedItem.toString(),
                    til_last_name.editText!!.text.toString(),
                    Integer.parseInt(til_age.editText!!.text.toString()),
                    Integer.parseInt(til_weight.editText!!.text.toString()),
                    Integer.parseInt(til_height.editText!!.text.toString()))

            presenter.addDonor(donor)
            dialog.dismiss()

        } else if (til_name.editText!!.text.toString() == "" ||
                til_id.editText!!.text.toString() == "" ||
                til_age.editText!!.text.toString() == "" ||
                til_last_name.editText!!.text.toString() == "" ||
                til_height.editText!!.text.toString() == "" ||
                til_weight.editText!!.text.toString() == "") {

            til_last_name.error = context.resources.getString(R.string.llenar)
            til_age.error = context.resources.getString(R.string.llenar)
            til_id.error = context.resources.getString(R.string.llenar)
            til_age.error = context.resources.getString(R.string.llenar)
            til_weight.error = context.resources.getString(R.string.llenar)
            til_last_name.error = context.resources.getString(R.string.llenar)
        }
    }

    companion object {
        val instance: AddDonorFragment by lazy {
            AddDonorFragment()
        }
    }
}
