package com.e.jarvis.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.repository.KeyHash
import com.e.jarvis.repository.Service
import kotlinx.coroutines.launch

class PesquisaViewModel(val service: Service) : ViewModel() {
    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )
    val listSearch = MutableLiveData<ArrayList<GenericResults>>()
    fun getSearch(starString : String){
        viewModelScope.launch {
            var result : ArrayList<GenericResults> = arrayListOf()

            val chars = service.getCharSearch(hash.ts, hash.publicKey, hash.getKey(),
                "%$starString%"
            ).data.results
            val comics = service.getComicsSearch(hash.ts, hash.publicKey, hash.getKey(),
                "%$starString%"
            ).data.results
            val series = service.getSeriesSearch(hash.ts, hash.publicKey, hash.getKey(),
                "%$starString%"
            ).data.results

            result.addAll(chars)
            result.addAll(comics)
            result.addAll(series)

            listSearch.value = result
        }
    }
}