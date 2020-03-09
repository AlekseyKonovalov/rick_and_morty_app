package com.example.rickandmortyapp.db.dao

import androidx.room.*
import com.example.rickandmortyapp.db.entity.ProfileDbEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

private const val TABLE_NAME = "ProfileDbEntity"

@Dao
interface ProfileDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Observable<List<ProfileDbEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id LIKE :id")
    fun getById(id: Int): Single<List<ProfileDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ProfileDbEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<ProfileDbEntity>): Completable

    @Delete
    fun delete(item: ProfileDbEntity): Completable

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll(): Completable

}
