package com.example.jorgecaro.almacenamiento_jorge_caro.interactors

import android.content.Context

import com.example.jorgecaro.almacenamiento_jorge_caro.model.User
import com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local.UserDao

/**
 * Created by jorge_caro on 3/14/18.
 */

class UserInteractor(private val userDao: UserDao,
                     private val sharedPreferenceInteractor: SharedPreferenceInteractor) {

    fun loginUser(user: User): Boolean {
        if (userDao.checkIfExist(user)) {
            sharedPreferenceInteractor.saveUser(user)
            return true
        }
        return false
    }

    fun deleteUser(): Boolean {
        userDao.delete(sharedPreferenceInteractor.user)
        sharedPreferenceInteractor.clear()
        return false
    }

    fun changeUserPassword(password: String) {
        val user = sharedPreferenceInteractor.user
        user.pass = password
        userDao.update(user)
        sharedPreferenceInteractor.changePassword(password)
    }
}
