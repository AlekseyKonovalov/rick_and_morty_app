package com.example.rickandmortyapp.data.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterDbEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    val isFavorite: Boolean,
    val rating: String
)