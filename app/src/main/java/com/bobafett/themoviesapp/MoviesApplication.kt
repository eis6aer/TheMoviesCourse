package com.bobafett.themoviesapp

import android.app.Application
import android.util.Log

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyApp", "onAppCreate")
    }
}