package com.example.proyecto.Realm;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class JugadorRealm extends RealmObject {
    @PrimaryKey
    String id = ObjectId.get().toHexString();

    @Required
    private String name;

    @Required
    private String posicion;

    private int edad;

    private int dorsal;

    /*@LinkingObjects("jugadores")
    final RealmResults<EquipoRealm> equipos = null;*/

    public JugadorRealm() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
