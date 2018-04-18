package com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.jorgecaro.almacenamiento_jorge_caro.R
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorContract
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.DonorPresenter
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.AddDonorFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.ChangePasswordFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.dialogs.DeleteUserFragment
import com.example.jorgecaro.almacenamiento_jorge_caro.domain.donor.view.recycler.RecyclerViewAdapter
import com.example.jorgecaro.almacenamiento_jorge_caro.interactors.SharedPreferenceInteractor
import com.example.jorgecaro.almacenamiento_jorge_caro.root.App
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), DonorContract.View {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    @Inject lateinit var addDonorDialog: AddDonorFragment
    @Inject lateinit var changePasswordDialog: ChangePasswordFragment
    @Inject lateinit var deleteUserDialog: DeleteUserFragment
    @Inject lateinit var presenter: DonorPresenter
    @Inject lateinit var sharedPreferenceInteractor: SharedPreferenceInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        App.component.inject(this)

        fab.setOnClickListener { addDonorDialog.show(supportFragmentManager, "Add") }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        //permite modificar el hint que el EditText muestra por defecto
        searchView.queryHint = getText(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val id = Integer.parseInt(query)
                refresh(id)
                //se oculta el EditText
                searchView.setQuery("", false)
                searchView.isIconified = true
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //id = Integer.parseInt(newText);
                return true
            }
        })
        return true
    }

    override fun refresh() {
        recyclerViewAdapter = RecyclerViewAdapter(this, presenter.listDonors(), supportFragmentManager)
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = recyclerViewAdapter
    }

    override fun refresh(id: Int) {
        recyclerViewAdapter = RecyclerViewAdapter(this, presenter.listDonorByID(id), supportFragmentManager)
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = recyclerViewAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (R.id.cerrar_sesion == id) {
            sharedPreferenceInteractor.clear()
            finish()
            return true
        }
        if (R.id.change_pass == id) {
            changePasswordDialog.show(supportFragmentManager, "Update")
        }
        if (R.id.delete_user == id) {
            deleteUserDialog.show(supportFragmentManager, "Delete")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showErrorPassword() {
        Toast.makeText(applicationContext, "error al ingresar los datos", Toast.LENGTH_LONG).show()
    }

    override fun showPasswordChangedMessage() {
        Toast.makeText(this, "contraseña cambiada correctamente", Toast.LENGTH_LONG).show()
    }

    override fun logout() {
        finish()
    }

    override fun onStart() {
        super.onStart()
        presenter.setview(this)
        refresh()
    }
}
