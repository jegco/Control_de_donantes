package com.example.jorgecaro.almacenamiento_jorge_caro.root;

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.DonorDialog;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.MainActivity;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.recycler.RecyclerViewAdapter;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jorge_caro on 3/14/18.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);
    void inject(DonorDialog donorDialog);
    void inject(RecyclerViewAdapter adapter);
}
