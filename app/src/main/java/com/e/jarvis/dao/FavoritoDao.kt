package com.e.jarvis.dao

import androidx.room.*
import com.e.jarvis.models.modelsfavoritos.Favorito

@Dao
interface FavoritoDao {

    @Query("SELECT * FROM favoritos WHERE tipoDoResult= :tipo ")
    suspend fun getAllCharsFavoritos(tipo : String): List<Favorito>

    @Query("SELECT * FROM favoritos WHERE tipoDoResult= :tipo ")
    suspend fun getAllComicsFavoritos(tipo : String): List<Favorito>

    @Query("SELECT * FROM favoritos WHERE tipoDoResult= :tipo ")
    suspend fun getAllSeriesFavoritos(tipo : String): List<Favorito>

    @Query("SELECT * FROM favoritos WHERE tipoDoResult= :tipo ")
    suspend fun getAllStoriesFavoritos(tipo : String): List<Favorito>






    @Query("SELECT * FROM favoritos WHERE id= :id")
    suspend fun getFavorito(id: String): Favorito

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorito(aChar: Favorito)

    @Delete
    suspend fun deleteFavorito(favorito: Favorito)


}