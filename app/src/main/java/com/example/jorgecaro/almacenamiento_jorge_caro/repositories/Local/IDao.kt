package com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local

/**
 * Created by jorge_caro on 3/14/18.
 */

interface IDao<T> {
    fun add(entity: T): Boolean
    fun delete(entity: T): Boolean
    fun search(): List<T>
    fun checkIfExist(entity: T): Boolean
    fun update(entity: T): Boolean
    fun search(id: Int): List<T>
    fun searchById(id: Int): T
}