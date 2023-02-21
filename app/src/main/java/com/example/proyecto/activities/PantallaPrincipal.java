package com.example.proyecto.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.example.proyecto.Fragments.BuscarJugador;
import com.example.proyecto.R;
import com.example.proyecto.Fragments.AddJugador;
import com.example.proyecto.Fragments.AddTeam;
import com.example.proyecto.Fragments.ListaEquiposFragment;
import com.example.proyecto.Fragments.MiCuenta;
import com.example.proyecto.Fragments.Settings;
import com.example.proyecto.Realm.UsuarioRealm;
import com.google.android.material.navigation.NavigationView;


public class PantallaPrincipal extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    public static int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );




        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new ListaEquiposFragment()).commit();


        if(contador > 0){
            System.out.println("POSICIOOOOOOOOOONN SUMBA => "+getIntent().getIntExtra("pos", 0));
            //ListaEquiposFragment.probando(getIntent().getIntExtra("pos", 0));
        }
        System.out.println("contadolllllllllllllllllllll "+contador);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        System.out.println("NICKNAME => "+pref.getString("nickname",""));
        System.out.println("EMAIL => "+pref.getString("password",""));


        /*drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        navigationView = findViewById(R.id.navigation);*/

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.navigation);




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;


                switch(item.getItemId()){
                    case R.id.addItemFragment:
                        selectedFragment = new AddTeam();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.userFragment:
                        selectedFragment = new MiCuenta();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.itemsListFragment:
                        selectedFragment = new ListaEquiposFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.settingsFragment:
                        selectedFragment = new Settings();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addJugadorFragment:
                        selectedFragment = new AddJugador();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.buscarJugadorFragment:
                        selectedFragment = new BuscarJugador();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
                return true;
            }
        });


        navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.teal_200)));


        /*BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new ListaEquiposFragment()).commit();*/

    }


    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return false;
    }

    public UsuarioRealm getDatosUsuario(){
        String nick="";
        String pass="";
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            nick = parametros.getString("nickname");
            pass = parametros.getString("password");

        }

        System.out.println("EL USUARIO ES: "+nick+" //// Y LA CONTRASEÑA ES: "+pass);

        return new UsuarioRealm(nick, pass);
    }


    /* private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;


            switch(item.getItemId()){
                case R.id.addItemFragment:
                    selectedFragment = new AddItems();
                    break;
                case R.id.userFragment:
                    selectedFragment = new MiCuenta();
                    break;
                case R.id.itemsListFragment:
                    selectedFragment = new ListaEquiposFragment();
                    break;
                case R.id.settingsFragment:
                    selectedFragment = new Settings();
                    break;
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
            return true;

        }

    };*/

}