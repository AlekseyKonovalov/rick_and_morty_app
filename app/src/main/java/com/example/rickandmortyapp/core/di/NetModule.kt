package com.example.rickandmortyapp.core.di

import com.example.rickandmortyapp.core.net.OkHTTPClientProvider
import com.example.rickandmortyapp.core.net.RetrofitProvider
import com.example.rickandmortyapp.data.network.RickMortyApi
import com.example.rickandmortyapp.data.network.RickMortyApiProvider
import com.example.rickandmortyapp.data.repository.CharactersRepositoryImpl
import com.example.rickandmortyapp.domain.gateway.CharactersRepository
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.config.Module
import toothpick.ktp.binding.bind

class NetModule : Module() {
    init {
        bind<OkHttpClient>().toProvider(OkHTTPClientProvider::class.java).singleton()
        bind<Scheduler>().withName(NetScheduler::class.java).toInstance(Schedulers.io())
        bind<Retrofit>().toProvider(RetrofitProvider::class.java).singleton()
        bind<RickMortyApi>().toProvider(RickMortyApiProvider::class.java).singleton()
        bind<CharactersRepository>().to(CharactersRepositoryImpl::class.java).singleton()
    }
}