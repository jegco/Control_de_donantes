package com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jorgecaro.almacenamiento_jorge_caro.R
import com.example.jorgecaro.almacenamiento_jorge_caro.model.User
import com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local.UserDao
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject

class SignUpFragment : DialogFragment() {

    @Inject lateinit var userDao: UserDao

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        App.component.inject(this)
        return inflater!!.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sign_up.setOnClickListener{
            if (validateName(til_name.editText!!.text.toString()) && validatePassword(til_password.editText!!.text.toString(), til_confirm_password.editText!!.text.toString())) {
                val user = User(til_name.editText!!.text.toString(), til_password.editText!!.text.toString())
                if (signUp(user)) {
                    Toast.makeText(context, "usuario registrado correctamente", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                } else
                    Toast.makeText(context, "error al ingresar los datos", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateName(username: String): Boolean {
        return username != ""
    }

    private fun validatePassword(password: String, passwordToConfirm: String): Boolean {
        return !(password == "" || passwordToConfirm == "" || password != passwordToConfirm)
    }

    private fun signUp(user: User): Boolean {
        return userDao.add(user)
    }

    companion object {
        val instance: SignUpFragment by lazy {
            SignUpFragment()
        }
    }
}