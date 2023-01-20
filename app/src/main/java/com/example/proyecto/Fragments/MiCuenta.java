package com.example.proyecto.Fragments;

import static io.realm.Realm.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyecto.MainActivity;
import com.example.proyecto.PantallaPrincipal;
import com.example.proyecto.R;
import com.example.proyecto.Realm.UsuarioRealm;

import org.w3c.dom.Text;

public class MiCuenta extends Fragment {
    View rootView;
    PantallaPrincipal context;
    TextView nombre;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_mi_cuenta, container, false);

        rootView.findViewById(R.id.idCerrarSesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(context, MainActivity.class);
                logout();
                startActivity(intent);
            }



        });

        //UsuarioRealm user = context.getDatosUsuario();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        //nick.setText(preferences.getString("nickname",""));
        //pass.setText(preferences.getString("password",""));

        nombre = (TextView) rootView.findViewById(R.id.idnameusuario2);
        nombre.setText(nombre.getText().toString()+" "+preferences.getString("nickname",""));

        return rootView;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (PantallaPrincipal) context;
        super.onAttach(context);
    }
    public void logout() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        System.out.println("SE HA CERRADO LA SESIÓN");
        context.finish();
    }

}