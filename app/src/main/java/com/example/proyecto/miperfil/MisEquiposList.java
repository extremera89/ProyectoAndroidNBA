package com.example.proyecto.miperfil;

import static io.realm.Realm.getApplicationContext;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.proyecto.Realm.EquipoRealm;
import com.example.proyecto.Realm.UsuarioRealm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MisEquiposList {
    private static ArrayList<EquipoRealm> misequipos;
    Realm realm = Realm.getDefaultInstance();

    public MisEquiposList(){
        misequipos = new ArrayList<EquipoRealm>();
    }

    public void deleteItem(int index) {
        misequipos.remove(index);
    }

    public EquipoRealm getItem(int index) {
        return misequipos.get(index);
    }

    public int getIndex(EquipoRealm equipo) {
        int pos = 0;
        for (EquipoRealm i : misequipos) {
            if (equipo.getName().equals(i.getName())) {
                return pos;
            }

            pos++;
        }

        return -1;
    }

    public int getSize() {
        return misequipos.size();
    }

    public void loadEquipos(){
        misequipos.clear();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        String usuario = preferences.getString("nickname","");

        System.out.println("USA METODO LOADEQUIPOS");

        List<UsuarioRealm> usuarios = realm.where(UsuarioRealm.class).findAll();

        for(UsuarioRealm u : usuarios ){
            if(u.getNickname().equals(usuario)){
                System.out.println("SON EL MISMO USUARIO");
                System.out.println(u.getNickname());
                for(EquipoRealm ele : u.getEquipos()){
                    misequipos.add(ele);
                }

            }
        }


    }

}
