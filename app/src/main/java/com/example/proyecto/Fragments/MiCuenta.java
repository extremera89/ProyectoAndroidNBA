package com.example.proyecto.Fragments;

import static io.realm.Realm.getApplicationContext;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;
import com.example.proyecto.Realm.UsuarioRealm;
import com.example.proyecto.activities.MainActivity;
import com.example.proyecto.activities.PantallaPrincipal;
import com.example.proyecto.miperfil.MiPerfilAdapter;
import com.example.proyecto.miperfil.MiPerfilSingleton;

import org.w3c.dom.Text;

import io.realm.Realm;

public class MiCuenta extends Fragment {
    View rootView;
    PantallaPrincipal context;
    EditText nombre;
    EditText password;


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
            }



        });


        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);

        nombre = (EditText) rootView.findViewById(R.id.idnameusuario2);
        nombre.setText(preferences.getString("nickname",""));
        String email = getEmail();

        EditText correo = (EditText) rootView.findViewById(R.id.idemail2);
        correo.setText(email);
        password = (EditText) rootView.findViewById(R.id.idpassword);
        password.setText(preferences.getString("password", ""));

        TextView actualizadatos  =(TextView) rootView.findViewById(R.id.actualizardatos);
        ImageButton botonactualizardatos = (ImageButton) rootView.findViewById(R.id.idActualizardatos);
        nombre.setEnabled(false);
        correo.setEnabled(false);
        password.setEnabled(false);

        botonactualizardatos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(actualizadatos.getText().toString().equals("Actualizar datos")){
                    actualizadatos.setText("Confirmar cambios");
                    nombre.setEnabled(true);
                    correo.setEnabled(true);
                    password.setEnabled(true);

                }else if(actualizadatos.getText().toString().equals("Confirmar cambios")){
                    actualizadatos.setText("Actualizar datos");
                    nombre.setEnabled(false);
                    correo.setEnabled(false);
                    password.setEnabled(false);
                    Realm realm = Realm.getDefaultInstance();

                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    String user = preferences.getString("nickname","");

                    UsuarioRealm usuario = realm.where(UsuarioRealm.class).equalTo("nickname", user).findFirst();

                    editor.putString("nickname", nombre.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.apply();

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            usuario.setNickname(nombre.getText().toString());
                            usuario.setEmail(correo.getText().toString());
                            usuario.setPassword(password.getText().toString());
                            realm.copyToRealmOrUpdate(usuario);
                        }
                    });


                }
            }
        });



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

    public String getEmail(){
        Realm realm = Realm.getDefaultInstance();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);

        String user = preferences.getString("nickname","");
        System.out.println("shared preferences ACTUALISAO "+ user);
        UsuarioRealm usuario = realm.where(UsuarioRealm.class).equalTo("nickname", user).findFirst();
        System.out.println("USUARIO ACTUALISAO "+usuario.getNickname());

        if(usuario.getNickname().equals(user)){
            return usuario.getEmail();
        }else{
            return "no";
        }
    }

}