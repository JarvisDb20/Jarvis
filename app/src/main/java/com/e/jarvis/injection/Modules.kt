package com.e.jarvis.injection

import android.app.Application
import androidx.room.Room
import com.e.jarvis.dao.ResultsDao
import com.e.jarvis.database.AppDatabase
import com.e.jarvis.repository.RepositoryDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomRepository = module {

    fun provideUserRepository(dao : ResultsDao) : RepositoryDataBase {
        return RepositoryDataBase(dao)
    }

    //devolve uma instancia unica para ser utilizada onde é dependencia
    single { provideUserRepository(get()) }
}

val roomDataBase = module {

    fun provideDataBase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "resultsdb")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: AppDatabase): ResultsDao {
        return database.resultsDao()
    }

    //devolve uma instancia unica
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }


}