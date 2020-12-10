package com.example.covid19tracker

import android.app.Application
import com.facebook.stetho.Stetho

class CovidApplication: Application() {

    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(applicationContext)
        //Stetho.initializeWithDefaults(this)
    }
}