package com.example.rickandmortyapp.data.db.dao

import androidx.room.*
import com.example.rickandmortyapp.data.db.entity.EpisodeDbEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

private const val TABLE_NAME = "EpisodeDbEntity"

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Observable<List<EpisodeDbEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id LIKE :id")
    fun getById(id: Int): Single<List<EpisodeDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: EpisodeDbEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<EpisodeDbEntity>): Completable

    @Delete
    fun delete(item: EpisodeDbEntity): Completable

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll(): Completable

}
