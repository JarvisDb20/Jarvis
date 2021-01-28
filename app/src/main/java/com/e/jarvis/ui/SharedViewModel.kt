package com.e.jarvis.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.UserModel
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.repository.FirebaseRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel(private val firebaseRepository: FirebaseRepository) : ViewModel( ) {
    private lateinit var result : GenericResults
    private val login = MutableLiveData<UserModel>()
    private lateinit var searchString :String

    fun getSelectedResult(): GenericResults {
        return result
    }

    fun setSelectedResult(genericResults: GenericResults) {
        result = genericResults
    }


    fun setLoggedUser(userModel: UserModel) {
        login.value = userModel
    }

    fun getLoggedUser(): LiveData<UserModel> {

        return login
    }
    fun setSeach(string: String){
        searchString = string
    }
    fun getSeach(): String{
        return searchString
    }


    fun getLogin() = MutableLiveData<Boolean>().apply  {
        viewModelScope.launch {
            firebaseRepository.getLogged().collect {
                value = it
            }
        }
    }


}