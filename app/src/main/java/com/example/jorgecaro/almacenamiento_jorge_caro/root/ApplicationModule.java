package com.example.jorgecaro.almacenamiento_jorge_caro.root;

import android.app.Application;
import android.content.Context;

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorPresenter;
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.LoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorge_caro on 3/14/18.
 */
@Module
class ApplicationModule {

    private Application application;

    ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return application;
    }

    @Provides
    @Singleton
    LoginPresenter provideLoginPresenter(){
        return new LoginPresenter();
    }

    @Provides
    @Singleton
    DonorPresenter provideDonorPresenter(){
        return new DonorPresenter();
    }
}
