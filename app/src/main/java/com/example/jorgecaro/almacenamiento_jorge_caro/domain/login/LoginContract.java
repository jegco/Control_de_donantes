package com.example.jorgecaro.almacenamiento_jorge_caro.domain.login;

import com.example.jorgecaro.almacenamiento_jorge_caro.model.User;

/**
 * Created by jorge_caro on 3/14/18.
 */

public interface LoginContract {
    interface View {
        void showErrorName();
        void showErrorPassword();
        void openSignUpDialog();
        void login(User user);
        void goToMainActivity();
        void showErrorOnLogin();
    }

    interface Presenter {
        void setView(View view);
        void login(User user);
        boolean validateName(String name);
        boolean validatePassword(String password);
    }
}
