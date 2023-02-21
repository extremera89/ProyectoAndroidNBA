package com.example.proyecto.Fragments;


import static io.realm.Realm.getApplicationContext;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.Equipo.Equipo;
import com.example.proyecto.Equipo.EquipoSingleton;
import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;
import com.example.proyecto.Realm.JugadorRealm;
import com.example.proyecto.Realm.UsuarioRealm;
import com.example.proyecto.activities.InfoJugador;
import com.example.proyecto.activities.PantallaPrincipal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import io.realm.Realm;

public class EditTeam extends DialogFragment {
    private Equipo team;
    private EquipoRealm ekipo;
    private EditText nombreequipo;
    private EditText anyoequipo;
    private EditText base;
    private EditText escolta;
    private EditText alero;
    private EditText alapivot;
    private EditText pivot;
    private Bitmap image;
    ImageView img;
    Button botonFoto;
    Bitmap imagenantigua;
    private int posicion;



    private ActivityResultLauncher<Intent> activityResultLauncher;
    private PantallaPrincipal context;
    View rootView;

    public EditTeam() {
    }

    public EditTeam(Equipo item, int pos) {
        this.team = item;
        this.posicion = pos;
    }

    public EditTeam(EquipoRealm item, int pos) {
        this.ekipo = item;
        this.posicion = pos;
    }


    public EditTeam(Equipo item) {
        this.team = item;
    }



