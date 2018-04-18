package com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local

import android.content.ContentValues
import android.content.Context
import com.example.jorgecaro.almacenamiento_jorge_caro.model.User

/**
 * Created by jorge caro on 7/27/2017.
 */

class UserDao(context: Context) : IDao<User> {

    private var database = Database(context)

    override fun add(entity: User): Boolean {
        val sq = database.writableDatabase
        val values = ContentValues()
        values.put(Database.userFields[1], entity.nombre)
        values.put(Database.userFields[2], entity.pass)
        val res = sq.insert(Database.userTable, null, values)
        return res > 0
    }

    override fun checkIfExist(entity: User): Boolean {
        val sq = database.writableDatabase
        val cursor = sq.rawQuery("SELECT * FROM USER WHERE " + Database.userFields[1] + " = ? AND " + Database.userFields[2] + " = ?", arrayOf(entity.nombre, entity.pass))
        return cursor.count > 0
    }

    override fun update(entity: User): Boolean {
        val sq = database.writableDatabase
        val valores = ContentValues()
        val res = sq.update(Database.userTable,
                valores, Database.userFields[0] + " = ? ", arrayOf(entity.id.toString() + "")).toLong()
        return res > 0
    }

    override fun search(id: Int): List<User> {
        TODO()
    }

    override fun delete(entity: User): Boolean {
        val sq = database.writableDatabase
        return sq.delete(Database.userTable,
                Database.userFields[1] + "= ?"
                        + " AND " + Database.userFields[2] + "= ?", arrayOf(entity.nombre, entity.pass)) > 0
    }

    override fun search(): List<User> {
        TODO()
    }

    override fun searchById(id: Int): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
