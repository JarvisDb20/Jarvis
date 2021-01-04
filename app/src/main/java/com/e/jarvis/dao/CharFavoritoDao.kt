package com.e.jarvis.dao

import androidx.room.*
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.modelsfavoritos.CharFavorito

@Dao
interface CharFavoritoDao {

    @Query("SELECT * FROM charsfavoritos")
    suspend fun getAllCharsFavoritos(): List<CharFavorito>

    @Query("SELECT * FROM charsfavoritos WHERE id= :id")
    suspend fun getCharFavorito(id: Int): CharFavorito

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharFavorito(char: CharFavorito)

    @Delete
    suspend fun deleteCharFavorito(charFavorito: CharFavorito)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComicFavorito(comic: CharFavorito)


}