    public static EditTeam newInstance() {
        EditTeam editItemFragment = new EditTeam();
        return editItemFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (PantallaPrincipal) context;
        super.onAttach(context);
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        rootView = inflater.inflate(R.layout.fragment_edit, null);


        byte[] encodeByte = Base64.decode(ekipo.getImage(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        imagenantigua = bitmap;


        //imagenantigua = ekipo.getImage();


        nombreequipo = (EditText) rootView.findViewById(R.id.editNombre);
        nombreequipo.setText(ekipo.getName());

        anyoequipo = (EditText) rootView.findViewById(R.id.editAnyo);
        anyoequipo.setText(String.valueOf(ekipo.getYear()));

        img = (ImageView) rootView.findViewById(R.id.imagenFoto);
        byte [] bye = Base64.decode(ekipo.getImage(),Base64.DEFAULT);
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(bye, 0, bye.length);
        img.setImageBitmap(bitmap2);

        /*nombreequipo = (EditText) rootView.findViewById(R.id.editNombre);
        nombreequipo.setText(team.getName());

        anyoequipo = (EditText) rootView.findViewById(R.id.editAnyo);
        anyoequipo.setText(team.getYear());*/



        base = (EditText) rootView.findViewById(R.id.editbase2);
        base.setText(ekipo.getJugadores().get(0).getName());


        escolta = (EditText) rootView.findViewById(R.id.editescolta2);
        escolta.setText(ekipo.getJugadores().get(1).getName());

        alero = (EditText) rootView.findViewById(R.id.editalero2);
        alero.setText(ekipo.getJugadores().get(2).getName());

        alapivot = (EditText) rootView.findViewById(R.id.editalapivot2);
        alapivot.setText(ekipo.getJugadores().get(3).getName());

        pivot = (EditText) rootView.findViewById(R.id.editpivot2);
        pivot.setText(ekipo.getJugadores().get(4).getName());

        Button saveButton = (Button) rootView.findViewById(R.id.save_button);

        botonFoto = (Button) rootView.findViewById(R.id.botonSeleccionarFoto);

        if(comprobarUsuario()){
            base.setEnabled(true);
            escolta.setEnabled(true);
            alero.setEnabled(true);
            alapivot.setEnabled(true);
            pivot.setEnabled(true);
            saveButton.setEnabled(true);
            botonFoto.setEnabled(true);
            nombreequipo.setEnabled(true);
            anyoequipo.setEnabled(true);

        }else{
            base.setEnabled(false);
            escolta.setEnabled(false);
            alero.setEnabled(false);
            alapivot.setEnabled(false);
            pivot.setEnabled(false);
            saveButton.setEnabled(false);
            botonFoto.setEnabled(false);
            nombreequipo.setEnabled(false);
            anyoequipo.setEnabled(false);

        }




        Button botonBase = rootView.findViewById(R.id.btnInfoBase);
        botonBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoJugador.class);
                intent.putExtra("nombre", base.getText().toString());
                intent.putExtra("posicion", posicion);
                startActivity(intent);
            }
        });

        Button botonEscolta = rootView.findViewById(R.id.btnInfoEscolta);
        botonEscolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoJugador.class);
                intent.putExtra("nombre", escolta.getText().toString());
                intent.putExtra("pos", posicion);
                startActivity(intent);
            }
        });



        Button botonAlero = rootView.findViewById(R.id.btnInfoAlero);
        botonAlero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoJugador.class);
                intent.putExtra("nombre", alero.getText().toString());
                intent.putExtra("pos",posicion);
                startActivity(intent);
            }
        });

        Button botonAlapivot = rootView.findViewById(R.id.btnInfoAlaPivot);
        botonAlapivot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoJugador.class);
                intent.putExtra("nombre", alapivot.getText().toString());
                intent.putExtra("pos",posicion);
                startActivity(intent);
            }
        });

        Button botonPivot = rootView.findViewById(R.id.btnInfoPivot);
        botonPivot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoJugador.class);
                intent.putExtra("nombre", pivot.getText().toString());
                startActivity(intent);
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItem(view);
            }
        });

        Button cancelButton = (Button) rootView.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelItem(view);
            }
        });

        img = (ImageView) rootView.findViewById(R.id.imagenFoto);

        botonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 100);
            }
        });



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(rootView);
        return alertDialogBuilder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && null!=data){
            Uri uri = data.getData();
            try {
                image = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), uri);
                img.setImageBitmap(image);
                //img.setImageURI(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    public void saveItem (View view) {
        String nombreequipo_str = nombreequipo.getText().toString();
        String year_str = anyoequipo.getText().toString();
        String base_str = base.getText().toString();
        String escolta_str = escolta.getText().toString();
        String alero_str = alero.getText().toString();
        String alapivot_str = alapivot.getText().toString();
        String pivot_str = pivot.getText().toString();


        if (nombreequipo_str.equals("")) {
            nombreequipo.setError("Error. El campo está vacío.");
            return;
        }

        if (year_str.equals("")) {
            anyoequipo.setError("Error. El campo está vacío.");
            return;
        }

        if (base_str.equals("")) {
            base.setError("Error. El campo está vacío.");
            return;
        }

        if (escolta_str.equals("")) {
            escolta.setError("Error. El campo está vacío.");
            return;
        }

        if (alero_str.equals("")) {
            alero.setError("Error. El campo está vacío.");
            return;
        }

        if (alapivot_str.equals("")) {
            alapivot.setError("Error. El campo está vacío.");
            return;
        }

        if (pivot_str.equals("")) {
            pivot.setError("Error. El campo está vacío.");
            return;
        }


        try {
            //Equipo equipo;
            EquipoRealm equipo;
            String imagenequipo;
            String imagenantiwa;






            if(image == null){
                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                imagenantigua.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                byte[] imagen2 = stream2.toByteArray();
                imagenantiwa = Base64.encodeToString(imagen2, Base64.DEFAULT);
                equipo = new EquipoRealm(nombreequipo_str, Integer.parseInt(year_str), imagenantiwa);

            }
            else{
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imagen = stream.toByteArray();
                imagenequipo = Base64.encodeToString(imagen, Base64.DEFAULT);
                equipo = new EquipoRealm(nombreequipo_str, Integer.parseInt(year_str), imagenequipo);

            }



            /*String idd = buscarEquipo(nombreequipo_str);

            equipo.setId(idd);
            System.out.println("LA IDDDDDDDDDDDDDDDDDD=>>>>>> " +equipo.getId());*/


            equipo.getJugadores().add(estoesprueba2(base_str));
            equipo.getJugadores().add(estoesprueba2(escolta_str));
            equipo.getJugadores().add(estoesprueba2(alero_str));
            equipo.getJugadores().add(estoesprueba2(alapivot_str));
            equipo.getJugadores().add(estoesprueba2(pivot_str));


            modificarEquipo(equipo);

            EquipoSingleton itemList = EquipoSingleton.getInstance();
            int itemIndex = itemList.getItemList().getIndex2(ekipo);
            //itemList.getItemList().editItem2(equipo, itemIndex);
            //itemList.getItemList().saveItems(context);

            RecyclerView recycler = (RecyclerView) context.findViewById(R.id.home);
            recycler.getAdapter().notifyItemChanged(itemIndex);
            ResetFragmentFields();
            dismiss();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JugadorRealm estoesprueba2(String nombre){
        Realm realm = Realm.getDefaultInstance();

        List<JugadorRealm> jugaore = realm.where(JugadorRealm.class).findAll();
        for(JugadorRealm r : jugaore){
            if(r.getName().equals(nombre)){
                System.out.println("ENTRÓOOOOO ");
                return r;
            }

        }
        return null;
    }

    private void ResetFragmentFields() {
        nombreequipo.setText("");
        anyoequipo.setText("");
        base.setText("");
        escolta.setText("");
        alero.setText("");
        alapivot.setText("");
        pivot.setText("");
        img.setImageResource(android.R.drawable.ic_menu_report_image);

    }

    public void modificarEquipo(EquipoRealm equipo){
        Realm realm = Realm.getDefaultInstance();

        String id = ekipo.getId();

        EquipoRealm equipoactualizar = realm.where(EquipoRealm.class).equalTo("id", id).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                equipoactualizar.setName(equipo.getName());
                equipoactualizar.setJugadores(equipo.getJugadores());
                equipoactualizar.setYear(equipo.getYear());
                equipoactualizar.setImage(equipo.getImage());

                realm.copyToRealmOrUpdate(equipoactualizar);

            }
        });

    }

    /*public String buscarEquipo(String name){
        List<EquipoRealm> ekipos = realm.where(EquipoRealm.class).findAll();
        for(EquipoRealm r : ekipos){
            if(r.getName().equals(name)){
                System.out.println("ENTRÓOOOOO " + r.getId());
                return r.getId();
            }

        }
        return null;
    }*/

    public boolean comprobarUsuario(){
        Realm realm = Realm.getDefaultInstance();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("userCredentials", Context.MODE_PRIVATE);

        String user = preferences.getString("nickname","");

        EquipoRealm equipo = realm.where(EquipoRealm.class).equalTo("name", nombreequipo.getText().toString()).findFirst();

        System.out.println("EQUIPO=> "+equipo.getUsuario());

        if(equipo.getUsuario().getNickname().equals(user)){
            return true;
        }else{
            return false;
        }

    }


    public void cancelItem(View view) {
        dismiss();
    }

}