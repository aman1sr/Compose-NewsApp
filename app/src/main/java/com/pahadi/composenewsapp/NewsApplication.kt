package com.pahadi.composenewsapp

import android.app.Application
//import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {  // todo: Configuration.Provider : wrt workManager
    override fun onCreate() {
        super.onCreate()
    }
}