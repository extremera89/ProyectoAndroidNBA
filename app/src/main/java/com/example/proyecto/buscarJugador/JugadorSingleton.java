package com.example.proyecto.buscarJugador;

public class JugadorSingleton {
    private static final JugadorSingleton INSTANCE = new JugadorSingleton();
    private static JugadoresList itemList;



    public JugadorSingleton() {
        itemList = new JugadoresList();
    }

    public static JugadorSingleton getInstance() {
        return INSTANCE;
    }

    public static JugadoresList getItemList() {
        return itemList;
    }
}
