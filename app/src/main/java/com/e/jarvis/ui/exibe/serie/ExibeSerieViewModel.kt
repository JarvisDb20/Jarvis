package com.e.jarvis.ui.exibe.serie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.repository.KeyHash
import com.e.jarvis.repository.Service
import kotlinx.coroutines.launch

class ExibeSerieViewModel(val service: Service) : ViewModel() {

    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )

    val serie = MutableLiveData<ArrayList<GenericResults>>()

    fun getSerie(id: String) {
        viewModelScope.launch {
            serie.value =
                service.getSerieRepo(id, hash.ts, hash.publicKey, hash.getKey()).data.results
        }
    }

    fun getSeriesChar(id: String) {
        viewModelScope.launch {
            serie.value = service.getSeriesCharRepo(
                id,
                hash.ts,
                hash.publicKey,
                hash.getKey()
            ).data.results
        }
    }

    fun getSeriesStories(id: String) {
        viewModelScope.launch {
            serie.value = service.getSeriesStoriesRepo(
                id,
                hash.ts,
                hash.publicKey,
                hash.getKey()
            ).data.results
        }
    }


}