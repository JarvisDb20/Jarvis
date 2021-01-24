package com.e.jarvis.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.jarvis.database.dao.FavoritoDao
import com.e.jarvis.database.dao.QuizDao
import com.e.jarvis.database.dao.MarvelDao
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.modelsQuiz.Quiz
import com.e.jarvis.models.modelsfavoritos.Favorito

@Database(
    entities = [GenericResults::class, Favorito::class,Quiz::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun resultsDao(): MarvelDao
    abstract fun favoritosDao(): FavoritoDao
    abstract fun quizDao(): QuizDao

}