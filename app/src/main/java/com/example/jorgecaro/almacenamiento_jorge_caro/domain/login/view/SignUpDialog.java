package com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jorgecaro.almacenamiento_jorge_caro.model.User;
import com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local.UserDao;

/**
 * Created by jorge_caro on 3/14/18.
 */

public class SignUpDialog {

    private static SignUpDialog instance;
    private Context context;

    private SignUpDialog(Context context){
        this.context = context;
    }

    public static SignUpDialog getInstance(Context context){
        if(instance == null) instance = new SignUpDialog(context);
        return instance;
    }

    public AlertDialog create(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmacion");
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText nombre = new EditText(context);
        nombre.setHint("Nombre de usuario");
        layout.addView(nombre);
        final EditText pass = new EditText(context);
        pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pass.setHint("Password");
        layout.addView(pass);
        final EditText pass_n = new EditText(context);
        pass_n.setHint("confirmar password");
        pass_n.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(pass_n);
        builder.setView(layout);
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(validateName(nombre.getText().toString()) && validatePassword(pass.getText().toString(), pass_n.getText().toString())) {
                    User user = new User(nombre.getText().toString(), pass.getText().toString());
                    if (signUp(user)) {
                        Toast.makeText(context, "usuario registrado correctamente", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    } else
                        Toast.makeText(context, "error al ingresar los datos", Toast.LENGTH_LONG).show();
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

    private boolean validateName(String username){
        return !username.equals("");
    }
    private boolean validatePassword(String password, String passwordToConfirm){
        return !(password.equals("") || passwordToConfirm.equals("") || !password.equals(passwordToConfirm));
    }

    private boolean signUp(User user){
        return UserDao.getInstance(context).add(user);
    }
}
