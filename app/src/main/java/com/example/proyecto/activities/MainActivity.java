package com.example.proyecto.activities;

import static io.realm.Realm.getApplicationContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.Realm.OperacionesUsuario;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    EditText nick;
    EditText pass;
    SharedPreferences.Editor editor ;
    static boolean iniciado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        if(!iniciado){
            Realm.init(this);
            String realm = "EquipoProject";
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name(realm)
                    .allowQueriesOnUiThread(true)
                    .allowWritesOnUiThread(true)
                    .compactOnLaunch()
                    .build();

            Realm.setDefaultConfiguration(config);
            iniciado = true;
        }


        nick = (EditText) findViewById(R.id.nombreUsuario);

        pass = (EditText) findViewById(R.id.password);

        //loadUserPreferences();


        Button botonEnviar = (Button) findViewById(R.id.btnEnviar);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserPreferences();


            }
        });

        Button botonRegistro = (Button) findViewById(R.id.btnRegistro);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaregistro = new Intent(MainActivity.this, ActivityRegistro.class);
                startActivity(pantallaregistro);
            }
        });

    }

    private void saveUserPreferences(){
        SharedPreferences preferences = getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("nickname", nick.getText().toString());
        editor.putString("password", pass.getText().toString());
        String nick2 = preferences.getString("nickname","");
        String pass2 = preferences.getString("password", "");


        editor.apply();
        if(!"".equals(nick.getText().toString()) && !"".equals(pass.getText().toString())){
            login();
        }

    }


    private void loadUserPreferences(){
        SharedPreferences preferences = getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        nick.setText(preferences.getString("nickname",""));
        pass.setText(preferences.getString("password",""));
        if(!"".equals(nick.getText().toString()) && !"".equals(pass.getText().toString())){
            login();
        }
    }

    public void logout(){
        SharedPreferences preferences = getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        finish();


    }

    public void login(){
        if(OperacionesUsuario.comprobarLogin(nick.getText().toString(), pass.getText().toString())) {
            Intent pantallaPrincipal = new Intent(MainActivity.this, PantallaPrincipal.class);
            Bundle bundle = new Bundle();
            bundle.putString("nickname", nick.getText().toString());
            bundle.putString("password", pass.getText().toString());
            pantallaPrincipal.putExtras(bundle);
            startActivity(pantallaPrincipal);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "USUARIO/CONTRASEÑA INCORRECTO", Toast.LENGTH_SHORT).show();
        }
    }









}