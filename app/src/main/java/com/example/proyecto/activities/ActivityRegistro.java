package com.example.proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.example.proyecto.Realm.OperacionesUsuario;


public class ActivityRegistro extends AppCompatActivity {

    SharedPreferences.Editor editor ;
    EditText nick;
    EditText pass;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().hide();

        nick = (EditText) findViewById(R.id.nombreUsuarioRegistro);

        email = (EditText) findViewById(R.id.email);

        pass = (EditText) findViewById(R.id.passwordRegistro);


        Button registrar = (Button) findViewById(R.id.btnRegistro2);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperacionesUsuario.insertarUsuarioNuevo(nick.getText().toString(), email.getText().toString(), pass.getText().toString());
                Intent intent = new Intent(ActivityRegistro.this, PantallaPrincipal.class);
                saveUserPreferences();
                startActivity(intent);

            }
        });

    }

    private void saveUserPreferences(){
        SharedPreferences preferences = getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("nickname", nick.getText().toString());
        editor.putString("password", pass.getText().toString());
        editor.apply();

    }
}
