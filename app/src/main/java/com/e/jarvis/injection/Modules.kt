package com.e.jarvis.injection

import android.app.Application
import androidx.room.Room
import com.e.jarvis.dao.FavoritoDao
import com.e.jarvis.dao.QuizDao
import com.e.jarvis.dao.ResultsDao
import com.e.jarvis.database.AppDatabase
import com.e.jarvis.repository.QuizRepository
import com.e.jarvis.repository.RepositoryDataBase
import com.e.jarvis.repository.Service
import com.e.jarvis.ui.exibe.chars.ExibeCharViewModel
import com.e.jarvis.ui.exibe.comic.ExibeComicsViewModel
import com.e.jarvis.ui.exibe.serie.ExibeSerieViewModel
import com.e.jarvis.ui.exibe.story.ExibeStoriesViewModel
import com.e.jarvis.ui.home.HomeViewModel
import com.e.jarvis.ui.question.QuestionFragment
import com.e.jarvis.ui.question.QuestionViewModel
import com.e.jarvis.ui.quiz.QuizViewModel
import com.e.jarvis.ui.search.PesquisaViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val roomRepositoryModule = module {

    fun provideUserRepository(
        dao: ResultsDao,
        favoritoDao: FavoritoDao
    ): RepositoryDataBase {
        return RepositoryDataBase(dao, favoritoDao)
    }

    //devolve uma instancia unica para ser utilizada onde é dependencia
    single { provideUserRepository(get(), get()) }


    fun provideQuizRepository(
        dao: QuizDao
    ): QuizRepository {
        return QuizRepository(dao)
    }
    single { provideQuizRepository(get()) }

}

val roomDataBaseModule = module {

    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "javisdb")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: AppDatabase): ResultsDao {
        return database.resultsDao()
    }

    fun provideFavoritosDao(database: AppDatabase): FavoritoDao {
        return database.favoritosDao()
    }

    fun provideQuizDao(database: AppDatabase): QuizDao {
        return database.QuizDao()
    }

    //devolve uma instancia unica
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
    single { provideFavoritosDao(get()) }
    single { provideQuizDao(get()) }


}


val viewModelModule = module {

    viewModel {
        ExibeCharViewModel(get(), get())
    }

    viewModel {
        ExibeComicsViewModel(get(), get())
    }

    viewModel {
        ExibeSerieViewModel(get(), get())
    }

    viewModel {
        ExibeStoriesViewModel(get(), get())
    }

    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel {
        PesquisaViewModel(get())
    }

    viewModel {
        QuizViewModel(get())
    }

    viewModel {
        QuestionViewModel(get())
    }

}


val retrofitModule = module {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideAPI(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    single { provideRetrofit() }
    single { provideAPI(get()) }

}