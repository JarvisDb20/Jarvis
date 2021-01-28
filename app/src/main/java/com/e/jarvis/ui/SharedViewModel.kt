package com.e.jarvis.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
<<<<<<< HEAD
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.UserModel
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.repository.FirebaseRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel(private val firebaseRepository: FirebaseRepository) : ViewModel( ) {
    private lateinit var result : GenericResults
    private val login = MutableLiveData<UserModel>()
=======
import com.e.jarvis.models.LoginModel
import com.e.jarvis.models.generics.GenericResults

class SharedViewModel : ViewModel() {
    private lateinit var result : GenericResults
    private val login = MutableLiveData<LoginModel>()
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
    private lateinit var searchString :String

    fun getSelectedResult(): GenericResults {
        return result
    }

    fun setSelectedResult(genericResults: GenericResults) {
        result = genericResults
    }

<<<<<<< HEAD
    fun setLoggedUser(userModel: UserModel) {
        login.value = userModel
    }

    fun getLoggedUser(): LiveData<UserModel> {
=======
    fun setLoggedUser(loginModel: LoginModel) {
        login.value = loginModel
    }

    fun getLoggedUser(): LiveData<LoginModel> {
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
        return login
    }
    fun setSeach(string: String){
        searchString = string
    }
    fun getSeach(): String{
        return searchString
    }

<<<<<<< HEAD
    fun getLogin() = MutableLiveData<Boolean>().apply  {
        viewModelScope.launch {
            firebaseRepository.getLogged().collect {
                value = it
            }
        }
    }

=======
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
}