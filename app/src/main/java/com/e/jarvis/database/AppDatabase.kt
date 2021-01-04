package com.e.jarvis.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.jarvis.dao.CharFavoritoDao
import com.e.jarvis.dao.ResultsDao
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.modelsfavoritos.CharFavorito

@Database(
    entities = [GenericResults::class, CharFavorito::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun resultsDao(): ResultsDao
    abstract fun favoritosDao(): CharFavoritoDao

}