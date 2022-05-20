package ucv.edu.pe.mindperformance

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import ucv.edu.pe.mindperformance.adapter.PracticaAdapter

enum class ProviderType{
    BASIC
}

class DrawerMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toogle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawermain)
        initRecyclerView()

        val toolbar : Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)

        toogle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toogle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    //DECLARAR FUNCIONES DEL MENU DE NAVEGACIÓN
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_user -> this.usersettings()
            R.id.nav_logout -> this.logout()
            R.id.nav_curso -> this.curso()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rcv_practicas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PracticaAdapter(PracticaProvider.Practicalist)
    }

    //funciones del menu de navegacion
    private fun usersettings() {
        val user = Intent(this, UserActivity::class.java)
        startActivity(user)
    }

    private fun logout() {
        title = "Inicio"

        val logout = findViewById<Group>(R.id.nav_logout)
        logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }

    private fun curso(){
        val curso = Intent(this, CursoMainActivity::class.java)
        startActivity(curso)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toogle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toogle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}