package com.example.jorgecaro.almacenamiento_jorge_caro.root;

import android.app.Application;

/**
 * Created by jorge_caro on 3/14/18.
 */

public class App extends Application {
    public static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.
                    builder().
                    applicationModule(new ApplicationModule(this)).
                    build();
    }
}