package com.example.jorgecaro.almacenamiento_jorge_caro.Modelo;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class Usuario {
    private String nombre, pass;

    public Usuario(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
