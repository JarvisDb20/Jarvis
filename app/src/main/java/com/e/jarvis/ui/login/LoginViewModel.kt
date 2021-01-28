package com.e.jarvis.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.UserModel
import com.e.jarvis.repository.FirebaseRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: FirebaseRepository) : ViewModel() {
    fun login(user: UserModel) = MutableLiveData<ResponseWrapper<UserModel>>().apply{
        viewModelScope.launch {
            repository.logIn(user).collect {
                value = it
            }
        }
    }
}