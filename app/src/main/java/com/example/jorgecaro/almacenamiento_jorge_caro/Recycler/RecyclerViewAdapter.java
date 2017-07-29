package com.example.jorgecaro.almacenamiento_jorge_caro.Recycler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgecaro.almacenamiento_jorge_caro.DAO.DonanteDao;
import com.example.jorgecaro.almacenamiento_jorge_caro.MainActivity;
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
    Activity activity;

    public RecyclerViewAdapter(Context context, ArrayList<Donante> donantes, Activity activity){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.donantes = donantes;
        this.activity = activity;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.getNombre().setText("Nombre: "+donantes.get(position).getNombre()+" "+donantes.get(position).getApellido());
        holder.getEdad().setText("Edad: "+donantes.get(position).getEdad()+"");
        holder.getEstatura().setText("Estatura: "+donantes.get(position).getEstatura()+"");
        holder.getId().setText("Numero de identificacion: "+donantes.get(position).getId()+"");
        holder.getPeso().setText("Peso: "+donantes.get(position).getPeso()+"");
        holder.getSangre().setText("Tipo de sangre: "+donantes.get(position).getSangre());
        holder.getTipo().setText(donantes.get(position).getTipo());
        final int pos = position;
        holder.getEditar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo_editar(donantes.get(pos)).show();
            }
        });
        holder.getEliminar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo_eliminar(donantes.get(pos).getId()).show();

            }
        });
    }

    public AlertDialog dialogo_editar(Donante donante) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar parqueo");
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog, null);
        builder.setView(layout);
        final TextInputLayout id, nombre, apellido, edad, estatura, peso;
        final Spinner sangre, tipo;
        id = (TextInputLayout) layout.findViewById(R.id.til_id);
        nombre = (TextInputLayout) layout.findViewById(R.id.til_nombre_donante);
        apellido = (TextInputLayout) layout.findViewById(R.id.til_apellido_usuario);
        edad = (TextInputLayout) layout.findViewById(R.id.til_edad);
        estatura = (TextInputLayout) layout.findViewById(R.id.til_estatura);
        peso = (TextInputLayout) layout.findViewById(R.id.til_peso);
        sangre = (Spinner) layout.findViewById(R.id.sangre);
        tipo = (Spinner) layout.findViewById(R.id.tipo);
        id.getEditText().setText(donante.getId()+"");
        id.getEditText().setEnabled(false);
        nombre.getEditText().setText(donante.getNombre());
        apellido.getEditText().setText(donante.getApellido());
        edad.getEditText().setText(donante.getEdad()+"");
        estatura.getEditText().setText(donante.getEstatura()+"");
        peso.getEditText().setText(donante.getPeso()+"");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, context.getResources()
                .getStringArray(R.array.sangre));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sangre.setAdapter(adapter);
        int pos_sangre = adapter.getPosition(donante.getSangre());
        sangre.setSelection(pos_sangre);
        adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, context.getResources()
                .getStringArray(R.array.tipo));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);
        int pos_tipo = adapter.getPosition(donante.getTipo());
        tipo.setSelection(pos_tipo);
        builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (!id.getEditText().getText().toString().equals("") &&
                        !nombre.getEditText().getText().toString().equals("") &&
                        !apellido.getEditText().getText().toString().equals("") &&
                        !edad.getEditText().getText().toString().equals("") &&
                        !estatura.getEditText().getText().toString().equals("") &&
                        !peso.getEditText().getText().toString().equals("")) {
                    Donante donante = new Donante(nombre.getEditText().getText().toString()
                            ,Integer.parseInt(id.getEditText().getText().toString())
                            ,sangre.getSelectedItem().toString()
                            ,tipo.getSelectedItem().toString()
                            ,apellido.getEditText().getText().toString()
                            ,Integer.parseInt(edad.getEditText().getText().toString())
                            ,Integer.parseInt(peso.getEditText().getText().toString())
                            ,Integer.parseInt(estatura.getEditText().getText().toString()));
                    boolean res = DonanteDao.update_donante(donante,((MainActivity)activity).getDb());
                    if (res == true) {
                        Toast.makeText(context, "donante editado correctamente", Toast.LENGTH_LONG).show();
                        ((MainActivity)activity).refresh();
                    }
                }else if(nombre.getEditText().getText().toString().equals("") ||
                        id.getEditText().getText().toString().equals("") ||
                        edad.getEditText().getText().toString().equals("")||
                        apellido.getEditText().getText().toString().equals("")||
                        estatura.getEditText().getText().toString().equals("")||
                        peso.getEditText().getText().toString().equals("")){

                    nombre.setError(context.getResources().getString(R.string.llenar));
                    edad.setError(context.getResources().getString(R.string.llenar));
                    id.setError(context.getResources().getString(R.string.llenar));
                    estatura.setError(context.getResources().getString(R.string.llenar));
                    peso.setError(context.getResources().getString(R.string.llenar));
                    apellido.setError(context.getResources().getString(R.string.llenar));
                }
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


    public AlertDialog dialogo_eliminar(final int id) {
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
                DonanteDao.delete_donante(id,((MainActivity)activity).getDb());
                Toast.makeText(context, "donante eliminado correctamente" , Toast.LENGTH_LONG).show();
                ((MainActivity)activity).refresh();
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
