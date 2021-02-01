package com.e.jarvis.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.e.jarvis.R
import com.e.jarvis.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_quiz.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class QuizFragment : BaseFragment() {
    override var loginRequired = true
    private val viewModel: QuizViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btn_quiz.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_quizFragment_to_questionFragment)
        }
        viewModel.initialQuestions();
    }
}