package com.example.proyecto.Realm;

import static io.realm.Realm.getApplicationContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import io.realm.Realm;

public class OperacionesJugadoresAPI {

    public static void anyadirjugadorApi(JugadorAPIRealm jugador){
        Realm realm = Realm.getDefaultInstance();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);

        String user = preferences.getString("nickname","");

        UsuarioRealm usuario = realm.where(UsuarioRealm.class).equalTo("nickname", user).findFirst();



        System.out.println("JUGADOR REPETIO: "+jugador.getId()+"-"+jugador.getFirstname()+"-"+jugador.getLastname());

        if(isFavorite(jugador)){
            System.out.println("TA REPETIO");
            System.out.println(usuario.getJugadoresapi().size());

            System.out.println("EL JUGADOR ES: "+jugador.getFirstname()+" - "+jugador.getLastname());

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    System.out.println("EL USUARIO ES: "+usuario);
                    if(devPosicionJugador(jugador)>=0){
                        usuario.getJugadoresapi().deleteFromRealm(devPosicionJugador(jugador));
                        System.out.println(usuario.getJugadoresapi().size());
                        System.out.println("SE ELIMINA");
                        realm.insert(usuario);
                    }
                    else{
                        System.out.println("ERROR");
                    }

                }
            });
            Toast.makeText(getApplicationContext(), "Jugador eliminado de favoritos", Toast.LENGTH_SHORT).show();

        }else{
            System.out.println("SE INSERTa");
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    usuario.getJugadoresapi().add(jugador);
                    realm.insert(usuario);
                }
            });

            Toast.makeText(getApplicationContext(), "Jugador añadido a favoritos", Toast.LENGTH_SHORT).show();

        }


    }


    public static boolean isFavorite(JugadorAPIRealm jugador){
        Realm realm = Realm.getDefaultInstance();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);

        String user = preferences.getString("nickname","");

        UsuarioRealm usuario = realm.where(UsuarioRealm.class).equalTo("nickname", user).findFirst();


        for(JugadorAPIRealm r : usuario.getJugadoresapi()){
            if(r.getFirstname().equals(jugador.getFirstname()) && r.getLastname().equals(jugador.getLastname())){
                return true;
            }
        }

        return false;
    }

    public static int devPosicionJugador(JugadorAPIRealm jugador){
        Realm realm = Realm.getDefaultInstance();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);

        String user = preferences.getString("nickname","");

        UsuarioRealm usuario = realm.where(UsuarioRealm.class).equalTo("nickname", user).findFirst();

        for(int i = 0; i<usuario.getJugadoresapi().size(); i++){
            if(jugador.getFirstname().equals(usuario.getJugadoresapi().get(i).getFirstname()) &&
                    jugador.getLastname().equals(usuario.getJugadoresapi().get(i).getLastname())){
                return i;
            }
        }

        return -1;
    }
}
