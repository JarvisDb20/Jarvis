package com.e.jarvis.ui.exibe.story

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.KeyHash
import com.e.jarvis.repository.RepositoryDataBase
import com.e.jarvis.repository.Service
import kotlinx.coroutines.launch

class ExibeStoriesViewModel(val service: Service, val dataBase: RepositoryDataBase) : ViewModel() {


    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )


    val stories = MutableLiveData<ArrayList<GenericResults>>()

    fun getStoriesComics(id: String) {
        viewModelScope.launch {
            stories.value =
                service.getStoriesComicsRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }
    }

    fun getStoriesChar(id: String) {
        viewModelScope.launch {
            stories.value =
                service.getStoriesCharRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }
    }

    fun getStoriesSeries(id: String) {
        viewModelScope.launch {
            stories.value =
                service.getStoriesSeriesRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }
    }

    fun getStoriesStories(id: String) {
        viewModelScope.launch {
            stories.value =
                service.getStoriesStoriesRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }
    }


    //room:

    fun addResults(storie: GenericResults) {
        viewModelScope.launch {
            dataBase.addResults(storie)
        }
    }

}