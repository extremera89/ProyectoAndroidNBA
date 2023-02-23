package com.example.proyecto.Realm;

import org.bson.types.ObjectId;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class UsuarioRealm extends RealmObject{
    @PrimaryKey
    String id = ObjectId.get().toHexString();

    @Required
    String nickname;

    @Required
    String email;

    @Required
    String password;

    RealmList<EquipoRealm> equipos;

    RealmList<JugadorAPIRealm> jugadoresapi;


    public UsuarioRealm(){

    }

    public UsuarioRealm(String nick, String email, String pass){
        this.nickname = nick;
        this.email = email;
        this.password = pass;
        equipos = new RealmList<>();
        jugadoresapi = new RealmList<>();
    }

    public UsuarioRealm(String nick, String pass) {
        this.nickname = nick;
        this.password = pass;
        equipos = new RealmList<>();
        jugadoresapi = new RealmList<>();

    }

    public UsuarioRealm(String nickname, String email, String password, RealmList<EquipoRealm> equipos) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.equipos = equipos;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RealmList<EquipoRealm> getEquipos() {
        return equipos;
    }

    public void setEquipos(RealmList<EquipoRealm> equipos) {
        this.equipos = equipos;
    }

    public RealmList<JugadorAPIRealm> getJugadoresapi() {
        return jugadoresapi;
    }

    public void setJugadoresapi(RealmList<JugadorAPIRealm> jugadoresapi) {
        this.jugadoresapi = jugadoresapi;
    }



}
