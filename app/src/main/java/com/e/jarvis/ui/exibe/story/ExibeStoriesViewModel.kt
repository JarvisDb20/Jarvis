package com.e.jarvis.ui.exibe.story

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.models.utils.KeyHash
import com.e.jarvis.repository.RepositoryDataBase
import com.e.jarvis.repository.Service
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExibeStoriesViewModel(val service: Service, val dataBase: RepositoryDataBase) : ViewModel() {


    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )


    val stories = MutableLiveData<ArrayList<GenericResults>>()

    val loading = MutableLiveData<Int>()

    fun getStoriesComics(id: String) {
        loading.value = 1
        viewModelScope.launch {
            delay(1000)
            stories.value =
                service.getStoriesComicsRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }
    }

    fun getStoriesChar(id: String) {
        loading.value = 1
        viewModelScope.launch {
            delay(1000)
            stories.value =
                service.getStoriesCharRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }
    }

    fun getStoriesSeries(id: String) {
        loading.value = 1
        viewModelScope.launch {
            delay(1000)
            stories.value =
                service.getStoriesSeriesRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }
    }

    fun getStoriesStories(id: String) {
        loading.value = 1
        viewModelScope.launch {
            delay(1000)
            stories.value =
                service.getStoriesStoriesRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }
    }


    //room tabela results
    fun addResults(storie: GenericResults) {
        viewModelScope.launch {
            dataBase.addResults(storie)
        }
    }


    //room tabela favoritos
    fun addFavorito(storie: GenericResults) {
        viewModelScope.launch {
            dataBase.addFavorito(Favorito(storie.id, storie, "storie"))
        }
    }
}