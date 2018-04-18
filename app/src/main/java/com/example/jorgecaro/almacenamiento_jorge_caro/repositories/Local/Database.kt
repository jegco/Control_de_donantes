package com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by jorge caro on 7/27/2017.
 */

class Database(context: Context) : SQLiteOpenHelper(context, NAME, null, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createUsersTable)
        db.execSQL(createDonorsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sqlDrop = "DROP TABLE IF EXISTS " + userTable
        val sqlDrop2 = "DROP TABLE IF EXISTS " + donorTable
        db.execSQL(sqlDrop)
        db.execSQL(sqlDrop2)
        onCreate(db)
    }

    companion object {
        val NAME = "control_de_donantes"
        val version = 2
        val userTable = "user"
        val donorTable = "donor"
        @JvmStatic
        val userFields = arrayOf("ID", "NOMBRE", "PASS")
        @JvmStatic
        val donorFields = arrayOf("ID", "NOMBRE", "APELLIDO", "EDAD", "ESTATURA", "SANGRE", "TIPO", "PESO")
        val createUsersTable = ("CREATE TABLE "
                + userTable + "(" + userFields[0] + " INT AUTO_INCREMENT,"
                + userFields[1] + " TEXT NOT NULL, "
                + userFields[2] + " TEXT NOT NULL,"
                + "PRIMARY KEY(" + userFields[0] + "))")
        @JvmStatic
        val createDonorsTable = ("CREATE TABLE "
                + donorTable + "(" + donorFields[0] + " INT AUTO_INCREMENT,"
                + donorFields[1] + " TEXT NOT NULL, "
                + donorFields[2] + " TEXT NOT NULL, "
                + donorFields[3] + " INT NOT NULL, "
                + donorFields[4] + " INT NOT NULL, "
                + donorFields[5] + " TEXT NOT NULL,"
                + donorFields[6] + " TEXT NOT NULL, "
                + donorFields[7] + " INT NOT NULL, "
                + "PRIMARY KEY(" + donorFields[0] + "))")
    }

}
