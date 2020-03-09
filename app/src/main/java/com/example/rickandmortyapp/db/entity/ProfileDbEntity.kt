package com.example.rickandmortyapp.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id", "accountEmail"])
data class ProfileDbEntity(
    @NonNull
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    @NonNull
    val accountEmail: String
)
