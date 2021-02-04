package com.e.jarvis.repository

import com.e.jarvis.database.db.FavoritoDb
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.modelsfavoritos.Favorito
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class FavoritesRepository(
    private val favoritoDb: FavoritoDb,
    private val repository: FirebaseRepository
) {

    fun getAll() = flow {
        var retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.LOADING, null)
        emit(retorno)
        favoritoDb.getAll()
            .catch { error ->
                retorno = ResponseWrapper<List<Favorito>>(
                    ResponseWrapper.Status.ERROR,
                    null,
                    error.message
                )
                emit(retorno)
            }
            .collect { res ->
                retorno = if (res.isEmpty()) {
                    ResponseWrapper<List<Favorito>>(
                        ResponseWrapper.Status.ERROR,
                        null,
                        "Not found - empty list"
                    )
                } else {
                    ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.SUCCESS, res)
                }
                emit(retorno)
            }
    }.flowOn(Dispatchers.Default)

    suspend fun addFavorito(favorito: Favorito, firestore: Boolean = true) {
        favoritoDb.addFavorito(favorito)
        if (firestore)
            repository.addFavorite(favorito).collect()
    }

    suspend fun deleteFavorito(favorito: Favorito) {
        favoritoDb.deleteFavorito(favorito)
        repository.removeFavorite(favorito).collect()
    }

    suspend fun syncDb() {
        favoritoDb.deleteAllFavorito()
        repository.getFavorite().collect { fav ->
            if (fav.status == ResponseWrapper.Status.SUCCESS) {
                fav.data?.forEach {
                    addFavorito(it, false)
                }
            }
        }
    }
}
