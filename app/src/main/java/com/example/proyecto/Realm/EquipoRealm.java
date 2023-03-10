package com.example.proyecto.Realm;

import org.bson.types.ObjectId;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;




public class EquipoRealm extends RealmObject {

    @PrimaryKey
    String id = ObjectId.get().toHexString();

    @Required
    String name;

    int year;

    RealmList<JugadorRealm> jugadores;

    String image;

    UsuarioRealm usuario;


    public EquipoRealm() {
    }

    public EquipoRealm(String name, int year, ArrayList<JugadorRealm> jugadores, String image) {
        this.name = name;
        this.year = year;
        jugadores = new ArrayList<>();
        this.image = image;
    }

    public EquipoRealm(String name, int year, String image) {
        this.name = name;
        this.year = year;
        this.image = image;
        this.jugadores = new RealmList<>();


    }

    public EquipoRealm(String name, int year,  String image, UsuarioRealm usuario) {
        this.name = name;
        this.year = year;
        jugadores = new RealmList<>();
        this.image = image;
        this.usuario = usuario;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void setImage(String image) {
        this.image = image;
    }

    /*public void addImage(Bitmap new_image){
        if (new_image != null) {
            imagen = new_image;
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            new_image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
            byte[] b = byteArrayBitmapStream.toByteArray();
            image = Base64.encodeToString(b, Base64.DEFAULT);
        }
    }*/

    /*public Bitmap getImage(){
        if (imagen == null && image != null) {
            byte[] decodeString = Base64.decode(image, Base64.DEFAULT);
            imagen = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        }
        return imagen;
    }*/

    public RealmList<JugadorRealm> getJugadores() {
        return jugadores;
    }

    public void setJugadores(RealmList<JugadorRealm> jugadores) {
        this.jugadores = jugadores;
    }

    public String getImage(){
        return this.image;
    }

    public UsuarioRealm getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRealm usuario) {
        this.usuario = usuario;
    }
}

