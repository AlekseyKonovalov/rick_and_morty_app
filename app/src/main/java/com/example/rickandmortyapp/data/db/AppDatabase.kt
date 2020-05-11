package com.example.rickandmortyapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortyapp.data.db.dao.CharacterDao
import com.example.rickandmortyapp.data.db.dao.EpisodeDao
import com.example.rickandmortyapp.data.db.dao.LocationDao
import com.example.rickandmortyapp.data.db.dao.ProfileDao
import com.example.rickandmortyapp.data.db.entity.CharacterDbEntity
import com.example.rickandmortyapp.data.db.entity.EpisodeDbEntity
import com.example.rickandmortyapp.data.db.entity.LocationDbEntity
import com.example.rickandmortyapp.data.db.entity.ProfileDbEntity

@Database(
    entities = [CharacterDbEntity::class,
        EpisodeDbEntity::class,
        LocationDbEntity::class,
        ProfileDbEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao
    abstract fun profileDao(): ProfileDao
}