package com.example.proyecto.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto.Interfaces.JsonPlaceHolder;
import com.example.proyecto.R;
import com.example.proyecto.Realm.JugadorRealm;
import com.example.proyecto.activities.PantallaPrincipal;
import com.example.proyecto.com.prueba.gson.Datum;
import com.example.proyecto.com.prueba.gson.Player;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddJugador extends Fragment {
    View rootView;
    PantallaPrincipal context;
    private EditText editName;
    private EditText editPosicion;
    private EditText editEdad;
    private EditText editDorsal;



    public AddJugador() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_addjugador, container, false);
        editName = (EditText) rootView.findViewById(R.id.editNameJugador);
        editPosicion = (EditText) rootView.findViewById(R.id.editPosJugador);
        editEdad = (EditText) rootView.findViewById(R.id.editescolta2);
        editDorsal = (EditText)  rootView.findViewById(R.id.editbase2);

        Button aceptar = (Button) rootView.findViewById(R.id.btnAceptarJugador);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveJugador(view);

            }
        });



        return rootView;

    }

    public void insertarJugador(JugadorRealm jugador){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.insert(jugador);
            }
        });

    }


    public void saveJugador(View view){
        String namejugador = editName.getText().toString();
        String posicionJugador = editPosicion.getText().toString();
        String edadjugador = editEdad.getText().toString();
        String dorsaljugador = editDorsal.getText().toString();


        try{
            JugadorRealm jugador = new JugadorRealm();
            jugador.setName(namejugador);
            jugador.setPosicion(posicionJugador);
            jugador.setDorsal(Integer.parseInt(dorsaljugador));
            jugador.setEdad(Integer.parseInt(edadjugador));

            insertarJugador(jugador);

            resetearCampos();

        }catch(Exception e){
            e.printStackTrace();
        }



    }

    public void resetearCampos(){
        editDorsal.setText("");
        editName.setText("");
        editPosicion.setText("");
        editEdad.setText("");
    }



}