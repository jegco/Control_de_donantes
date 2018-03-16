package com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorgecaro.almacenamiento_jorge_caro.R;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.MainActivity;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.LoginContract;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.LoginPresenter;
import com.example.jorgecaro.almacenamiento_jorge_caro.model.User;
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    EditText editText_nombre, editText_pass;
    TextInputLayout nombre, pass;
    Button iniciar_sesion, registrar_usuario;
    @Inject
    public LoginPresenter presenter;

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

        App.component.inject(this);

        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_nombre = nombre.getEditText();
                editText_pass = pass.getEditText();
                User user = new User(editText_nombre.getText().toString(), editText_pass.getText().toString());
                login(user);
            }
        });

        registrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpDialog();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @Override
    public void showErrorName() {
            editText_pass.setError("");
            editText_nombre.setError(getResources().getString(R.string.llenar));
    }

    @Override
    public void showErrorPassword() {
            editText_nombre.setError("");
            editText_pass.setError(getResources().getString(R.string.llenar));
    }

    @Override
    public void openSignUpDialog() {
        SignUpDialog.getInstance(this).create().show();
    }

    @Override
    public void login(User user) {
        presenter.login(user);
    }

    @Override
    public void goToMainActivity() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void showErrorOnLogin() {
        Toast.makeText(getApplicationContext(), "El user no existe", Toast.LENGTH_SHORT).show();
    }
}
