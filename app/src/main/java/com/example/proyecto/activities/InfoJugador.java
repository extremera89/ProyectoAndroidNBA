package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.Interfaces.JsonPlaceHolder;
import com.example.proyecto.R;
import com.example.proyecto.com.prueba.gson.Datum;
import com.example.proyecto.com.prueba.gson.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoJugador extends AppCompatActivity {

    private TextView prueba;
    static PantallaPrincipal context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jugador);
        //prueba = findViewById(R.id.prueba);
        getPosts();
        System.out.println("POSICION RECOGIDA EN INFOJUGADOR => "+getIntent().getIntExtra("posicion", 0));



        Button botonBack = (Button) findViewById(R.id.backBoton);
        botonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(InfoJugador.this, PantallaPrincipal.class);
                intent.putExtra("pos", getIntent().getStringExtra("pos"));
                PantallaPrincipal.contador++;
                startActivity(intent);

            }
        });

    }



    private void getPosts(){
        String nombrejugador = getIntent().getStringExtra("nombre");
        System.out.println(nombrejugador);

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
                    prueba.setText("SE JODIÓ");
                    return;
                }



                Player postList = response.body();
                List<Datum> listajugadores = postList.getData();


                for(Datum p : listajugadores){
                    String posible = p.getFirst_name()+" "+p.getLast_name();
                    if(posible.equals(nombrejugador)) {
                        String content = "";
                        content += "Equipo: " + p.getTeam().getFull_name() + "\n";
                        content += "Nombre: " + p.getFirst_name() + "\n";
                        content += "Apellido: " + p.getLast_name() + "\n";
                        String posicion ="";

                        switch(p.getPosition()){
                            case "G":
                                posicion = "Guard";
                                break;
                            case "F":
                                posicion = "Forward";
                                break;
                            case "C":
                                posicion = "Center";
                                break;
                            default:
                                posicion = "Sin posición disponible";
                        }
                        content += "Posición: " + posicion +"\n";
                        prueba.append(content);
                    }
                }


            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                prueba.setText(t.getMessage());
            }
        });

    }

}