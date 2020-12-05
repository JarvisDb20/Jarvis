package com.e.jarvis.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.chars.Results
import com.e.jarvis.repository.KeyHash
import com.e.jarvis.repository.Service
import kotlinx.coroutines.launch

class HomeViewModel(val service: Service) : ViewModel() {

    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )
    val chars = MutableLiveData<ArrayList<Results>>()

    fun getChars(listCharId : ArrayList<String>){
        viewModelScope.launch {
            val resultado = arrayListOf<Results>()
            listCharId.forEach {
                resultado.addAll(
                    service.getCharRepo(it,hash.ts,hash.publicKey,hash.getKey()).data.results
                )
            }
            chars.value = resultado
        }
    }

}

