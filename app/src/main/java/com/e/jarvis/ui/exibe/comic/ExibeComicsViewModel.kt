package com.e.jarvis.ui.exibe.comic

import com.e.jarvis.models.generics.GenericResults

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.models.utils.KeyHash
import com.e.jarvis.repository.RepositoryDataBase
import com.e.jarvis.repository.Service
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch

class ExibeComicsViewModel(val service: Service, val dataBase: RepositoryDataBase) : ViewModel() {

    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )

    val comic = MutableLiveData<ArrayList<GenericResults>>()

    val loading = MutableLiveData<Int>()


    fun getComic(id: String) {
        loading.value = 1

        viewModelScope.launch {
            delay(1500)
            comic.value =
                service.getComicRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }

    }

    fun getComicChar(id: String) {
        loading.value = 1

        viewModelScope.launch {
            delay(1500)
            comic.value =
                service.getComicsCharRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }

    }

    fun getComicSeries(id: String) {
        loading.value = 1

        viewModelScope.launch {
            delay(1500)
            comic.value =
                service.getComicSeriesRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }

    }

    fun getComicStories(id: String) {
        loading.value = 1

        viewModelScope.launch {

            delay(1500)

            comic.value =
                service.getComicStorieRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }

    }





    //room tabela results
    fun addResults(comic: GenericResults) {
        viewModelScope.launch {
            dataBase.addResults(comic)
        }
    }


    //room tabela favoritos
    fun addFavorito(comic: GenericResults) {
        viewModelScope.launch {
            dataBase.addFavorito(Favorito(comic.id, comic, "comic"))
        }
    }

}