package com.e.jarvis.dao

import androidx.room.*
import com.e.jarvis.models.modelsfavoritos.Favorito

@Dao
interface FavoritoDao {

    @Query("SELECT * FROM favoritos")
    suspend fun getAllFavoritos(): List<Favorito>

    @Query("SELECT * FROM favoritos WHERE id= :id")
    suspend fun getFavorito(id: String): Favorito

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorito(aChar: Favorito)

    @Delete
    suspend fun deleteFavorito(favorito: Favorito)





}