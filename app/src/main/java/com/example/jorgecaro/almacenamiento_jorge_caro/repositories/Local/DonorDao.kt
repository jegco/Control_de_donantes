package com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import com.example.jorgecaro.almacenamiento_jorge_caro.model.Donor

import java.util.ArrayList

/**
 * Created by jorge caro on 7/27/2017.
 */

class DonorDao(context: Context) : IDao<Donor> {

    private val database: Database = Database(context)

    override fun add(entity: Donor): Boolean {
        val sq = database.writableDatabase
        val valores = ContentValues()
        valores.put(Database.donorFields[0], entity.id)
        valores.put(Database.donorFields[1], entity.name)
        valores.put(Database.donorFields[2], entity.lastName)
        valores.put(Database.donorFields[3], entity.age)
        valores.put(Database.donorFields[4], entity.height)
        valores.put(Database.donorFields[5], entity.bloodType)
        valores.put(Database.donorFields[6], entity.rh)
        valores.put(Database.donorFields[7], entity.weight)
        val res = sq.insert(Database.donorTable, null, valores)
        return res > 0
    }

    override fun delete(entity: Donor): Boolean {
        val sq = database.writableDatabase
        return sq.delete(Database.donorTable,
                Database.donorFields[0] + "=" + entity.id, null) > 0
    }

    override fun search(): List<Donor> {
        val sq = database.writableDatabase
        val donors = ArrayList<Donor>()
        val cursor = sq.rawQuery("SELECT * FROM " + Database.donorTable, arrayOf())
        if (cursor.moveToFirst()) {
            do {

                donors.add(Donor(cursor.getString(1), cursor.getInt(0),
                        cursor.getString(5), cursor.getString(6),
                        cursor.getString(2), cursor.getInt(3),
                        cursor.getInt(4), cursor.getInt(7)))

            } while (cursor.moveToNext())
        }
        return donors
    }

    override fun checkIfExist(entity: Donor): Boolean {
        return false
    }

    override fun update(entity: Donor): Boolean {
        val sq = database.writableDatabase
        val valores = ContentValues()
        valores.put(Database.donorFields[1], entity.name)
        valores.put(Database.donorFields[2], entity.lastName)
        valores.put(Database.donorFields[3], entity.age)
        valores.put(Database.donorFields[4], entity.height)
        valores.put(Database.donorFields[5], entity.bloodType)
        valores.put(Database.donorFields[6], entity.rh)
        valores.put(Database.donorFields[7], entity.weight)
        val res = sq.update(Database.donorTable,
                valores, Database.donorFields[0] + " = ?", arrayOf(entity.id.toString() + "")).toLong()
        return res > 0
    }

    override fun search(id: Int): List<Donor> {
        val sq = database.writableDatabase
        val donors = ArrayList<Donor>()
        val cursor = sq.rawQuery("SELECT * FROM " + Database.donorTable + " WHERE " + Database.donorFields[0] + " = " + id, null)
        if (cursor.moveToFirst()) {
            do {

                donors.add(Donor(cursor.getString(1), cursor.getInt(0),
                        cursor.getString(5), cursor.getString(6),
                        cursor.getString(2), cursor.getInt(3),
                        cursor.getInt(4), cursor.getInt(7)))

            } while (cursor.moveToNext())
        }
        return donors
    }

    override fun searchById(id: Int): Donor {
        val sq = database.writableDatabase
        lateinit var donor: Donor
        val cursor = sq.rawQuery("SELECT * FROM " + Database.donorTable + " WHERE " + Database.donorFields[0] + " = " + id, null)
        if (cursor.moveToFirst()) {
            do {

                donor = Donor(cursor.getString(1), cursor.getInt(0),
                        cursor.getString(5), cursor.getString(6),
                        cursor.getString(2), cursor.getInt(3),
                        cursor.getInt(4), cursor.getInt(7))

            } while (cursor.moveToNext())
        }
        return donor
    }
}
