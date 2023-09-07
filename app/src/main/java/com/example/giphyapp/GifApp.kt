package com.example.giphyapp

import android.app.Application
import com.example.giphyapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GifApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }
    }

}