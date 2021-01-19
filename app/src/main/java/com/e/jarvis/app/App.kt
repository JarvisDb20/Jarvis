package com.e.jarvis.app

import android.app.Application
import com.e.jarvis.di.retrofitModule
//import com.e.jarvis.injection.retrofitModule
import com.e.jarvis.di.roomDataBaseModule
import com.e.jarvis.di.roomRepositoryModule
import com.e.jarvis.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@App)

            modules(
                    roomRepositoryModule,
                    roomDataBaseModule,
                    viewModelModule,
                    retrofitModule
            )
        }
    }
}