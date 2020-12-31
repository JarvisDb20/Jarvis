package com.e.jarvis.app

import android.app.Application
import com.e.jarvis.injection.roomDataBase
import com.e.jarvis.injection.roomRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@App)

            modules(
                roomRepository,
                roomDataBase
            )
        }
    }
}