package com.e.jarvis.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.chars.DataChars
import com.e.jarvis.models.chars.Results
import com.e.jarvis.repository.KeyHash
import com.e.jarvis.repository.Service
import com.e.jarvis.repository.service
import kotlinx.coroutines.launch

class HomeViewModel(service: Service) : ViewModel() {

    val hash = KeyHash( "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6")

    val personagem = MutableLiveData<List<Results>>()

    fun getChar(id : String) {
        viewModelScope.launch {
            personagem.value = service.getCharRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }


    }


}

//@GET("characters?ts=1&apikey=f28a07f38dc7090aa24b3e50496e6ac6&hash=f7aece34f231420e8d8fb2e698fa4113")