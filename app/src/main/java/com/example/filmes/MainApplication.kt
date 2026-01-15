package com.example.filmes

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@MainApplication)
            modules(
                com.example.filmes.di.appModule,
                com.example.filmes.di.viewModelModule
            )
        }
    }
}
