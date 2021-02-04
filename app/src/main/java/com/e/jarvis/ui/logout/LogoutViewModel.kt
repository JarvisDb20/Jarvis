package com.e.jarvis.ui.logout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.repository.FirebaseRepository
import kotlinx.coroutines.launch

class LogoutViewModel(private val repository: FirebaseRepository) : ViewModel() {
    fun logout(){
        viewModelScope.launch {
            repository.logOut()
        }
    }
}