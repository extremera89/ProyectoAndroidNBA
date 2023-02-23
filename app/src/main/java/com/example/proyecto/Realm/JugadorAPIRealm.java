package com.example.proyecto.Realm;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class JugadorAPIRealm extends RealmObject {

    @PrimaryKey
    String id = ObjectId.get().toHexString();

    @Required
    private String firstname;

    @Required
    private String lastname;

    private String position;

    public JugadorAPIRealm() {
    }

    public JugadorAPIRealm(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public JugadorAPIRealm(String firstname, String lastname, String position) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }



}
