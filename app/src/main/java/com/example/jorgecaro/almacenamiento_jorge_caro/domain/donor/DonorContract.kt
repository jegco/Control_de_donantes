package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor

import com.example.jorgecaro.almacenamiento_jorge_caro.model.Donor

/**
 * Created by jorge_caro on 3/15/18.
 */

interface DonorContract {
    interface View {
        fun showErrorPassword()
        fun showPasswordChangedMessage()
        fun refresh()
        fun refresh(id: Int)
        fun logout()
    }

    interface Presenter {
        fun validateFields(name: String, lastname: String, age: String, heigth: String, weigth: String): Boolean
        fun setview(view: DonorContract.View)
        fun editDonor(donor: Donor)
        fun deleteDonor(donor: Donor)
        fun addDonor(donor: Donor)
        fun deleteUser()
        fun searchDonor(id: Int): Donor?
    }
}
