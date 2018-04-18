package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.MainActivity
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.DonorInteractor
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.UserInteractor
import com.example.jorgecaro.almacenamiento_jorge_caro.model.Donor

/**
 * Created by jorge_caro on 3/15/18.
 */

class DonorPresenter(private val userInteractor: UserInteractor,
                     private val donorInteractor: DonorInteractor) : DonorContract.Presenter {

    private lateinit var view: DonorContract.View
    private lateinit var donors: List<Donor>


    override fun validateFields(name: String, lastname: String, age: String, heigth: String, weigth: String): Boolean {
        return name != "" && lastname != "" && age != "" && heigth != "" && weigth != ""
    }


    override fun setview(view: DonorContract.View) {
        this.view = view
    }

    fun listDonorByID(id: Int): List<Donor> {
        this.donors = donorInteractor.searchDonorsById(id)
        return donors
    }

    fun listDonors(): List<Donor> {
        this.donors = donorInteractor.searchDonors()
        return donors
    }

    override fun editDonor(donor: Donor) {
        donorInteractor.updateDonor(donor)
        view.refresh()
    }

    override fun deleteUser() {
        userInteractor.deleteUser()
        view.logout()
    }

    fun changePassword(password: String, newPasswod: String) {
        if (validatePassword(password, newPasswod)) {
            userInteractor.changeUserPassword(newPasswod)
            view.showPasswordChangedMessage()
        } else
            showErrorPassword()
    }

    private fun validatePassword(password: String, newPassword: String): Boolean {
        return password != newPassword || password != "" && newPassword != ""
    }

    private fun showErrorPassword() {
        view.showErrorPassword()
    }

    override fun addDonor(donor: Donor) {
        donorInteractor.addDonor(donor)
        view.refresh()
    }

    override fun deleteDonor(donor: Donor) {
        donorInteractor.deleteDonor(donor)
        view.refresh()
    }

    override fun searchDonor(id: Int): Donor? {
        for (donor in donors) {
            if (donor.id == id) return donor
        }
        return null
    }
}
