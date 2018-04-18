package com.example.jorgecaro.almacenamiento_jorge_caro.domain.login

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.LoginActivity
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.DonorInteractor
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.UserInteractor
import com.example.jorgecaro.almacenamiento_jorge_caro.model.User

/**
 * Created by jorge_caro on 3/14/18.
 */

class LoginPresenter(private var userInteractor: UserInteractor) : LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    override fun validateName(name: String): Boolean {
        if (name == "") {
            view.showErrorName()
            return false
        }
        return true
    }

    override fun validatePassword(password: String): Boolean {
        if (password == "") {
            view.showErrorPassword()
            return false
        }
        return true
    }

    override fun login(user: User) {
        if (validateName(user.nombre) && validatePassword(user.pass)) {
            if (userInteractor.loginUser(user))
                view.goToMainActivity()
             else
                view.showErrorOnLogin()
        }
    }
}
