package com.e.jarvis.ui.logout

import androidx.lifecycle.ViewModel
import com.e.jarvis.repository.FirebaseRepository

class LogoutViewModel(private val repository: FirebaseRepository) : ViewModel() {
    fun logout(){
        repository.logOut()
    }
}