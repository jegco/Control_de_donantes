package com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local;

import java.util.List;

/**
 * Created by jorge_caro on 3/14/18.
 */

public interface IDao<T> {
    boolean add(T entity);
    boolean delete(T entity);
    List<T> search();
    boolean checkIfExist(T entity);
    boolean update(T entity);
    List<T> search(int id);
}