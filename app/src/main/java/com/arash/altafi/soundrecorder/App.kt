package com.arash.altafi.soundrecorder

import android.app.Application
import com.arash.altafi.soundrecorder.di.DaggerMainComponent
import com.arash.altafi.soundrecorder.di.MainComponent
import timber.log.Timber


open class App: Application() {

    val mainComponent: MainComponent by lazy {
        DaggerMainComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

    }
}