package com.e.jarvis.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.jarvis.models.LoginModel
import com.e.jarvis.models.generics.GenericResults

class SharedViewModel : ViewModel() {
    private lateinit var result : GenericResults
    private val login = MutableLiveData<LoginModel>()
    private lateinit var searchString :String

    fun getSelectedResult(): GenericResults {
        return result
    }

    fun setSelectedResult(genericResults: GenericResults) {
        result = genericResults
    }

    fun setLoggedUser(loginModel: LoginModel) {
        login.value = loginModel
    }

    fun getLoggedUser(): LiveData<LoginModel> {
        return login
    }
    fun setSeach(string: String){
        searchString = string
    }
    fun getSeach(): String{
        return searchString
    }

}