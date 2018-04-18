package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jorgecaro.almacenamiento_jorge_caro.R
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorPresenter
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App
import kotlinx.android.synthetic.main.fragment_delete_user.*
import javax.inject.Inject

class DeleteUserFragment : DialogFragment() {

    @Inject lateinit var presenter: DonorPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        App.component.inject(this)

        return inflater!!.inflate(R.layout.fragment_delete_user, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delete_user_button.setOnClickListener { presenter.deleteUser() }
        cancel.setOnClickListener { dismiss() }
    }

    companion object {
        val instance: DeleteUserFragment by lazy {
            DeleteUserFragment()
        }
    }
}
