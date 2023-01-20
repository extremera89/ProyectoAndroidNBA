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
import android.widget.Button;
import android.widget.Toast;

import com.example.proyecto.Equipo.Equipo;
import com.example.proyecto.Equipo.EquipoAdapter;
import com.example.proyecto.Equipo.EquipoSingleton;
import com.example.proyecto.MainActivity;
import com.example.proyecto.PantallaPrincipal;
import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;

import java.util.List;

import io.realm.Realm;


public class ListaEquiposFragment extends Fragment {

    View rootView;
    static PantallaPrincipal context;

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



        EquipoAdapter.OnItemClickListener onItemClickListener = new EquipoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EquipoRealm item) {
                DialogFragment editItemFragment = new EditTeam(item,  EquipoSingleton.getItemList().getIndex2(item));
                //System.out.println("POSICIOOOOOON -> "+EquipoSingleton.getItemList().getIndex(item));
                FragmentManager fm = context.getSupportFragmentManager();
                editItemFragment.show(fm, "EditDialogFragment");
            }



        };


        recycler.setAdapter(new EquipoAdapter(onItemClickListener));
        //recycler.setAdapter(new EquipoAdapter());

        return rootView;
    }

    public static void eliminar(Equipo equipo){
        EquipoSingleton itemList = EquipoSingleton.getInstance();
        int itemIndex = itemList.getItemList().getIndex(equipo);
        itemList.getItemList().deleteItem(itemIndex);
        itemList.getItemList().saveItems(context);
        FragmentManager fm = context.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout, new ListaEquiposFragment());
        ft.commit();

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


    /*public static void probando(int pos){
        DialogFragment editItemFragment = new EditTeam(EquipoSingleton.getItemList().getItem(pos), pos);
        FragmentManager fm = context.getSupportFragmentManager();
        editItemFragment.show(fm, "EditDialogFragment");
    }*/

}