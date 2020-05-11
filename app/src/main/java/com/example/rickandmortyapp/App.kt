package com.example.rickandmortyapp

import android.app.Application
import com.example.rickandmortyapp.core.di.ApplicationModule
import com.example.rickandmortyapp.core.di.Scopes
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.ktp.KTP

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        initToothpick()
        openAppScope()
    }

    private fun openAppScope() {
        KTP.TP.openScope(Scopes.APPLICATION_SCOPE)
            .installModules(ApplicationModule(this))
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction())
        }
    }
}