package com.example.jorgecaro.almacenamiento_jorge_caro.root

import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.MainActivity
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.AddDonorFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.EditDonorFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.recycler.RecyclerViewAdapter
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.LoginActivity
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.ChangePasswordFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.DeleteUserFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.login.view.dialogs.SignUpFragment

import javax.inject.Singleton

import dagger.Component

/**
 * Created by jorge_caro on 3/14/18.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(addDialog: AddDonorFragment)
    fun inject(editDonorFragment: EditDonorFragment)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(adapter: RecyclerViewAdapter)
    fun inject(changePasswordFragment: ChangePasswordFragment)
    fun inject(deleteUserFragment: DeleteUserFragment)
}
