package com.e.jarvis.ui.exibe.serie

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

class ExibeSerieViewModel(val service: Service, val dataBase: RepositoryDataBase) : ViewModel() {

    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )

    val serie = MutableLiveData<ArrayList<GenericResults>>()

    val loading = MutableLiveData<Int>()

    fun getSerie(id: String) {
        loading.value = 1
        viewModelScope.launch {

            serie.value =
                service.getSerieRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results

            loading.value = 0
        }
    }

    fun getSeriesChar(id: String) {
        loading.value = 1
        viewModelScope.launch {

            serie.value = service.getSeriesCharRepo(
                id,
                hash.ts,
                hash.publicKey,
                hash.getKey()
            ).data.results

            loading.value = 0
        }
    }

    fun getSeriesStories(id: String) {
        loading.value = 1
        viewModelScope.launch {

            serie.value = service.getSeriesStoriesRepo(
                id,
                hash.ts,
                hash.publicKey,
                hash.getKey()
            ).data.results

            loading.value = 0
        }
    }

    //room tabela results
    fun addResults(serie: GenericResults) {
        viewModelScope.launch {
            dataBase.addResults(serie)
        }
    }

    //room tabela favoritos
    fun addFavorito(serie: GenericResults) {
        viewModelScope.launch {
            dataBase.addFavorito(Favorito(serie.id, serie, "serie"))
        }
    }


}