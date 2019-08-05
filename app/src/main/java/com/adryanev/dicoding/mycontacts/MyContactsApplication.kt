package com.adryanev.dicoding.mycontacts

import android.app.Application
import timber.log.Timber

class MyContactsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

    }
}