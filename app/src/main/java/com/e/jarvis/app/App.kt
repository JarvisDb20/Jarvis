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
                appModule,
            )
        }
    }
}