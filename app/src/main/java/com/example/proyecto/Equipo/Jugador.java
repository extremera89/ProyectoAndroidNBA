package com.example.proyecto.Equipo;
public class Jugador {

    private String name;
    private String posicion;
    private int edad;
    private int dorsal;

    public Jugador(String first_name, String last_name, int edad, int dorsal) {
        this.name = first_name;
        this.posicion = last_name;
        this.edad = edad;
        this.dorsal = dorsal;
    }

    public Jugador(String first_name){
        this.name = first_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }
}
