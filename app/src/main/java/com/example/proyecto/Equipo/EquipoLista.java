package com.example.proyecto.Equipo;

import android.content.Context;

import com.example.proyecto.Equipo.Equipo;
import com.example.proyecto.Realm.EquipoRealm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class EquipoLista {
    private static ArrayList<Equipo> equipos;
    private static ArrayList<EquipoRealm> equipos2;

    Realm realm = Realm.getDefaultInstance();
    private String FILENAME = "items.sav";
    /*public EquipoLista() {
        equipos = new ArrayList<Equipo>();
    }*/
    public EquipoLista() {
        equipos2 = new ArrayList<EquipoRealm>();
    }
    public void setItems(ArrayList<Equipo> item_list) {
        equipos = item_list;
    }
    public ArrayList<Equipo> getItems() {
        return equipos;
    }
    /*public void addItem(Equipo equipo) {
        equipos.add(equipo);
    }*/

    public void addItem(EquipoRealm equipo) {
        equipos2.add(equipo);
    }
    public void deleteItem(int index) {
        equipos.remove(index);
    }

    public void deleteItem2(int index) {
        equipos2.remove(index);
    }
    public Equipo getItem(int index) {
        return equipos.get(index);
    }

    public EquipoRealm getItem2(int index) {
        return equipos2.get(index);
    }
    public void editItem(Equipo equipo, int index) { equipos.set(index, equipo); }
    public void editItem2(EquipoRealm equipo, int index) { equipos2.set(index, equipo); }

    public int getIndex(Equipo equipo) {
        int pos = 0;
        for (Equipo i : equipos) {
            if (equipo.getName().equals(i.getName())) {
                return pos;
            }

            pos++;
        }

        return -1;
    }

    public int getIndex2(EquipoRealm equipo) {
        int pos = 0;
        for (EquipoRealm i : equipos2) {
            if (equipo.getName().equals(i.getName())) {
                return pos;
            }

            pos++;
        }

        return -1;
    }

    public int getSize() {
        return equipos2.size();
    }

    public void loadItems(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Equipo>>() {}.getType();
            equipos = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            equipos = new ArrayList<Equipo>();
        } catch (IOException e) {
            equipos = new ArrayList<Equipo>();
        }
    }

    public void loadEquipos(Context context){
        equipos2.clear();


        List<EquipoRealm> ekipos = realm.where(EquipoRealm.class).findAll();
        for(EquipoRealm r : ekipos){
            equipos2.add(r);
        }


    }

    public void saveItems(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(equipos, osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
