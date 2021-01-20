package com.e.jarvis.database.db

import com.e.jarvis.database.dao.FavoritoDao
import com.e.jarvis.models.modelsfavoritos.Favorito

class FavoritoDb(val favoritoDao: FavoritoDao){

    //tabela favoritos:
    suspend fun addFavorito(favorito: Favorito) =
        favoritoDao.addFavorito(favorito)

    //pega chars favoritos
    suspend fun getAllCharsFavoritos() = favoritoDao.getAllCharsFavoritos("char")

    //pega comics favoritos
    suspend fun getAllComicsFavoritos() = favoritoDao.getAllComicsFavoritos("comic")

    //pega series favoritos
    suspend fun getAllSeriesFavoritos() = favoritoDao.getAllSeriesFavoritos("serie")

    //pega stories favoritos
    suspend fun getAllStoriesFavoritos() = favoritoDao.getAllStoriesFavoritos("storie")

    //deleta favorito selecionado com o long click
    suspend fun deleteFavorito(favorito: Favorito) = favoritoDao.deleteFavorito(favorito)
}