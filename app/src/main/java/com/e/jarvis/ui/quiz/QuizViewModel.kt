package com.e.jarvis.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.modelsQuiz.Alternatives
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.repository.QuizRepository
import kotlinx.coroutines.launch

class QuizViewModel(private val QuizRepo: QuizRepository) : ViewModel() {
    //Pegar instancia do usuario
    fun initialQuestions() {
        viewModelScope.launch {
            if (QuizRepo.getAllIds().isEmpty())
                QuizRepo.addQuiz(
                    Quiz(
                        0,
                        "Born with super-human senses and the power to heal from almost any wound, _____ was captured by a secret Canadian organization and given an unbreakable skeleton and claws. Who is this character?",
                        Alternatives("Wolverine", true),
                        Alternatives("Scott Summers", false),
                        Alternatives("Groot", false),
                        Alternatives("Psylocke", false)
                    )
                )
        }
    }
}