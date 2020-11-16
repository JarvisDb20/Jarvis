package com.e.jarvis.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.e.jarvis.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(homeToolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainFragment) as NavHostFragment
        navController = navHostFragment.navController


        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController,appBarConfiguration)
        findViewById<Toolbar>(R.id.homeToolbar)
            .setupWithNavController(navController, appBarConfiguration)


        //drawerLayout = drawer
        //appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        //nvMenu.setupWithNavController(navController)
        //homeToolbar.setupWithNavController(navController,appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.mainFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}