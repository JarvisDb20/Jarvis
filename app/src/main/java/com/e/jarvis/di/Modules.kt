package com.e.jarvis.di

import android.app.Application
import androidx.room.Room
import com.e.jarvis.database.AppDatabase
import com.e.jarvis.database.dao.*
import com.e.jarvis.database.db.FavoritoDb
import com.e.jarvis.database.db.MarvelDb
import com.e.jarvis.database.db.QuizDb
import com.e.jarvis.models.ResponseHandler
import com.e.jarvis.repository.FirebaseRepository
import com.e.jarvis.repository.MarvelRepository
import com.e.jarvis.retrofit.MarvelService
import com.e.jarvis.ui.SharedViewModel
import com.e.jarvis.ui.cadastro.CadastroViewModel
import com.e.jarvis.ui.exibe.ExibeViewModel
import com.e.jarvis.ui.favorites.FavoritosViewModel
import com.e.jarvis.ui.home.HomeViewModel
import com.e.jarvis.ui.login.LoginViewModel
import com.e.jarvis.ui.logout.LogoutViewModel
import com.e.jarvis.ui.perguntas.QuestionViewModel
import com.e.jarvis.ui.pesquisa.PesquisaViewModel
import com.e.jarvis.ui.quiz.QuizViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val roomDataBaseModule = module {

    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "javisdb")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideMarvelDao(database: AppDatabase): MarvelDao = database.resultsDao()
    fun provideMarvelDb(dao: MarvelDao): MarvelDb = MarvelDb(dao)

    fun provideFavoritosDao(database: AppDatabase): FavoritoDao = database.favoritosDao()
    fun provideFavoritoDb(dao: FavoritoDao): FavoritoDb = FavoritoDb(dao)

    fun provideQuizDao(database: AppDatabase): QuizDao = database.quizDao()
    fun provideQuizDb(dao: QuizDao): QuizDb = QuizDb(dao)


    single { provideDataBase(androidApplication()) }

    single { provideMarvelDao(get()) }
    single { provideMarvelDb(get()) }

    single { provideFavoritosDao(get()) }
    single { provideFavoritoDb(get()) }

    single { provideQuizDao(get()) }
    single { provideQuizDb(get()) }


}

val repositoryModule = module {
    single { MarvelRepository(get(), get()) }
    single { FirebaseRepository(get(), get(), get(), get()) }
}

val viewModelModule = module {
    viewModel { ExibeViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { PesquisaViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { QuestionViewModel(get()) }
    viewModel { SharedViewModel(get()) }
    viewModel { FavoritosViewModel(get()) }
    viewModel { CadastroViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { LogoutViewModel(get()) }
}


val appModule = module {
    factory { ResponseHandler() }
    single { Firebase.auth }
    single { Firebase.storage }
    single { Firebase.firestore }
}

val retrofitModule = module {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideAPI(retrofit: Retrofit): MarvelService = retrofit.create(MarvelService::class.java)

    single { provideRetrofit() }
    single { provideAPI(get()) }

}