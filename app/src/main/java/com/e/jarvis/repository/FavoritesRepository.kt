package com.e.jarvis.repository

import android.util.Log
import android.widget.Toast
import com.e.jarvis.database.db.FavoritoDb
import com.e.jarvis.models.ResponseWrapper
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.modelsfavoritos.Favorito
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

//favoritos: get all, delete por id, add

//o repository é responsavel por passar as informações para a view model
//tratamento de erro tem que ser feito aqui e não na viewmodel


class FavoritesRepository(
    private val favoritoDb: FavoritoDb,
    private val repository: FirebaseRepository
) {

    fun getAllCharsFavoritos(): Flow<ResponseWrapper<List<Favorito>>> = flow {
        var retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.LOADING, null)
        emit(retorno)
        favoritoDb.getAllCharsFavoritos()
            .catch { error ->
                retorno = ResponseWrapper<List<Favorito>>(
                    ResponseWrapper.Status.ERROR,
                    null,
                    error.message
                )
                emit(retorno)
            }
            .collect { res ->
                if (res.isEmpty()) {
                    retorno = ResponseWrapper<List<Favorito>>(
                        ResponseWrapper.Status.ERROR,
                        null,
                        "Not found - empty list"
                    )
                } else {
                    retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.SUCCESS, res)
                }
                emit(retorno)
            }
    }.flowOn(Dispatchers.Default)

    fun getAllComicsFavoritos(): Flow<ResponseWrapper<List<Favorito>>> = flow {
        var retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.LOADING, null)
        emit(retorno)
        favoritoDb.getAllComicsFavoritos()
            .catch { error ->
                retorno = ResponseWrapper<List<Favorito>>(
                    ResponseWrapper.Status.ERROR,
                    null,
                    error.message
                )
                emit(retorno)
            }
            .collect { res ->
                if (res.isEmpty()) {
                    retorno = ResponseWrapper<List<Favorito>>(
                        ResponseWrapper.Status.ERROR,
                        null,
                        "Not found - empty list"
                    )
                } else {
                    retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.SUCCESS, res)
                }
                emit(retorno)
            }
    }.flowOn(Dispatchers.Default)

    fun getAllSeriesFavoritos(): Flow<ResponseWrapper<List<Favorito>>> = flow {
        var retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.LOADING, null)
        emit(retorno)
        favoritoDb.getAllSeriesFavoritos()
            .catch { error ->
                retorno = ResponseWrapper<List<Favorito>>(
                    ResponseWrapper.Status.ERROR,
                    null,
                    error.message
                )
                emit(retorno)
            }
            .collect { res ->
                if (res.isEmpty()) {
                    retorno = ResponseWrapper<List<Favorito>>(
                        ResponseWrapper.Status.ERROR,
                        null,
                        "Not found - empty list"
                    )
                } else {
                    retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.SUCCESS, res)
                }
                emit(retorno)
            }
    }.flowOn(Dispatchers.Default)

    fun getAllStoriesFavoritos(): Flow<ResponseWrapper<List<Favorito>>> = flow {
        var retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.LOADING, null)
        emit(retorno)
        favoritoDb.getAllStoriesFavoritos()
            .catch { error ->
                retorno = ResponseWrapper<List<Favorito>>(
                    ResponseWrapper.Status.ERROR,
                    null,
                    error.message
                )
                emit(retorno)
            }
            .collect { res ->
                if (res.isEmpty()) {
                    retorno = ResponseWrapper<List<Favorito>>(
                        ResponseWrapper.Status.ERROR,
                        null,
                        "Not found - empty list"
                    )
                } else {
                    retorno = ResponseWrapper<List<Favorito>>(ResponseWrapper.Status.SUCCESS, res)
                }
                emit(retorno)
            }
    }.flowOn(Dispatchers.Default)

    suspend fun addFavorito(favorito: Favorito) {
        favoritoDb.addFavorito(favorito)
        repository.addFavorite(favorito).collect()
    }

    suspend fun deleteFavorito(favorito: Favorito) {
        favoritoDb.deleteFavorito(favorito)
        repository.removeFavorite(favorito).collect()
    }


}
