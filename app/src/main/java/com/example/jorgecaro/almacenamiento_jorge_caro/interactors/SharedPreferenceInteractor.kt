package com.example.jorgecaro.almacenamiento_jorge_caro.interactors

import android.content.Context
import android.content.SharedPreferences

import com.example.jorgecaro.almacenamiento_jorge_caro.model.PreferenceConstants
import com.example.jorgecaro.almacenamiento_jorge_caro.model.User

import java.io.File

import android.content.Context.MODE_PRIVATE

/**
 * Created by jorge_caro on 3/15/18.
 */

class SharedPreferenceInteractor(private val context: Context) {
    private val editor: SharedPreferences.Editor
    private val preferences: SharedPreferences = context.getSharedPreferences(PreferenceConstants.PREFERENCE, MODE_PRIVATE)

    val user: User
        get() = User(
                preferences.getString(PreferenceConstants.USER, "n/a"),
                preferences.getString(PreferenceConstants.PASS, "n/a"))

    init {
        editor = preferences.edit()
    }

    internal fun saveUser(user: User) {
        editor.putString(PreferenceConstants.USER, user.nombre)
        editor.putString(PreferenceConstants.PASS, user.pass)
        editor.apply()
    }

    fun clear() {
        val dir = context.filesDir.absolutePath
        val f0 = File(dir, PreferenceConstants.PREFERENCE)
        f0.delete()
        clearSharedPreferences(context)
    }

    private fun clearSharedPreferences(ctx: Context) {
        val dir = File(ctx.filesDir.parent + "/shared_prefs/")
        val children = dir.list()
        for (aChildren in children) {
            ctx.getSharedPreferences(aChildren.replace(".xml", ""), Context.MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply()
        }
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
        }

        for (aChildren in children) File(dir, aChildren).delete()
    }

    internal fun changePassword(password: String) {
        editor.putString("pass", password)
        editor.apply()
    }
}
