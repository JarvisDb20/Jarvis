package com.e.jarvis.app

import android.app.Application
import com.e.jarvis.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                roomDataBaseModule,
                viewModelModule,
                retrofitModule,
                repositoryModule,
<<<<<<< HEAD
                appModule,
=======
                    favoritosModule
>>>>>>> 69e1fe294b9ed5d434c17e1eb0f2afdc84073051
            )
        }
    }
}