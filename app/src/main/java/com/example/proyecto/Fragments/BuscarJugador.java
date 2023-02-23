package com.example.proyecto.Fragments;

import static io.realm.Realm.getApplicationContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.Interfaces.JsonPlaceHolder;
import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;
import com.example.proyecto.Realm.JugadorAPIRealm;
import com.example.proyecto.Realm.JugadorRealm;
import com.example.proyecto.Realm.OperacionesJugadoresAPI;
import com.example.proyecto.Realm.UsuarioRealm;
import com.example.proyecto.activities.PantallaPrincipal;
import com.example.proyecto.buscarJugador.BuscarJugadorAdapter;
import com.example.proyecto.buscarJugador.JugadorSingleton;
import com.example.proyecto.buscarJugador.JugadoresList;
import com.example.proyecto.buscarJugador.RecyclerViewSwipeDecorator;
import com.example.proyecto.com.prueba.gson.Datum;
import com.example.proyecto.com.prueba.gson.Player;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BuscarJugador extends Fragment{
    View rootView;
    PantallaPrincipal context;
    BuscarJugadorAdapter adapter;
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_buscar_jugador, container, false);
        recycler = (RecyclerView) rootView.findViewById(R.id.recyclerbuscadorjugadores);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBackMethod);
        itemTouchHelper.attachToRecyclerView(recycler);

        Button botonBuscar = rootView.findViewById(R.id.botonBuscarjugador);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarJugadores();

            }
        });
        adapter = new BuscarJugadorAdapter();

        recycler.setAdapter(adapter);

        return rootView;

    }

    ItemTouchHelper.SimpleCallback callBackMethod = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            JugadorAPIRealm jugaor = JugadorSingleton.getItemList().getItem(position);
            OperacionesJugadoresAPI.anyadirjugadorApi(jugaor);


            recycler.getAdapter().notifyItemChanged(position);

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftLabel("FAV")
                    .setSwipeLeftLabelColor(getResources().getColor(R.color.white))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_star_24)
                    .setSwipeLeftActionIconTint(getResources().getColor(R.color.white))
                    .addSwipeLeftBackgroundColor(getResources().getColor(R.color.gold))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

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
                    JugadorAPIRealm posible = new JugadorAPIRealm(p.getFirst_name(), p.getLast_name(), p.getPosition());

                    JugadorSingleton.getItemList().loadJugadores(posible);
                    recycler.getAdapter().notifyItemChanged(JugadorSingleton.getItemList().getIndex(posible));
                    recycler.getAdapter().notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }



}