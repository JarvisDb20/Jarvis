package com.e.jarvis.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.e.jarvis.dao.ResultsDao
import com.e.jarvis.models.generics.GenericResults
import com.e.jarvis.models.utils.ConverterType

@Database (entities = [GenericResults::class], version = 1, exportSchema = false)
@TypeConverters(ConverterType::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun resultsDao() : ResultsDao

}