package com.e.jarvis.database.db

import com.e.jarvis.database.dao.FavoritoDao
import com.e.jarvis.models.modelsfavoritos.Favorito

//chamadas relacionadas a favoritos do repositório antigo ficam aqui
class FavoritoDb(val favoritoDao: FavoritoDao) {

    //tabela favoritos:
    suspend fun addFavorito(favorito: Favorito) =
            favoritoDao.addFavorito(favorito)

    //pega chars favoritos
    fun getAllCharsFavoritos() = favoritoDao.getAllCharsFavoritos("char")

    //pega comics favoritos
    fun getAllComicsFavoritos() = favoritoDao.getAllComicsFavoritos("comic")

    //pega series favoritos
    fun getAllSeriesFavoritos() = favoritoDao.getAllSeriesFavoritos("series")

    //pega stories favoritos
    fun getAllStoriesFavoritos() = favoritoDao.getAllStoriesFavoritos("stories")

    //deleta favorito selecionado com o long click
    suspend fun deleteFavorito(favorito: Favorito) = favoritoDao.deleteFavorito(favorito)
}