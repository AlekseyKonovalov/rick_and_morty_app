package com.example.rickandmortyapp.data.db.dao

import androidx.room.*
import com.example.rickandmortyapp.data.db.entity.CharacterDbEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

private const val TABLE_NAME = "CharacterDbEntity"

@Dao
interface CharacterDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Observable<List<CharacterDbEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id LIKE :id")
    fun getById(id: Int): Single<List<CharacterDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CharacterDbEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<CharacterDbEntity>): Completable

    @Delete
    fun delete(item: CharacterDbEntity): Completable

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll(): Completable

}


