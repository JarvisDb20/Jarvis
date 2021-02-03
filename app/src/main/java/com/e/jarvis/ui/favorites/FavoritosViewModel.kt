package com.e.jarvis.ui.favorites


import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.database.db.FavoritoDb

import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.repository.FavoritesRepository
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class FavoritosViewModel(private val repoFavoritos: FavoritesRepository) : ViewModel() {

    val loading = MutableLiveData<Int>()

    val listCharsFavoritos = MutableLiveData<ResponseWrapper<List<Favorito>>>()
    val listComicsFavoritos = MutableLiveData<ResponseWrapper<List<Favorito>>>()
    val listSeriesFavoritos = MutableLiveData<ResponseWrapper<List<Favorito>>>()
    val listStoriesFavoritos = MutableLiveData<ResponseWrapper<List<Favorito>>>()

    fun getAllCharsFavoritos() {
        viewModelScope.launch {
            repoFavoritos.getAllCharsFavoritos()
                .collect {
                    when (it.status) {
                        ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                        ResponseWrapper.Status.ERROR -> listCharsFavoritos.value =
                            ResponseWrapper(it.status, null, it.error)
                        else -> {
                            listCharsFavoritos.value = ResponseWrapper(it.status, it.data)
                        }
                    }
                    loading.value = View.INVISIBLE
                }
        }
    }

    fun getAllComicsFavoritos() {
        viewModelScope.launch {
            repoFavoritos.getAllComicsFavoritos()
                .collect {
                    when (it.status) {
                        ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                        ResponseWrapper.Status.ERROR -> listComicsFavoritos.value =
                            ResponseWrapper(it.status, null, it.error)
                        else -> {
                            listComicsFavoritos.value = ResponseWrapper(it.status, it.data)
                        }
                    }
                    loading.value = View.INVISIBLE
                }
        }
    }

    fun getAllSeriesFavoritos() {
        viewModelScope.launch {
            repoFavoritos.getAllSeriesFavoritos()
                .collect {
                    when (it.status) {
                        ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                        ResponseWrapper.Status.ERROR -> listSeriesFavoritos.value =
                            ResponseWrapper(it.status, null, it.error)
                        else -> {
                            listSeriesFavoritos.value = ResponseWrapper(it.status, it.data)
                        }
                    }
                    loading.value = View.INVISIBLE
                }
        }
    }

    fun getAllStoriesFavoritos() {
        viewModelScope.launch {
            repoFavoritos.getAllStoriesFavoritos()
                .collect {
                    when (it.status) {
                        ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                        ResponseWrapper.Status.ERROR -> listStoriesFavoritos.value =
                            ResponseWrapper(it.status, null, it.error)
                        else -> {
                            listStoriesFavoritos.value = ResponseWrapper(it.status, it.data)
                        }
                    }
                    loading.value = View.INVISIBLE
                }
        }
    }

    fun deleteFavorito(favorito: Favorito) {
        viewModelScope.launch {
            repoFavoritos.deleteFavorito(favorito)
        }

//        when (favorito.tipoDoResult) {
//            "char" -> {
//                getAllCharsFavoritos()
//            }
//            "comic" -> {
//                getAllComicsFavoritos()
//            }
//            "serie" -> {
//                getAllSeriesFavoritos()
//            }
//            "storie" -> {
//                getAllStoriesFavoritos()
//            }
//        }

    }


}





