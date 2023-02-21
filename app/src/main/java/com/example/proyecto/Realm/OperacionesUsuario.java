package com.example.proyecto.Realm;

import androidx.annotation.NonNull;

import io.realm.Realm;

public class OperacionesUsuario {
    static Realm realm = Realm.getDefaultInstance();


    public static void insertarUsuarioNuevo(String nick, String email, String pass){
        UsuarioRealm user = new UsuarioRealm(nick, email, pass);


        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.insert(user);
            }
        });


    }

    public static boolean comprobarLogin(String nick, String pass){
        boolean res = false;


        UsuarioRealm usuarioacomprobar = realm.where(UsuarioRealm.class).equalTo("nickname", nick).findFirst();

        if(usuarioacomprobar!=null ){
            if(usuarioacomprobar.getPassword().equals(pass)) {
                System.out.println("USUARIO Y CONTRASEÑA TRUEEEE");
                res = true;
            }
        }
        else{
            System.out.println("USUARIO O CONTRASEÑA FALSO");
            res = false;
        }

        return res;

    }



}
