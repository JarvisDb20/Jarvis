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
                            ResponseWrapper.Status.ERROR -> listCharsFavoritos.value = ResponseWrapper(it.status, null, it.error)
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
                            ResponseWrapper.Status.ERROR -> listComicsFavoritos.value = ResponseWrapper(it.status, null, it.error)
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
                            ResponseWrapper.Status.ERROR -> listSeriesFavoritos.value = ResponseWrapper(it.status, null, it.error)
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
                            ResponseWrapper.Status.ERROR -> listStoriesFavoritos.value = ResponseWrapper(it.status, null, it.error)
                            else -> {
                                listStoriesFavoritos.value = ResponseWrapper(it.status, it.data)
                            }
                        }
                        loading.value = View.INVISIBLE
                    }
        }
    }


}


//nao usa a view so pega o valor
//tipo enum


//    //comics da tabela favoritos
//    fun getAllComicsFavoritos() {
//        viewModelScope.launch {
////            listComicsFavoritos.value = dataBase.getAllComicsFavoritos()
//        }
//
//    }
//
//    //series da tabela favoritos
//    fun getAllSeriesFavoritos() {
//        viewModelScope.launch {
////            listSeriesFavoritos.value = dataBase.getAllSeriesFavoritos()
//        }
//
//    }
//
//    //stories da tabela favoritos
//    fun getAllStoriesFavoritos() {
//        viewModelScope.launch {
//            //listStoriesFavoritos.value = dataBase.getAllStoriesFavoritos()
//        }
//
//    }

//
//    fun deleteFavoritoChar(favorito: Favorito) {
//        viewModelScope.launch {
//            dataBase.deleteFavorito(favorito)
//            //chamando essa função, ela atualiza
//            getAllCharsFavoritos()
//        }
//    }
//
//    fun deleteFavoritoComic(favorito: Favorito) {
//        viewModelScope.launch {
//            dataBase.deleteFavorito(favorito)
//            //chamando essa função, ela atualiza
//            getAllComicsFavoritos()
//        }
//    }
//
//    fun deleteFavoritoSerie(favorito: Favorito) {
//        viewModelScope.launch {
//            dataBase.deleteFavorito(favorito)
//            //chamando essa função, ela atualiza
//            getAllSeriesFavoritos()
//        }
//    }
//
//    fun deleteFavoritoStorie(favorito: Favorito) {
//        viewModelScope.launch {
//            dataBase.deleteFavorito(favorito)
//            //chamando essa função, ela atualiza
//            getAllStoriesFavoritos()
//        }
//    }




