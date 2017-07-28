package com.example.jorgecaro.almacenamiento_jorge_caro.Recycler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Donante;
import com.example.jorgecaro.almacenamiento_jorge_caro.R;

import java.util.ArrayList;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Donante> donantes;

    public RecyclerViewAdapter(Context context, ArrayList<Donante> donantes){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.donantes = donantes;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.getNombre().setText(donantes.get(position).getNombre()+" "+donantes.get(position).getApellido());
        holder.getEdad().setText(donantes.get(position).getEdad()+"");
        holder.getEstatura().setText(donantes.get(position).getEstatura()+"");
        holder.getId().setText(donantes.get(position).getId());
        holder.getPeso().setText(donantes.get(position).getPeso()+"");
        holder.getSangre().setText(donantes.get(position).getSangre());
        holder.getTipo().setText(donantes.get(position).getTipo());
        holder.getEditar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo_editar().show();
            }
        });
        holder.getEliminar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo_eliminar().show();
            }
        });
    }

    public AlertDialog dialogo_editar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar parqueo");
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog, null);
        builder.setView(layout);
        builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

                Toast.makeText(context, "donante editado correctamente" , Toast.LENGTH_LONG).show();

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }


    public AlertDialog dialogo_eliminar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmacion");
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final TextView matricula = new TextView(context);
        matricula.setText("Desea eliminar al donante?");
        layout.addView(matricula);
        builder.setView(layout);
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

                Toast.makeText(context, "donante eliminado correctamente" , Toast.LENGTH_LONG).show();

            }
        });

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    @Override
    public int getItemCount() {
        return donantes.size();
    }
}
