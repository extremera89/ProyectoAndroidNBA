package com.example.proyecto.Interfaces;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.example.proyecto.com.prueba.gson.Player;


public interface JsonPlaceHolder {

    @GET("api/v1/players")
    Call<Player> getPosts(@Query("search") String name);



}
