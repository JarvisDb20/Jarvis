package com.e.jarvis.ui.exibe.chars

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.chars.Results
import com.e.jarvis.repository.KeyHash
import com.e.jarvis.repository.Service
import com.e.jarvis.repository.service
import kotlinx.coroutines.launch

class ExibeCharViewModel(service: Service) : ViewModel() {


    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )

    //para testes vou usar o id do Hulk: 1009351

    val char = MutableLiveData<List<Results>>()

    fun getChar(id: String) {
        viewModelScope.launch {
            char.value =
                service.getCharRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }


    }


}