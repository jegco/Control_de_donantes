package com.example.jorgecaro.almacenamiento_jorge_caro.root

import android.app.Application
import android.content.Context

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorPresenter
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.UserDialog
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.AddDonorFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.ChangePasswordFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.DeleteUserFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.EditDonorFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.LoginPresenter
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.dialogs.SignUpFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.DonorInteractor
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.SharedPreferenceInteractor
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.UserInteractor
import com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local.DonorDao
import com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local.UserDao

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by jorge_caro on 3/14/18.
 */
@Module
internal class ApplicationModule(private val application: Application) {

    @Provides @Singleton fun provideContext(): Context = application

    @Provides @Singleton fun provideLoginPresenter(userInteractor: UserInteractor): LoginPresenter = LoginPresenter(userInteractor)

    @Provides @Singleton fun provideDonorPresenter(userInteractor: UserInteractor, donorInteractor: DonorInteractor): DonorPresenter = DonorPresenter(userInteractor, donorInteractor)

    @Provides @Singleton fun provideDonorDao(context: Context): DonorDao = DonorDao(context)

    @Provides @Singleton fun provideDonorInteractor(donorDao: DonorDao): DonorInteractor = DonorInteractor(donorDao)

    @Provides @Singleton fun provideUSerDao(context: Context): UserDao = UserDao(context)

    @Provides @Singleton fun provideUserInteractor(userDao: UserDao,
                                                   sharedPreferenceInteractor: SharedPreferenceInteractor): UserInteractor = UserInteractor(userDao, sharedPreferenceInteractor)

    @Provides @Singleton fun provideSharedPreferenceInteractor(context: Context): SharedPreferenceInteractor = SharedPreferenceInteractor(context)

    @Provides @Singleton fun provideAddDonorDialog(): AddDonorFragment = AddDonorFragment.instance

    @Provides @Singleton fun provideEditDonorDialog(): EditDonorFragment = EditDonorFragment.instance

    @Provides @Singleton fun provideUserDialog(context: Context, donorPresenter: DonorPresenter): UserDialog = UserDialog(context, donorPresenter)

    @Provides @Singleton fun provideSignUpDialog(): SignUpFragment = SignUpFragment.instance

    @Provides @Singleton fun provideDeleteUserpDialog(): DeleteUserFragment = DeleteUserFragment.instance

    @Provides @Singleton fun provideChangePasswordDialog(): ChangePasswordFragment = ChangePasswordFragment.instance

}
