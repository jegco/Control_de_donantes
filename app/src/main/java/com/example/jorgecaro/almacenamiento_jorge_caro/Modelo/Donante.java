package com.example.jorgecaro.almacenamiento_jorge_caro.Modelo;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class Donante {
    private String nombre, sangre, tipo, apellido;
    private int edad, peso, estatura, id;

    public Donante(String nombre, int id, String sangre, String tipo, String apellido, int edad, int peso, int estatura) {
        this.nombre = nombre;
        this.id = id;
        this.sangre = sangre;
        this.tipo = tipo;
        this.apellido = apellido;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }
}
