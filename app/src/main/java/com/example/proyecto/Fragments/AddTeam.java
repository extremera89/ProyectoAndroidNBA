package com.example.proyecto.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyecto.Equipo.Equipo;
import com.example.proyecto.Equipo.EquipoSingleton;
import com.example.proyecto.Equipo.Jugador;
import com.example.proyecto.PantallaPrincipal;
import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;
import com.example.proyecto.Realm.JugadorRealm;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


public class AddTeam extends Fragment {

    View rootView;
    PantallaPrincipal context;
    private EditText nombreEquipo;
    private EditText yearEquipo;

    private AutoCompleteTextView base;
    private AutoCompleteTextView escolta;
    private AutoCompleteTextView alero;
    private AutoCompleteTextView alapivot;
    private AutoCompleteTextView pivot;

    private Bitmap image;
    String imagenString;
    String sinImagen;
    ImageView img;
    Button botonFoto;

    ArrayList<JugadorRealm> basesAll = new ArrayList<>();
    ArrayList<JugadorRealm> escoltasAll = new ArrayList<>();
    ArrayList<JugadorRealm> alerosAll = new ArrayList<>();
    ArrayList<JugadorRealm> alapivotsAll = new ArrayList<>();
    ArrayList<JugadorRealm> pivotsAll = new ArrayList<>();

    ArrayAdapter<String> adapterarraybases;
    ArrayAdapter<String> adapterarrayescoltas;
    ArrayAdapter<String> adapterarrayaleros;
    ArrayAdapter<String> adapterarrayalapivots;
    ArrayAdapter<String> adapterarraypivots;

    Realm realm = Realm.getDefaultInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView =  inflater.inflate(R.layout.fragment_addteam, container, false);

        getBases();
        getEscoltas();
        getAleros();
        getAlapivots();
        getPivots();

        ArrayList<String> nombrebases =new ArrayList<>();
        ArrayList<String> nombreescoltas =new ArrayList<>();
        ArrayList<String> nombrealeros =new ArrayList<>();
        ArrayList<String> nombrealapivots =new ArrayList<>();
        ArrayList<String> nombrepivots =new ArrayList<>();

        for(JugadorRealm ola : basesAll){
            nombrebases.add(ola.getName());
        }
        for(JugadorRealm ola : escoltasAll){
            nombreescoltas.add(ola.getName());
        }
        for(JugadorRealm ola : alerosAll){
            nombrealeros.add(ola.getName());
        }
        for(JugadorRealm ola : alapivotsAll){
            nombrealapivots.add(ola.getName());
        }
        for(JugadorRealm ola : pivotsAll){
            nombrepivots.add(ola.getName());
        }

