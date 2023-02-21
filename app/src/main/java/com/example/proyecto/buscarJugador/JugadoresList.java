package com.example.proyecto.buscarJugador;


import java.util.ArrayList;


public class JugadoresList {
    private static ArrayList<String> jugadores;

    public JugadoresList(){
        jugadores = new ArrayList<String>();

    }

    public void deleteItem(int index) {
        jugadores.remove(index);
    }

    public ArrayList<String> getJugadores(){
        return jugadores;
    }

    public String getItem(int index) {
        return jugadores.get(index);
    }

    public int getIndex(String jugador) {
        int pos = 0;
        for (String i : jugadores) {
            if (jugador.equals(i)) {
                return pos;
            }

            pos++;
        }

        return -1;
    }

    public int getSize() {
        return jugadores.size();
    }

    public void loadJugadores(String posible){

        jugadores.add(posible);

    }


}
