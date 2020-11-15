package com.e.jarvis.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.jarvis.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callQuiz()
    }

    fun callQuiz() {
        val mainFrag = QuizFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragment, mainFrag)
            commit()
        }
    }
}