package com.example.proyecto.miperfil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.Equipo.Equipo;
import com.example.proyecto.Fragments.ListaEquiposFragment;
import com.example.proyecto.R;
import com.example.proyecto.Realm.EquipoRealm;

public class MiPerfilAdapter extends RecyclerView.Adapter<MiPerfilAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;

    public MiPerfilAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public MiPerfilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitem, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiPerfilAdapter.ViewHolder holder, int position) {
        EquipoRealm equipo = MiPerfilSingleton.getItemList().getItem(position);
        System.out.println(MiPerfilSingleton.getItemList().getItem(position).getName());
        holder.bind2(equipo);
        holder.itemView.setOnClickListener(view -> {
            if (equipo != null) onItemClickListener.onItemClick(equipo);
        });
    }

    @Override
    public int getItemCount() {
        return MiPerfilSingleton.getItemList().getSize();
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
                    EquipoRealm equipo = MiPerfilSingleton.getItemList().getItem(getAdapterPosition());


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

    public interface OnItemClickListener {
        public void onItemClick(EquipoRealm equipo);
    }
}
