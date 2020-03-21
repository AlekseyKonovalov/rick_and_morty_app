package com.example.rickandmortyapp.core.di

import android.content.Context
import com.example.rickandmortyapp.BuildConfig
import com.example.rickandmortyapp.navigation.AppNavigator
import com.example.rickandmortyapp.core.BaseUrl
import com.example.rickandmortyapp.core.ResourceProvider
import com.example.rickandmortyapp.db.AppDatabase
import com.example.rickandmortyapp.db.DatabaseProvider
import com.example.rickandmortyapp.navigation.SplashNavigator
import com.example.rickandmortyapp.navigation.TabContainerNavigator
import toothpick.config.Module
import toothpick.ktp.binding.bind

class ApplicationModule(context: Context) : Module() {
    init {
        bind<Context>().toInstance(context)
        bind<ResourceProvider>().singleton()
        bind<String>().withName(BaseUrl::class.java).toInstance(BuildConfig.BASE_URL)
        bind<AppDatabase>().toProvider(DatabaseProvider::class.java).singleton()
        bind<AppNavigator>().singleton()
        bind<SplashNavigator>().singleton()
        bind<TabContainerNavigator>().singleton()
    }
}