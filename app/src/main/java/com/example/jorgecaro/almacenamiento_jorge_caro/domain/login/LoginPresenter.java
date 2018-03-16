package com.example.jorgecaro.almacenamiento_jorge_caro.domain.login;

import android.content.SharedPreferences;

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.MainActivity;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.LoginActivity;
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.SharedPreferenceInteractor;
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.UserInteractor;
import com.example.jorgecaro.almacenamiento_jorge_caro.model.Constantes_de_preferencia;
import com.example.jorgecaro.almacenamiento_jorge_caro.model.User;
import com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local.UserDao;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jorge_caro on 3/14/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    UserInteractor userInteractor;

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
        userInteractor = UserInteractor.getInstance((LoginActivity) view);
    }

    @Override
    public boolean validateName(String name) {
        if(name.equals("")){
            view.showErrorName();
            return false;
        }
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        if(password.equals("")){
            view.showErrorPassword();
        }
        return true;
    }

    @Override
    public void login(User user) {
        if(validateName(user.getNombre()) && validatePassword(user.getPass())) {
            if(userInteractor.loginUser(user))
                view.goToMainActivity();
            else view.showErrorOnLogin();
        }
    }
}
