package com.e.jarvis.ui.perguntas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.database.db.QuizDb
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.modelsQuiz.Alternatives
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.repository.QuizRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class QuestionViewModel(private val repository: QuizRepository) : ViewModel() {
    val question = MutableLiveData<Quiz>()
    val answer = MutableLiveData<Alternatives>()
    fun getQuiz() {
        viewModelScope.launch {
            val ids = repository.getQuiz().collect { quiz ->
                if (quiz.status == ResponseWrapper.Status.SUCCESS) {
                    quiz.data?.let{quest ->
                        when {
                            quest.alternative1.correct -> answer.value = quest.alternative1
                            quest.alternative2.correct -> answer.value = quest.alternative2
                            quest.alternative3.correct -> answer.value = quest.alternative3
                            quest.alternative4.correct -> answer.value = quest.alternative4
                        }
                        question.value = quest
                    }

                }
            }
        }
    }

    fun addPointsQuiz(){
        viewModelScope.launch {
            repository.addPointsQuiz().collect ()
        }
    }
}