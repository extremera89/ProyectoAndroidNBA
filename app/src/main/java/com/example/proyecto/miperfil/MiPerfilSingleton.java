package com.example.proyecto.miperfil;


public class MiPerfilSingleton {
    private static final MiPerfilSingleton INSTANCE = new MiPerfilSingleton();
    private static MisEquiposList itemList;



    public MiPerfilSingleton() {
        itemList = new MisEquiposList();
    }

    public static MiPerfilSingleton getInstance() {
        return INSTANCE;
    }

    public static MisEquiposList getItemList() {
        return itemList;
    }
}
