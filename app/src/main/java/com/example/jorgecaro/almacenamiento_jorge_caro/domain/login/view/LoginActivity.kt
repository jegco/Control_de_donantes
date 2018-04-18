package com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import com.example.jorgecaro.almacenamiento_jorge_caro.R
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.MainActivity
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.LoginContract
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.LoginPresenter
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.dialogs.SignUpFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.model.User
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App
import kotlinx.android.synthetic.main.activity_login.*

import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject lateinit var presenter: LoginPresenter
    @Inject lateinit var signUpDialog: SignUpFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        App.component.inject(this)

        button_iniciar_sesion.setOnClickListener {
            val user = User(til_nombre_usuario.editText!!.text.toString(), til_password.editText!!.text.toString())
            login(user)
        }

        button_registrar_usuario.setOnClickListener { openSignUpDialog() }
    }

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
    }

    override fun showErrorName() {
        til_nombre_usuario.editText!!.error = ""
        til_nombre_usuario.editText!!.error = resources.getString(R.string.llenar)
    }

    override fun showErrorPassword() {
        til_password.editText!!.error = ""
        til_password.editText!!.error = resources.getString(R.string.llenar)
    }

    override fun openSignUpDialog() {
        signUpDialog.show(supportFragmentManager, "Sign up")
    }

    override fun login(user: User) {
        presenter.login(user)
    }

    override fun goToMainActivity() {
        val i = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(i)
    }

    override fun showErrorOnLogin() {
        Toast.makeText(applicationContext, "El user no existe", Toast.LENGTH_SHORT).show()
    }
}
