package com.example.proyecto.Fragments;

import static io.realm.Realm.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;
import com.example.proyecto.activities.MainActivity;
import com.example.proyecto.activities.PantallaPrincipal;
import com.example.proyecto.miperfil.MiPerfilAdapter;
import com.example.proyecto.miperfil.MiPerfilSingleton;

public class MiCuenta extends Fragment {
    View rootView;
    PantallaPrincipal context;
    TextView nombre;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_mi_cuenta, container, false);
        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.recyclermicuenta);
        MiPerfilSingleton.getItemList().loadEquipos();

        rootView.findViewById(R.id.idCerrarSesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(context, MainActivity.class);
                logout();
                startActivity(intent);
                //context.finish();
            }



        });


        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);

        nombre = (TextView) rootView.findViewById(R.id.idnameusuario2);
        nombre.setText(nombre.getText().toString()+" "+preferences.getString("nickname",""));


        MiPerfilAdapter.OnItemClickListener onItemClickListener = new MiPerfilAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EquipoRealm item) {
                DialogFragment editItemFragment = new EditTeam(item,  MiPerfilSingleton.getItemList().getIndex(item));
                FragmentManager fm = context.getSupportFragmentManager();
                editItemFragment.show(fm, "EditDialogFragment");
            }


        };

        recycler.setAdapter(new MiPerfilAdapter(onItemClickListener));

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