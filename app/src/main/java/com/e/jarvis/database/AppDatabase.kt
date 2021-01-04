package com.e.jarvis.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.jarvis.dao.FavoritoDao
import com.e.jarvis.dao.ResultsDao
import com.e.jarvis.models.favoritosgenerics.FavoritoGenerics
import com.e.jarvis.models.generics.GenericResults

@Database (entities = [GenericResults::class, FavoritoGenerics::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun resultsDao() : ResultsDao
    abstract fun favoritosDao(): FavoritoDao

}