package com.example.proyecto.buscarJugador;


import com.example.proyecto.Realm.JugadorAPIRealm;

import java.util.ArrayList;


public class JugadoresList {
    private static ArrayList<JugadorAPIRealm> jugadores;

    public JugadoresList(){
        jugadores = new ArrayList<JugadorAPIRealm>();

    }

    public void deleteItem(int index) {
        jugadores.remove(index);
    }

    public ArrayList<JugadorAPIRealm> getJugadores(){
        return jugadores;
    }

    public JugadorAPIRealm getItem(int index) {
        return jugadores.get(index);
    }

    public int getIndex(JugadorAPIRealm jugador) {
        int pos = 0;
        for (JugadorAPIRealm i : jugadores) {
            if (jugador.getFirstname().equals(i.getFirstname()) && jugador.getLastname().equals(i.getLastname())) {
                return pos;
            }

            pos++;
        }

        return -1;
    }

    public int getSize() {
        return jugadores.size();
    }

    public void loadJugadores(JugadorAPIRealm posible){

        jugadores.add(posible);

    }


}
