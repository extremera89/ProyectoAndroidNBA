package com.example.proyecto.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.MainActivity;
import com.example.proyecto.PantallaPrincipal;
import com.example.proyecto.R;


public class Settings extends Fragment {

    View rootView;
    PantallaPrincipal context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_settings, container, false);


        return rootView;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (PantallaPrincipal) context;
        super.onAttach(context);
    }
}