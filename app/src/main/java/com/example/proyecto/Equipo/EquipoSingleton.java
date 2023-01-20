package com.example.proyecto.Equipo;

public class EquipoSingleton {

    private static final EquipoSingleton INSTANCE = new EquipoSingleton();
    private static EquipoLista itemList;



    public EquipoSingleton() {
        itemList = new EquipoLista();
    }

    public static EquipoSingleton getInstance() {
        return INSTANCE;
    }

    public static EquipoLista getItemList() {
        return itemList;
    }



    public static void setItemList(EquipoLista itemList) {
        EquipoSingleton.itemList = itemList;
    }
}
