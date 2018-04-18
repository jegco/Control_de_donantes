package com.example.jorgecaro.almacenamiento_jorge_caro.interactors

import com.example.jorgecaro.almacenamiento_jorge_caro.model.Donor
import com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local.DonorDao

/**
 * Created by jorge_caro on 3/15/18.
 */

class DonorInteractor(private var donorDao: DonorDao) {

    fun addDonor(donor: Donor) {
        this.donorDao.add(donor)
    }

    fun searchDonorsById(id: Int): List<Donor> {
        return donorDao.search(id)
    }

    fun searchDonors(): List<Donor> {
        return donorDao.search()
    }

    fun deleteDonor(donor: Donor) {
        donorDao.delete(donor)
    }

    fun updateDonor(donor: Donor) {
        donorDao.update(donor)
    }
}
