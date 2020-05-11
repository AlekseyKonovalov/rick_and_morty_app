package com.example.rickandmortyapp.data.db

import android.content.Context
import androidx.room.Room
import javax.inject.Inject
import javax.inject.Provider

class DatabaseProvider @Inject constructor(private val context: Context) :
    Provider<AppDatabase> {

    override fun get(): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()

    companion object {
        private const val DB_NAME = "db_rickmorty"
    }
}