package com.e.jarvis.ui.exibe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.models.utils.KeyHash
import com.e.jarvis.repository.RepositoryDataBase
import com.e.jarvis.repository.Service
import kotlinx.coroutines.launch

class ExibeViewModel(val service: Service, val dataBase: RepositoryDataBase) : ViewModel() {


    private val hash = KeyHash(
        "bacf6559c29f05132ea07020962d41a65dcd3304",
        "f28a07f38dc7090aa24b3e50496e6ac6"
    )

    val result = MutableLiveData<List<GenericResults>>()

    val loading = MutableLiveData<Int>()

    fun getResult(genericResults: GenericResults, origin: String) {
        val apiObject = genericResults.apiObject!!
        when (apiObject.tipoId) {
            "char" -> {
                getChar(apiObject.id, origin)
            }
            "comic" -> {
                getCharComics(apiObject.id, origin)
            }
            "series" -> {
                getCharDaSerie(apiObject.id, origin)
            }
            "stories" -> {
                getCharDaStories(apiObject.id, origin)
            }
        }
    }

    fun getChar(id: String, origin: String) {
        loading.value = 1
        viewModelScope.launch {
            var resultExibido = dataBase.getResults(id)
            if (resultExibido.isNullOrEmpty()) {
                when (origin) {
                    "char" -> {
                        resultExibido = service.getCharRepo(
                            id,
                            hash.ts,
                            hash.publicKey,
                            hash.getKey()
                        ).data.results
                    }
                    "comic" -> {

                    }
                    "series" -> {

                    }
                    "stories" -> {

                    }
                }
                dataBase.addResults(resultExibido[0])
            }
            result.value = resultExibido
            loading.value = 0
        }
    }

    fun getCharComics(id: String, origin: String) {
        loading.value = 1
        viewModelScope.launch {
            when (origin) {
                "char" -> {
                    result.value = service.getCharComicRepo(
                        id,
                        hash.ts,
                        hash.publicKey,
                        hash.getKey()
                    ).data.results
                }
                "comic" -> {

                }
                "series" -> {

                }
                "stories" -> {

                }
            }
            loading.value = 0
        }
    }

    fun getCharDaSerie(id: String, origin: String) {
        loading.value = 1
        viewModelScope.launch {
            when (origin) {
                "char" -> {
                    result.value = service.getCharSeriesRepo(
                        id,
                        hash.ts,
                        hash.publicKey,
                        hash.getKey()
                    ).data.results
                }
                "comic" -> {

                }
                "series" -> {

                }
                "stories" -> {

                }
            }

            loading.value = 0
        }
    }

    fun getCharDaStories(id: String, origin: String) {
        loading.value = 1
        viewModelScope.launch {
            when (origin) {
                "char" -> {
                    result.value = service.getCharStoriesRepo(
                        id,
                        hash.ts,
                        hash.publicKey,
                        hash.getKey()
                    ).data.results
                }
                "comic" -> {

                }
                "series" -> {

                }
                "stories" -> {

                }
            }
            loading.value = 0
        }
    }


    //room tabela results
    fun addResults(result: GenericResults) {
        viewModelScope.launch {
            dataBase.addResults(result)
        }
    }

    //room tabela favoritos
    fun addFavorito(result: GenericResults) {
        viewModelScope.launch {
            dataBase.addFavorito(Favorito(result.id, result, "char"))
        }
    }
}