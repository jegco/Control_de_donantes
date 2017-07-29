package com.example.jorgecaro.almacenamiento_jorge_caro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgecaro.almacenamiento_jorge_caro.DAO.Database;
import com.example.jorgecaro.almacenamiento_jorge_caro.DAO.DonanteDao;
import com.example.jorgecaro.almacenamiento_jorge_caro.DAO.UserDao;
import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Constantes_de_preferencia;
import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Donante;
import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Usuario;
import com.example.jorgecaro.almacenamiento_jorge_caro.Recycler.RecyclerViewAdapter;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Database db;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    SharedPreferences sharedPreferencesCompat;
    SharedPreferences.Editor editor;
    EditText editId;
    ImageButton buscar, limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editId = (EditText) findViewById(R.id.editId);
        buscar = (ImageButton) findViewById(R.id.btn_buscar);
        limpiar = (ImageButton) findViewById(R.id.btn_limpiar);

        db = new Database(this);
        sharedPreferencesCompat = getSharedPreferences(Constantes_de_preferencia.PREFERENCIA_1, MODE_PRIVATE);
        editor = sharedPreferencesCompat.edit();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        refresh();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo_registrar().show();
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= Integer.parseInt(editId.getText().toString());
                refresh(id);
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editId.setText("");
                refresh();
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.sangre));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sangre.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.tipo));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);
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
                    if (res == true) {
                        Toast.makeText(getBaseContext(), "donante registrado correctamente", Toast.LENGTH_LONG).show();
                        refresh();
                    }else Toast.makeText(getBaseContext(), "el donante ya existe", Toast.LENGTH_LONG).show();
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

    public AlertDialog dialogo_cambiar_pass() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmacion");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText pass = new EditText(this);
        pass.setHint("Password");
        layout.addView(pass);
        final EditText pass_n = new EditText(this);
        pass_n.setHint("Nueva password");
        layout.addView(pass_n);
        builder.setView(layout);
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (!pass.getText().toString().equals("") && !pass_n.getText().toString().equals("") && !pass.getText().toString().equals(pass_n.getText().toString())) {
                    boolean res = UserDao.update_user(new Usuario(sharedPreferencesCompat.getString(Constantes_de_preferencia.USUARIO,"n/a"),pass.getText().toString()), pass_n.getText().toString(),db);
                    if(res == true) {
                        Toast.makeText(getApplicationContext(), "contraseña cambiada correctamente", Toast.LENGTH_LONG).show();
                        editor.putString("pass", pass_n.getText().toString());
                        editor.apply();
                    }else Toast.makeText(getApplicationContext(), "error al ingresar los datos", Toast.LENGTH_LONG).show();
                }

            }
        });

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void refresh(){
        recyclerViewAdapter = new RecyclerViewAdapter(this,DonanteDao.search_donantes(db), this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void refresh(int id){
        recyclerViewAdapter = new RecyclerViewAdapter(this,DonanteDao.search_donante(id, db), this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public Database getDb() {
        return db;
    }

    public static void clearSharedPreferences(Context ctx){
        File dir = new File(ctx.getFilesDir().getParent() + "/shared_prefs/");
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            ctx.getSharedPreferences(children[i].replace(".xml", ""), Context.MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();
        }
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        for (int i = 0; i < children.length; i++) {
            new File(dir, children[i]).delete();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.cerrar_sesion == id){
            String dir = getFilesDir().getAbsolutePath();
            File f0 = new File(dir, Constantes_de_preferencia.PREFERENCIA_1);
            f0.delete();
            clearSharedPreferences(this);
            finish();
            return true;
        }
        if (R.id.cambiar_pass == id){
            dialogo_cambiar_pass().show();
        }
        if (R.id.eliminar_user == id){
            dialogo_eliminar().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public AlertDialog dialogo_eliminar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmacion");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final TextView matricula = new TextView(this);
        matricula.setText("Desea eliminar al usuario?");
        layout.addView(matricula);
        builder.setView(layout);
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                boolean res = UserDao.delete_user(new Usuario(sharedPreferencesCompat.getString(Constantes_de_preferencia.USUARIO,"n/a"),sharedPreferencesCompat.getString(Constantes_de_preferencia.PASS,"n/a")),db);
                if (res == true) {
                    Toast.makeText(getBaseContext(), "usuario eliminado correctamente", Toast.LENGTH_LONG).show();
                    String dir = getFilesDir().getAbsolutePath();
                    File f0 = new File(dir, Constantes_de_preferencia.PREFERENCIA_1);
                    f0.delete();
                    clearSharedPreferences(getBaseContext());
                    finish();
                }
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
}
