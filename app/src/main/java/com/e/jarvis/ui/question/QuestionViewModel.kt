package com.e.jarvis.ui.question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.modelsQuiz.Alternatives
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.repository.QuizRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class QuestionViewModel(private val QuizRepo: QuizRepository) : ViewModel() {
    val question = MutableLiveData<Quiz>()
    val answer = MutableLiveData<Alternatives>()
    fun getQuiz() {
        viewModelScope.launch {
            val ids = QuizRepo.getAllIds()

            val questionId = Random.nextInt(0, ids.size)
            val quest = QuizRepo.getQuiz(ids[questionId])
            question.value = quest

            if (quest.alternative1.correct)
                answer.value = quest.alternative1
            else if (quest.alternative2.correct)
                answer.value = quest.alternative2
            else if (quest.alternative3.correct)
                answer.value = quest.alternative3
            else if (quest.alternative4.correct)
                answer.value = quest.alternative4


        }
    }
}