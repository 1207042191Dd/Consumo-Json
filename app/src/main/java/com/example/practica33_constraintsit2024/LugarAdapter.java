package com.example.practica33_constraintsit2024;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder> {
    private List<LugarTuristico> lugares;

    public LugarAdapter(List<LugarTuristico> lugares) {
        this.lugares = lugares;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lugar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LugarTuristico lugar = lugares.get(position);
        holder.nombre.setText(lugar.getNombreLugar());
        holder.categoria.setText("Tel: " + lugar.getTelefono());
    }
    @Override
    public int getItemCount() {
        return lugares.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, categoria;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreLugar);

            categoria = itemView.findViewById(R.id.categoriaLugar);

        }
    }
}

