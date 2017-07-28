package com.example.jorgecaro.almacenamiento_jorge_caro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jorgecaro.almacenamiento_jorge_caro.DAO.Database;
import com.example.jorgecaro.almacenamiento_jorge_caro.DAO.UserDao;
import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Constantes_de_preferencia;
import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText editText_nombre, editText_pass;
    TextInputLayout nombre, pass;
    Button iniciar_sesion, registrar_usuario;
    SharedPreferences.Editor editor;
    Database db;
    SharedPreferences sharedPreferencesCompat;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombre = (TextInputLayout) findViewById(R.id.til_nombre_usuario);
        pass = (TextInputLayout) findViewById(R.id.til_password);
        iniciar_sesion = (Button) findViewById(R.id.button_iniciar_sesion);
        registrar_usuario = (Button) findViewById(R.id.button_registrar_usuario);
        editText_nombre = nombre.getEditText();
        editText_pass = pass.getEditText();
        db = new Database(this);

        sharedPreferencesCompat = getSharedPreferences(Constantes_de_preferencia.PREFERENCIA_1, MODE_PRIVATE);
        editor = sharedPreferencesCompat.edit();

        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_nombre = nombre.getEditText();
                editText_pass = pass.getEditText();
                Usuario usuario = new Usuario(editText_nombre.getText().toString(), editText_pass.getText().toString());
                if(!editText_nombre.getText().toString().equals("")&&!editText_pass.getText().toString().equals("")) {
                    boolean res = UserDao.search_user(usuario, db);
                    if(res==true) {
                        editor.putString("usuario", editText_nombre.getText().toString());
                        editor.putString("pass", editText_pass.getText().toString());
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                }else if(editText_nombre.getText().toString().equals("")){
                    pass.setError("");
                    nombre.setError(getResources().getString(R.string.llenar));
                }else if(editText_pass.getText().toString().equals("")){
                    nombre.setError("");
                    pass.setError(getResources().getString(R.string.llenar));
                }
            }
        });

        registrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo_registrar().show();
            }
        });

    }

    public AlertDialog dialogo_registrar() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmacion");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText nombre = new EditText(this);
        nombre.setHint("Nombre de usuario");
        layout.addView(nombre);
        final EditText pass = new EditText(this);
        pass.setHint("Password");
        layout.addView(pass);
        final EditText pass_n = new EditText(this);
        pass_n.setHint("confirmar password");
        layout.addView(pass_n);
        builder.setView(layout);
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (!pass.getText().toString().equals("") && !pass_n.getText().toString().equals("") && pass.getText().toString().equals(pass_n.getText().toString())) {
                    boolean res = UserDao.insert_user(new Usuario(nombre.getText().toString(), pass.getText().toString()), db);
                    if(res == true) {
                        Toast.makeText(getApplicationContext(), "usuario registrado correctamente", Toast.LENGTH_LONG).show();
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
}
