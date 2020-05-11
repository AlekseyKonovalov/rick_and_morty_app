package com.example.rickandmortyapp.core.di

import android.content.Context
import com.example.rickandmortyapp.BuildConfig
import com.example.rickandmortyapp.core.util.ResourceProvider
import com.example.rickandmortyapp.data.db.AppDatabase
import com.example.rickandmortyapp.data.db.DatabaseProvider
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.navigation.CharacterDetailNavigator
import com.example.rickandmortyapp.feature.splash.splash_flow.navigation.SplashNavigator
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.UpdateCharacterBus
import com.example.rickandmortyapp.feature.characters.characters_flow.navigation.CharactersNavigator
import com.example.rickandmortyapp.navigation.*
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
        bind<CharactersNavigator>().singleton()
        bind<CharacterDetailNavigator>().singleton()

        bind<UpdateCharacterBus>().singleton()
    }
}