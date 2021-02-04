package com.e.jarvis.ui.favorites


import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.repository.FavoritesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritosViewModel(private val repoFavoritos: FavoritesRepository) : ViewModel() {

    val loading = MutableLiveData<Int>()

    val listCharsFavoritos = MutableLiveData<List<Favorito>>()
    val listComicsFavoritos = MutableLiveData<List<Favorito>>()
    val listSeriesFavoritos = MutableLiveData<List<Favorito>>()
    val listStoriesFavoritos = MutableLiveData<List<Favorito>>()

    fun deleteFavorito(favorito: Favorito) {
        viewModelScope.launch {
            repoFavoritos.deleteFavorito(favorito)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            repoFavoritos.getAll()
                .collect { fav ->
                    when (fav.status) {
                        ResponseWrapper.Status.LOADING -> loading.value = View.VISIBLE
                        else -> {
                            val char: ArrayList<Favorito> = arrayListOf()
                            val stories: ArrayList<Favorito> = arrayListOf()
                            val comics: ArrayList<Favorito> = arrayListOf()
                            val series: ArrayList<Favorito> = arrayListOf()
                            fav.data?.forEach {
                                when (it.tipoDoResult) {
                                    "char" -> char.add(it)
                                    "comic" -> comics.add(it)
                                    "series" -> series.add(it)
                                    "stories" -> stories.add(it)
                                }
                            }
                            listCharsFavoritos.value = char
                            listComicsFavoritos.value = comics
                            listSeriesFavoritos.value = series
                            listStoriesFavoritos.value = stories
                        }
                    }
                    loading.value = View.INVISIBLE
                }
        }
    }

    fun syncDb() {
        viewModelScope.launch {
            repoFavoritos.syncDb()
        }
    }
}





