package com.e.jarvis.dao

import androidx.room.*
import com.e.jarvis.models.favoritosgenerics.FavoritoGenerics

@Dao
interface FavoritoDao {

        @Query("SELECT * FROM favoritos")
        suspend fun getAllFavoritos(): List<FavoritoGenerics>

        @Query("SELECT * FROM favoritos WHERE id= :id")
        suspend fun getFavorito(id: Int) : List<FavoritoGenerics>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addFavorito(favorito: FavoritoGenerics)

        @Delete
        suspend fun deleteFavorito(favorito: FavoritoGenerics)

    }