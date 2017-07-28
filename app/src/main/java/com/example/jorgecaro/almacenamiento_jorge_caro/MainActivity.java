package com.example.jorgecaro.almacenamiento_jorge_caro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jorgecaro.almacenamiento_jorge_caro.DAO.Database;
import com.example.jorgecaro.almacenamiento_jorge_caro.DAO.DonanteDao;
import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Donante;

public class MainActivity extends AppCompatActivity {

    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new Database(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo_registrar().show();
            }
        });
    }

    public AlertDialog dialogo_registrar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registrar donante");
        LayoutInflater layoutInflater = LayoutInflater.from(this);
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
                    boolean res = DonanteDao.insert_donante(donante,db);
                    if (res == true)
                        Toast.makeText(getBaseContext(), "donante registrado correctamente", Toast.LENGTH_LONG).show();
                    else Toast.makeText(getBaseContext(), "el donante ya existe", Toast.LENGTH_LONG).show();
                }else if(nombre.getEditText().getText().toString().equals("") ||
                        id.getEditText().getText().toString().equals("") ||
                        edad.getEditText().getText().toString().equals("")||
                        apellido.getEditText().getText().toString().equals("")||
                        estatura.getEditText().getText().toString().equals("")||
                        peso.getEditText().getText().toString().equals("")){

                    nombre.setError(getResources().getString(R.string.llenar));
                    edad.setError(getResources().getString(R.string.llenar));
                    id.setError(getResources().getString(R.string.llenar));
                    estatura.setError(getResources().getString(R.string.llenar));
                    peso.setError(getResources().getString(R.string.llenar));
                    apellido.setError(getResources().getString(R.string.llenar));
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

}
