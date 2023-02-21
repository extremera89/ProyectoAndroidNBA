package com.example.proyecto.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyecto.Interfaces.JsonPlaceHolder;
import com.example.proyecto.R;
import com.example.proyecto.activities.PantallaPrincipal;
import com.example.proyecto.buscarJugador.BuscarJugadorAdapter;
import com.example.proyecto.buscarJugador.JugadorSingleton;
import com.example.proyecto.buscarJugador.JugadoresList;
import com.example.proyecto.com.prueba.gson.Datum;
import com.example.proyecto.com.prueba.gson.Player;
import com.example.proyecto.miperfil.MiPerfilAdapter;
import com.example.proyecto.miperfil.MisEquiposList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BuscarJugador extends Fragment {
    View rootView;
    PantallaPrincipal context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_buscar_jugador, container, false);
        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.recyclerbuscadorjugadores);

        Button botonBuscar = rootView.findViewById(R.id.botonBuscarjugador);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarJugadores();

            }
        });
        recycler.setAdapter(new BuscarJugadorAdapter());

        return rootView;

    }


    private void buscarJugadores(){
        JugadorSingleton.getItemList().getJugadores().clear();
        System.out.println("TAMAÑO DE ARRAYLIST: "+JugadorSingleton.getItemList().getSize());
        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.recyclerbuscadorjugadores);
        recycler.getAdapter().notifyDataSetChanged();

        EditText nombre = rootView.findViewById(R.id.jugadorBuscado);
        String nombrejugador = nombre.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.balldontlie.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolderApi = retrofit.create(JsonPlaceHolder.class);

        Call<Player> call = jsonPlaceHolderApi.getPosts(nombrejugador);

        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {

                if(!response.isSuccessful()){
                    System.out.println("NO FUNCIONA");
                    return;
                }



                Player postList = response.body();
                List<Datum> listajugadores = postList.getData();


                for(Datum p : listajugadores){
                    String posible = p.getFirst_name()+" "+p.getLast_name()+" - "+ p.getPosition();
                    JugadorSingleton.getItemList().loadJugadores(posible);
                    recycler.getAdapter().notifyItemChanged(JugadorSingleton.getItemList().getIndex(posible));
                    recycler.getAdapter().notifyDataSetChanged();

                }


            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                //prueba.setText(t.getMessage());
            }
        });




    }
}