        adapterarraybases = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line, nombrebases);
        adapterarrayescoltas = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line, nombreescoltas);
        adapterarrayaleros = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line, nombrealeros);
        adapterarrayalapivots = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line, nombrealapivots);
        adapterarraypivots = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line, nombrepivots);



        nombreEquipo = (EditText) rootView.findViewById(R.id.editNameEquipo);
        yearEquipo = (EditText) rootView.findViewById(R.id.editAnyo);

        base =  (AutoCompleteTextView) rootView.findViewById(R.id.editBase);
        escolta =  (AutoCompleteTextView) rootView.findViewById(R.id.editEscolta);
        alero =  (AutoCompleteTextView) rootView.findViewById(R.id.editAlero);
        alapivot =  (AutoCompleteTextView) rootView.findViewById(R.id.editAlapivot);
        pivot =  (AutoCompleteTextView) rootView.findViewById(R.id.editPivot);


        base.setAdapter(adapterarraybases);
        escolta.setAdapter(adapterarrayescoltas);
        alero.setAdapter(adapterarrayaleros);
        alapivot.setAdapter(adapterarrayalapivots);
        pivot.setAdapter(adapterarraypivots);




        img = (ImageView) rootView.findViewById(R.id.imagenFoto);
        botonFoto = (Button) rootView.findViewById(R.id.botonSeleccionarFoto);

        botonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 100);
            }
        });

        Button botonGuardar =  (Button) rootView.findViewById((R.id.btnAceptarEquipo));
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imagen = stream.toByteArray();
                sinImagen = Base64.encodeToString(imagen, Base64.DEFAULT);

                System.out.println("LA IMAGEN ES => "+sinImagen);
                if(img!=null){
                    saveItem(v);
                }else{
                    Toast.makeText(getContext(), "TIENES QUE SELECCIONAR UNA FOTO", Toast.LENGTH_SHORT).show();

                }

            }
        });

        return rootView;
    }

    public void getBases(){
        List<JugadorRealm> bases = realm.where(JugadorRealm.class).findAll();
        int contador = 0;
        for(JugadorRealm r : bases){

            contador++;
            if(r.getPosicion().equals("Base")){
                basesAll.add(r);

            }
        }
    }

    public void getEscoltas(){
        List<JugadorRealm> sg = realm.where(JugadorRealm.class).findAll();
        int contador = 0;
        for(JugadorRealm r : sg){

            contador++;
            if(r.getPosicion().equals("Escolta")){
                escoltasAll.add(r);

            }
        }
    }

    public void getAleros(){
        List<JugadorRealm> sf = realm.where(JugadorRealm.class).findAll();
        int contador = 0;
        for(JugadorRealm r : sf){

            contador++;
            if(r.getPosicion().equals("Alero")){
                alerosAll.add(r);

            }
        }
    }

    public void getAlapivots(){
        List<JugadorRealm> pf = realm.where(JugadorRealm.class).findAll();
        int contador = 0;
        for(JugadorRealm r : pf){

            contador++;
            if(r.getPosicion().equals("Alapivot")){
                alapivotsAll.add(r);

            }
        }
    }

    public void getPivots(){
        List<JugadorRealm> c = realm.where(JugadorRealm.class).findAll();
        int contador = 0;
        for(JugadorRealm r : c){

            contador++;
            if(r.getPosicion().equals("Pivot")){
                pivotsAll.add(r);

            }
        }
    }

    public void insertar(EquipoRealm equipo){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.insert(equipo);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && null!=data){
            Uri uri = data.getData();
            try {
                image = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), uri);
                img.setImageBitmap(image);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imagen = stream.toByteArray();
                imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);
                System.out.println("LA IMAGEN DE SELECCIONAR FOTO ES: "+imagenString);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }






    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (PantallaPrincipal) context;
        super.onAttach(context);
    }

    public void saveItem (View view) {
        String nameteam = nombreEquipo.getText().toString();
        String year = yearEquipo.getText().toString();
        String guard = base.getText().toString();
        String shootingguard = escolta.getText().toString();
        String smallforward = alero.getText().toString();
        String powerforward = alapivot.getText().toString();
        String center = pivot.getText().toString();

        if (nameteam.equals("")) {
            nombreEquipo.setError("Error. El campo está vacío.");
            return;
        }

        if (year.equals("")) {
            yearEquipo.setError("Error. El campo está vacío.");
            return;
        }

        if (guard.equals("")) {
            base.setError("Error. El campo está vacío.");
            return;
        }

        if (shootingguard.equals("")) {
            escolta.setError("Error. El campo está vacío.");
            return;
        }

        if (smallforward.equals("")) {
            alero.setError("Error. El campo está vacío.");
            return;
        }

        if (powerforward.equals("")) {
            alapivot.setError("Error. El campo está vacío.");
            return;
        }

        if (center.equals("")) {
            pivot.setError("Error. El campo está vacío.");
            return;
        }

        try {
            Equipo team = new Equipo(nameteam, year, image);
            /*Jugador jugador1 = new Jugador(guard);
            Jugador jugador2 = new Jugador(shootingguard);
            Jugador jugador3 = new Jugador(smallforward);
            Jugador jugador4 = new Jugador(powerforward);
            Jugador jugador5 = new Jugador(center);


            team.getJugadores().add(jugador1);
            team.getJugadores().add(jugador2);
            team.getJugadores().add(jugador3);
            team.getJugadores().add(jugador4);
            team.getJugadores().add(jugador5);*/


            EquipoSingleton itemList = EquipoSingleton.getInstance();
            /*itemList.getItemList().addItem(team);
            itemList.getItemList().saveItems(context);*/


            /*ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
            byte[] b = byteArrayBitmapStream.toByteArray();
            imagenrealm = Base64.encodeToString(b, Base64.DEFAULT);*/
            EquipoRealm ekipo;

            if(imagenString==null){
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imagen = stream.toByteArray();
                sinImagen = Base64.encodeToString(imagen, Base64.DEFAULT);
                ekipo = new EquipoRealm(nameteam, Integer.parseInt(year), sinImagen);
            }else{
                ekipo = new EquipoRealm(nameteam, Integer.parseInt(year), imagenString);

            }
            /*JugadorRealm jugador1 = estoesprueba(guard);
            JugadorRealm jugador2 = estoesprueba(shootingguard);
            JugadorRealm jugador3 = estoesprueba(smallforward);
            JugadorRealm jugador4 = estoesprueba(powerforward);
            JugadorRealm jugador5 = estoesprueba(center);*/
            ekipo.getJugadores().add(estoesprueba(guard));
            ekipo.getJugadores().add(estoesprueba(shootingguard));
            ekipo.getJugadores().add(estoesprueba(smallforward));
            ekipo.getJugadores().add(estoesprueba(powerforward));
            ekipo.getJugadores().add(estoesprueba(center));

            insertar(ekipo);

            ResetFragmentFields();

            replaceFragment(new ListaEquiposFragment());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JugadorRealm estoesprueba(String nombre){
        List<JugadorRealm> jugaore = realm.where(JugadorRealm.class).findAll();
        for(JugadorRealm r : jugaore){
            if(r.getName().equals(nombre)){
                System.out.println("ENTRÓOOOOO ");
                return r;
            }

        }
        return null;
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = context.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout, fragment);
        ft.commit();
    }

    private void ResetFragmentFields() {
        nombreEquipo.setText("");
        yearEquipo.setText("");
        base.setText("");
        escolta.setText("");
        alero.setText("");
        alapivot.setText("");
        pivot.setText("");
        img.setImageResource(android.R.drawable.ic_menu_report_image);
    }

}