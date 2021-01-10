package com.e.jarvis.ui.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.e.jarvis.R
import com.e.jarvis.models.modelsQuiz.Alternatives
import com.e.jarvis.ui.quiz.QuizViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionFragment : Fragment() {
    private val viewModel: QuestionViewModel by viewModel()
    private lateinit var answer : Alternatives

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvQuestion: TextView = view.findViewById(R.id.tv_question)
        val tvAlt1: TextView = view.findViewById(R.id.tv_question_alt1)
        val tvAlt2: TextView = view.findViewById(R.id.tv_question_alt2)
        val tvAlt3: TextView = view.findViewById(R.id.tv_question_alt3)
        val tvAlt4: TextView = view.findViewById(R.id.tv_question_alt4)

        viewModel.question.observe(viewLifecycleOwner, { question ->
            tvQuestion.text = question.question
            tvAlt1.text = question.alternative1.altString
            tvAlt2.text = question.alternative2.altString
            tvAlt3.text = question.alternative3.altString
            tvAlt4.text = question.alternative4.altString

        })
        viewModel.answer.observe(viewLifecycleOwner,{
            answer = it
        })
        
        tvAlt1.setOnClickListener {  
            if (answer.altString == tvAlt1.text)
                sendToast("Correct answer!")
            else
                sendToast("Wrong answer")
        }
        tvAlt2.setOnClickListener {
            if (answer.altString == tvAlt2.text)
                sendToast("Correct answer!")
            else
                sendToast("Wrong answer")
        }
        tvAlt3.setOnClickListener {
            if (answer.altString == tvAlt3.text)
                sendToast("Correct answer!")
            else
                sendToast("Wrong answer")
        }
        tvAlt4.setOnClickListener {
            if (answer.altString == tvAlt4.text)
                sendToast("Correct answer!")
            else
                sendToast("Wrong answer")
        }
        viewModel.getQuiz()
    }

    private fun sendToast(message : String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}