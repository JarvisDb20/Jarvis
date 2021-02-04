package com.e.jarvis.database.dao

import androidx.room.*
import com.e.jarvis.models.modelsfavoritos.Favorito
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface FavoritoDao {

    @Query("SELECT * FROM favoritos")
    fun getAll(): Flow<List<Favorito>>

    @Query("SELECT * FROM favoritos WHERE id= :id")
    fun getFavorito(id: String): Flow<Favorito>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorito(aChar: Favorito)

    @Delete
    suspend fun deleteFavorito(favorito: Favorito)

    @Query("DELETE FROM favoritos")
    suspend fun deleteAllFavorito()

    fun getFavoritoDistinct(id: String) = getFavorito(id).distinctUntilChanged()

}
