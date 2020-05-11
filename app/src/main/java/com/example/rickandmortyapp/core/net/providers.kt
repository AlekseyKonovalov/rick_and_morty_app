package com.example.rickandmortyapp.core.net

import com.example.rickandmortyapp.core.di.BaseUrl
import com.example.rickandmortyapp.core.di.NetScheduler
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider


class RetrofitProvider @Inject constructor(
    @BaseUrl private val baseUrl: String,
    private val client: OkHttpClient,
    @NetScheduler private val scheduler: Scheduler
) : Provider<Retrofit> {

    override fun get(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
            .client(client)
            .build()
    }

}

class OkHTTPClientProvider @Inject constructor() : Provider<OkHttpClient> {
    override fun get(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    companion object {
        private const val TIME_OUT = 120L
    }

}