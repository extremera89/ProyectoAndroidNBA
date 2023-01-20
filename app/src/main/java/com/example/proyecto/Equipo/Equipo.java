package com.example.proyecto.Equipo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Equipo {
    private String name;
    private String year;
    protected transient Bitmap image;
    ArrayList<Jugador> jugadores;
    protected String image_base64;



    public Equipo(String name, String year, Bitmap image) {
        this.name = name;
        this.year = year;
        this.jugadores = new ArrayList<>();
        addImage(image);
        //this.image = image;
    }

    public Equipo(String name, String year) {
        this.name = name;
        this.year = year;
        this.jugadores = new ArrayList<>();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public void setImage(Bitmap image) {
        this.image = image;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void addImage(Bitmap new_image){
        if (new_image != null) {
            image = new_image;
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            new_image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
            byte[] b = byteArrayBitmapStream.toByteArray();
            image_base64 = Base64.encodeToString(b, Base64.DEFAULT);
        }
    }

    public Bitmap getImage(){
        if (image == null && image_base64 != null) {
            byte[] decodeString = Base64.decode(image_base64, Base64.DEFAULT);
            image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        }
        return image;
    }



    @Override
    public String toString() {
        return "Equipo{" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
