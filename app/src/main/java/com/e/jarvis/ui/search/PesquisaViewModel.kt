package com.e.jarvis.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ApiObject
import com.e.jarvis.models.utils.KeyHash
import com.e.jarvis.repository.Service
import kotlinx.coroutines.launch

class PesquisaViewModel(val service: Service) : ViewModel() {

    val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )

    val listSearch = MutableLiveData<ArrayList<GenericResults>>()

    val loading = MutableLiveData<Int>()


    fun getSearch(starString : String){
        loading.value = 1

        viewModelScope.launch {
            val result : ArrayList<GenericResults> = arrayListOf()

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

            loading.value = 0
        }
    }

    //retorna o ApiObej com id e tipo
    fun passaDadosApiObj(currentItem: GenericResults): ApiObject {

        var apiObject: ApiObject = ApiObject("", "")

        if (currentItem.series == null)
            apiObject = ApiObject(currentItem.id, "series")
        else if (currentItem.comics == null)
            apiObject = ApiObject(currentItem.id, "comic")
        else if (currentItem.characters == null)
            apiObject = ApiObject(currentItem.id, "char")

        return apiObject

    }
}