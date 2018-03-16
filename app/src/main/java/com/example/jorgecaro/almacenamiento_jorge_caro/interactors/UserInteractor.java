package com.example.jorgecaro.almacenamiento_jorge_caro.interactors;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.MainActivity;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.UserDialog;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.LoginActivity;
import com.example.jorgecaro.almacenamiento_jorge_caro.model.Constantes_de_preferencia;
import com.example.jorgecaro.almacenamiento_jorge_caro.model.User;
import com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local.UserDao;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jorge_caro on 3/14/18.
 */

public class UserInteractor {

    private static UserInteractor instance;
    private UserDao userDao;
    private SharedPreferenceInteractor sharedPreferenceInteractor;

    private UserInteractor(Context context) {
        userDao = UserDao.getInstance(context);
        this.sharedPreferenceInteractor = SharedPreferenceInteractor.getInstance(context);
    }

    public static UserInteractor getInstance(Context context){
        if (instance == null) instance = new UserInteractor(context);
        return instance;
    }

    public boolean loginUser(User user) {
        if (userDao.checkIfExist(user)) {
            sharedPreferenceInteractor.saveUser(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser() {
        userDao.delete(sharedPreferenceInteractor.getUser());
        sharedPreferenceInteractor.clear();
        return false;
    }

    public void changeUserPassword(String password) {
        User user = sharedPreferenceInteractor.getUser();
        user.setPass(password);
        userDao.update(user);
        sharedPreferenceInteractor.changePassword(password);
    }


}
