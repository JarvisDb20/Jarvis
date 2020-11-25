package com.e.jarvis.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.e.jarvis.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_quiz.view.*

class QuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_quiz, container, false)
        //abre pesguntas do quiz
        view.btn_quiz.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_questionFragment)
        }
        return view

    }
}