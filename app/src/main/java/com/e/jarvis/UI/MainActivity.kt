package com.e.jarvis.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.e.jarvis.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private  lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(homeToolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainFragment) as NavHostFragment

        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //callQuiz()
        callQuestion()
    }

    fun callQuiz() {
        val mainFrag = QuizFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragment, mainFrag)
            commit()
        }
    }

    fun callQuestion() {
        val mainFrag = QuestionFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragment, mainFrag)
            commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}