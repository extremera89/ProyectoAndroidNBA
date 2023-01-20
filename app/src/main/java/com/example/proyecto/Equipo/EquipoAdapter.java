package com.example.proyecto.Equipo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.Fragments.EditTeam;
import com.example.proyecto.Fragments.ListaEquiposFragment;
import com.example.proyecto.PantallaPrincipal;
import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;


public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;


    public EquipoAdapter(OnItemClickListener onItemClickListener) {
       this.onItemClickListener = onItemClickListener;
    }

    public EquipoAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem, parent, false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Equipo equipo = EquipoSingleton.getItemList().getItem(position);
        EquipoRealm equipo = EquipoSingleton.getItemList().getItem2(position);

        holder.bind2(equipo);
        holder.itemView.setOnClickListener(view -> {
            if (equipo != null) onItemClickListener.onItemClick(equipo);
        });
    }



    @Override
    public int getItemCount() {
        return EquipoSingleton.getItemList().getSize();
    }

    /*public interface OnItemClickListener {
        public void onItemClick(Equipo equipo);
    }*/

    public interface OnItemClickListener {
        public void onItemClick(EquipoRealm equipo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        private TextView title;
        private TextView maker;
        private ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.team);
            maker = (TextView) itemView.findViewById(R.id.year);
            foto  = (ImageView) itemView.findViewById(R.id.imagenCard);
            rootView = itemView;

            itemView.findViewById(R.id.botonEliminar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EquipoRealm equipo = EquipoSingleton.getItemList().getItem2(getAdapterPosition());


                    //System.out.println("POSICION=> "+getAdapterPosition());


                    ListaEquiposFragment.eliminar2(equipo);
                }
            });


        }

        public void bind(Equipo item) {
            title.setText(item.getName());
            maker.setText(item.getYear());
            foto.setImageBitmap(item.getImage());

        }

        public void bind2(EquipoRealm item) {
            title.setText(item.getName());
            maker.setText(String.valueOf(item.getYear()));

            byte [] encodeByte = Base64.decode(item.getImage(),Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            foto.setImageBitmap(bitmap);


        }
    }



}
