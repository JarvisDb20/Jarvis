package com.e.jarvis.ui.exibe.chars

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.KeyHash
import com.e.jarvis.repository.RepositoryDataBase
import com.e.jarvis.repository.Service
import kotlinx.coroutines.launch

class ExibeCharViewModel(val service: Service, val dataBase: RepositoryDataBase) : ViewModel() {


    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )

    val char = MutableLiveData<ArrayList<GenericResults>>()

    fun getChar(id: String) {
        viewModelScope.launch {
            char.value =
                service.getCharRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }
    }

    fun getCharComics(id: String) {
        viewModelScope.launch {
            char.value =
                service.getCharComicRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }
    }

    fun getCharDaSerie(id: String) {
        viewModelScope.launch {
            char.value =
                service.getCharSeriesRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }
    }

    fun getCharDaStories(id: String) {
        viewModelScope.launch {
            char.value = service.getCharStoriesRepo(
                id,
                hash.ts,
                hash.publicKey,
                hash.getKey()
            ).data.results
        }
    }


    //room:

    fun addResults(char: GenericResults) {
        viewModelScope.launch {
            dataBase.addResults(char)
        }
    }


}