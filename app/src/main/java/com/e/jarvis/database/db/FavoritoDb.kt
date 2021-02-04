package com.e.jarvis.database.db

import com.e.jarvis.database.dao.FavoritoDao
import com.e.jarvis.models.modelsfavoritos.Favorito


//chamadas relacionadas a favoritos do repositório antigo ficam aqui
class FavoritoDb(val favoritoDao: FavoritoDao) {

    //tabela favoritos:
    suspend fun addFavorito(favorito: Favorito) = favoritoDao.addFavorito(favorito)

    //pega stories favoritos
    fun getAll() = favoritoDao.getAll()

    //deleta favorito selecionado com o long click
    suspend fun deleteFavorito(favorito: Favorito) = favoritoDao.deleteFavorito(favorito)

    suspend fun deleteAllFavorito() = favoritoDao.deleteAllFavorito()

}