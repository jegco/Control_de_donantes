package com.example.jorgecaro.almacenamiento_jorge_caro.domain.login

import com.example.jorgecaro.almacenamiento_jorge_caro.model.User

/**
 * Created by jorge_caro on 3/14/18.
 */

interface LoginContract {
    interface View {
        fun showErrorName()
        fun showErrorPassword()
        fun openSignUpDialog()
        fun login(user: User)
        fun goToMainActivity()
        fun showErrorOnLogin()
    }

    interface Presenter {
        fun setView(view: View)
        fun login(user: User)
        fun validateName(name: String): Boolean
        fun validatePassword(password: String): Boolean
    }
}
