package com.e.jarvis.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.e.jarvis.R
import com.e.jarvis.models.utils.MenuAppBar
import com.e.jarvis.models.utils.DrawerMenuItem
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navView: NavigationView
    private lateinit var toolbarLayout: AppBarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navBottomView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    val sharedModel : SharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navView = findViewById(R.id.nav_view)
        navBottomView = findViewById(R.id.nav_bottom_view)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbarLayout = findViewById<AppBarLayout>(R.id.toolbar_layout)
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.exibePersonagemFragment,
                R.id.exibeComicsFragment,
                R.id.exibeStoriesFragment,
                R.id.pesquisaFragment,
                R.id.exibeSeriesFragement,
                R.id.quizFragment,
                R.id.favoritosFragment,
            ), drawerLayout
        )
        // aparecer o icone de estrela
        setSupportActionBar(toolbar)
        // aparecer o titulo de acordo com a janela
        toolbar.setupWithNavController(navController, appBarConfiguration)
        //aparecer o bottom menu
        navBottomView.setupWithNavController(navController)
        //aparecer o drawer
        navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_appbar, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    fun setBottomNavigationVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        navBottomView.visibility = visibility
    }

    fun setToolbarVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        toolbarLayout.visibility = visibility

    }

    fun setNavDrawerVisibility(drawerMenuItem: DrawerMenuItem) {

        navView.menu.forEach { item ->
            when (item.title.toString().toLowerCase()) {
                "home" -> item.isVisible = drawerMenuItem.home
                "quiz" -> item.isVisible = drawerMenuItem.quiz
                "favorites" -> item.isVisible = drawerMenuItem.favorites
                "login" -> item.isVisible = drawerMenuItem.login
                "logout" -> item.isVisible = drawerMenuItem.logout

            }
        }
    }

    fun resizeFragment() {
        // Resize navhost de acordo com os outros elementos
        val view: View? = navHostFragment.view?.parent as ViewGroup

        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )

        if (toolbarLayout.visibility == View.VISIBLE) {
            params.addRule(RelativeLayout.BELOW, R.id.toolbar_layout)
        }

        if (navBottomView.visibility == View.VISIBLE) {
            params.addRule(RelativeLayout.ABOVE, R.id.nav_bottom_view)
        }
        if (view != null) {
            view.layoutParams = params
        }
    }


}
