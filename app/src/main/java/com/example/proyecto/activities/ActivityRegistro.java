package com.example.proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.example.proyecto.Realm.OperacionesUsuario;

import java.util.regex.Pattern;


public class ActivityRegistro extends AppCompatActivity {

    SharedPreferences.Editor editor ;
    EditText nick;
    EditText pass;
    EditText email;
    EditText pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().hide();

        nick = (EditText) findViewById(R.id.nombreUsuarioRegistro);

        email = (EditText) findViewById(R.id.email);

        pass = (EditText) findViewById(R.id.passwordRegistro);

        pass2 = (EditText) findViewById(R.id.passwordRegistro2);



        Button registrar = (Button) findViewById(R.id.btnRegistro2);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nick.getText().length()<4){
                    nick.setError("El nick debe contener 4 letras mínimo");
                }
                else if(email.getText().length()<1){
                    email.setError("El email no puede estar vacío");
                    if(!validarEmail(email.getText().toString())){
                        email.setError("El email no es correcto");
                    }
                }
                else if(pass.getText().length()<8){
                    pass.setError("La contraseña debe contener mínimo 8 caracteres");
                }
                else if(pass2.getText().length()<8){
                    pass2.setError("La contraseña  debe contener mínimo 8 caracteres");
                    if(!pass2.getText().toString().equals(pass.getText().toString())){
                        pass2.setError("Las contraseñas no coinciden!");
                    }
                }else{
                    OperacionesUsuario.insertarUsuarioNuevo(nick.getText().toString(), email.getText().toString(), pass.getText().toString());
                    Intent intent = new Intent(ActivityRegistro.this, PantallaPrincipal.class);
                    saveUserPreferences();
                    startActivity(intent);
                }



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


    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
