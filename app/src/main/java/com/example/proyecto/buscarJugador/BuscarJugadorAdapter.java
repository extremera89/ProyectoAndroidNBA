package com.example.proyecto.buscarJugador;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;


public class BuscarJugadorAdapter extends RecyclerView.Adapter<BuscarJugadorAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;

    public BuscarJugadorAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BuscarJugadorAdapter(){

    }

    @NonNull
    @Override
    public BuscarJugadorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buscarjugadorcard, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuscarJugadorAdapter.ViewHolder holder, int position) {

        String jugador = JugadorSingleton.getItemList().getItem(position);

        holder.bind2(jugador);
        holder.itemView.setOnClickListener(view -> {
            if (jugador != null) onItemClickListener.onItemClick(jugador);
        });

    }

    @Override
    public int getItemCount() {
        return JugadorSingleton.getItemList().getSize();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        private TextView nombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreBuscado);
            rootView = itemView;

        }

        public void bind2(String item) {
            nombre.setText(item);

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(String jugador);
    }


}

