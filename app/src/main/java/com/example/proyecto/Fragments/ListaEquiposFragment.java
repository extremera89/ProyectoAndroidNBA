package com.example.proyecto.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.proyecto.Equipo.Equipo;
import com.example.proyecto.Equipo.EquipoAdapter;
import com.example.proyecto.Equipo.EquipoSingleton;
import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;
import com.example.proyecto.activities.PantallaPrincipal;

import io.realm.Realm;


public class ListaEquiposFragment extends Fragment implements SearchView.OnQueryTextListener {

    View rootView;
    static PantallaPrincipal context;
    SearchView txtBuscar;
    EquipoAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (PantallaPrincipal) context;
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public static ListaEquiposFragment newInstance() {
        ListaEquiposFragment itemsListFragment = new ListaEquiposFragment();
        return itemsListFragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //EquipoSingleton.getItemList().loadItems(context);
        EquipoSingleton.getItemList().loadEquipos(context);
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.home);
        txtBuscar = rootView.findViewById(R.id.buscadorHome);



        EquipoAdapter.OnItemClickListener onItemClickListener = new EquipoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EquipoRealm item) {
                DialogFragment editItemFragment = new EditTeam(item,  EquipoSingleton.getItemList().getIndex2(item));
                //System.out.println("POSICIOOOOOON -> "+EquipoSingleton.getItemList().getIndex(item));
                FragmentManager fm = context.getSupportFragmentManager();
                editItemFragment.show(fm, "EditDialogFragment");
            }



        };
        adapter = new EquipoAdapter(onItemClickListener);

        recycler.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);

        return rootView;
    }

    public static void eliminar2(EquipoRealm equipo){
        EquipoSingleton itemList = EquipoSingleton.getInstance();
        int itemIndex = itemList.getItemList().getIndex2(equipo);
        itemList.getItemList().deleteItem2(itemIndex);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        equipo.deleteFromRealm();
        realm.commitTransaction();
        //itemList.getItemList().saveItems(context);
        FragmentManager fm = context.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout, new ListaEquiposFragment());
        ft.commit();

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }


}