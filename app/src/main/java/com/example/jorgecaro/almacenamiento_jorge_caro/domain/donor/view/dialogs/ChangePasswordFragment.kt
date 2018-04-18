package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jorgecaro.almacenamiento_jorge_caro.R
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorPresenter
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App
import kotlinx.android.synthetic.main.fragment_change_password.*
import javax.inject.Inject

class ChangePasswordFragment : DialogFragment() {

    @Inject lateinit var presenter: DonorPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        App.component.inject(this)

        return inflater!!.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        change_password.setOnClickListener {
            presenter.changePassword(password.editText!!.text.toString(), new_password.editText!!.text.toString())
        }
    }

    companion object {
        val instance: ChangePasswordFragment by lazy {
            ChangePasswordFragment()
        }
    }
}
