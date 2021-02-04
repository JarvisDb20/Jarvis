package com.e.jarvis.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.database.db.QuizDb
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.modelsQuiz.Alternatives
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.models.modelsQuiz.UserQuiz
import com.e.jarvis.repository.QuizRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuizViewModel(
    private val repository: QuizRepository
) : ViewModel() {
    //Pegar instancia do usuario

    fun getUser() : LiveData<ResponseWrapper<ArrayList<UserQuiz>>> = MutableLiveData<ResponseWrapper<ArrayList<UserQuiz>>>().apply {
        viewModelScope.launch {
            repository.getAllUserQuiz().collect {
                if (it.status == ResponseWrapper.Status.SUCCESS)
                    if (it.data.isNullOrEmpty())
                        repository.updateUserQuiz(UserQuiz(rank = "Rookie")).collect()
                    else
                        value = it
            }
        }
    }
}