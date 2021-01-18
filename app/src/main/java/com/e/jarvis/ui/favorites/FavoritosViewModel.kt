package com.e.jarvis.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.jarvis.injection.roomRepositoryModule
import com.e.jarvis.models.modelsfavoritos.Favorito
import com.e.jarvis.repository.RepositoryDataBase
import kotlinx.coroutines.launch

class FavoritosViewModel(private val dataBase: RepositoryDataBase) : ViewModel() {

    val listCharsFavoritos = MutableLiveData<List<Favorito>>()
    val listComicsFavoritos = MutableLiveData<List<Favorito>>()
    val listSeriesFavoritos = MutableLiveData<List<Favorito>>()
    val listStoriesFavoritos = MutableLiveData<List<Favorito>>()

    //chars da tabela favoritos
    fun getAllCharsFavoritos(){
        viewModelScope.launch {
            listCharsFavoritos.value = dataBase.getAllCharsFavoritos()
        }
    }

    //comics da tabela favoritos
    fun getAllComicsFavoritos(){
        viewModelScope.launch {
            listComicsFavoritos.value = dataBase.getAllComicsFavoritos()
        }

    }

    //series da tabela favoritos
    fun getAllSeriesFavoritos(){
        viewModelScope.launch {
            listSeriesFavoritos.value = dataBase.getAllSeriesFavoritos()
        }

    }

    //stories da tabela favoritos
    fun getAllStoriesFavoritos(){
        viewModelScope.launch {
            listStoriesFavoritos.value = dataBase.getAllStoriesFavoritos()
        }

    }


    fun deleteFavoritoChar(favorito: Favorito) {
        viewModelScope.launch {
            dataBase.deleteFavorito(favorito)
            //chamando essa função, ela atualiza
            getAllCharsFavoritos()
        }
    }

    fun deleteFavoritoComic(favorito: Favorito) {
        viewModelScope.launch {
            dataBase.deleteFavorito(favorito)
            //chamando essa função, ela atualiza
            getAllComicsFavoritos()
        }
    }

    fun deleteFavoritoSerie(favorito: Favorito) {
        viewModelScope.launch {
            dataBase.deleteFavorito(favorito)
            //chamando essa função, ela atualiza
            getAllSeriesFavoritos()
        }
    }

    fun deleteFavoritoStorie(favorito: Favorito) {
        viewModelScope.launch {
            dataBase.deleteFavorito(favorito)
            //chamando essa função, ela atualiza
            getAllStoriesFavoritos()
        }
    }




}
