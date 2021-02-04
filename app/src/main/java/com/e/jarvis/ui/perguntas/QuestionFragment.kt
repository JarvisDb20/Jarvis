package com.e.jarvis.ui.perguntas

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.e.jarvis.R
import com.e.jarvis.models.modelsQuiz.Alternatives
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.ui.BaseFragment
import com.google.android.material.card.MaterialCardView
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionFragment : BaseFragment() {
    private val viewModel: QuestionViewModel by viewModel()
    private lateinit var answer: Alternatives
    private var respondido = false
    private var pergunta = Quiz()

    private lateinit var mc1 :MaterialCardView
    private lateinit var mc2 :MaterialCardView
    private lateinit var mc3 :MaterialCardView
    private lateinit var mc4 :MaterialCardView

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

        mc1 = view?.findViewById<MaterialCardView>(R.id.mc_1)!!
        mc2 = view?.findViewById<MaterialCardView>(R.id.mc_2)!!
        mc3 = view?.findViewById<MaterialCardView>(R.id.mc_3)!!
        mc4 = view?.findViewById<MaterialCardView>(R.id.mc_4)!!


        viewModel.question.observe(viewLifecycleOwner, { question ->
            pergunta = question
            tvQuestion.text = question.question
            tvAlt1.text = question.alternative1.altString
            tvAlt2.text = question.alternative2.altString
            tvAlt3.text = question.alternative3.altString
            tvAlt4.text = question.alternative4.altString

        })
        viewModel.answer.observe(viewLifecycleOwner, {
            answer = it
        })

        mc1.setOnClickListener {
            alternativaSelecionada(tvAlt1.text.toString())
        }
        mc2.setOnClickListener {
            alternativaSelecionada(tvAlt2.text.toString())
        }
        mc3.setOnClickListener {
            alternativaSelecionada(tvAlt3.text.toString())
        }
        mc4.setOnClickListener {
            alternativaSelecionada(tvAlt4.text.toString())
        }
        viewModel.getQuiz()
    }

    private fun alternativaSelecionada(alternativa : String){
        if (!respondido) {
            respondido = true

            val colorCorrect = Color.parseColor("#55AA55")
            val colorIncorrect = Color.parseColor("#AA5555")

            if(pergunta.alternative1.correct) {
                if(alternativa == pergunta.alternative1.altString){
                    viewModel.addPointsQuiz()
                }
                mc1.setCardBackgroundColor(colorCorrect)
                mc2.setCardBackgroundColor(colorIncorrect)
                mc3.setCardBackgroundColor(colorIncorrect)
                mc4.setCardBackgroundColor(colorIncorrect)
            }else if(pergunta.alternative2.correct){
                if(alternativa == pergunta.alternative2.altString){
                    viewModel.addPointsQuiz()
                }
                mc1.setCardBackgroundColor(colorIncorrect)
                mc2.setCardBackgroundColor(colorCorrect)
                mc3.setCardBackgroundColor(colorIncorrect)
                mc4.setCardBackgroundColor(colorIncorrect)
            }else if(pergunta.alternative3.correct){
                if(alternativa == pergunta.alternative3.altString){
                    viewModel.addPointsQuiz()
                }
                mc1.setCardBackgroundColor(colorIncorrect)
                mc2.setCardBackgroundColor(colorIncorrect)
                mc3.setCardBackgroundColor(colorCorrect)
                mc4.setCardBackgroundColor(colorIncorrect)
            }else if(pergunta.alternative4.correct){
                if(alternativa == pergunta.alternative4.altString){
                    viewModel.addPointsQuiz()
                }
                mc1.setCardBackgroundColor(colorIncorrect)
                mc2.setCardBackgroundColor(colorIncorrect)
                mc3.setCardBackgroundColor(colorIncorrect)
                mc4.setCardBackgroundColor(colorCorrect)
            }
        }
    }
}