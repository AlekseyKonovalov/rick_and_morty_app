package com.example.rickandmortyapp.db.dao

import androidx.room.*
import com.example.rickandmortyapp.db.entity.LocationDbEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

private const val TABLE_NAME = "LocationDbEntity"

@Dao
interface LocationDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Observable<List<LocationDbEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id LIKE :id")
    fun getById(id: Int): Single<List<LocationDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: LocationDbEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<LocationDbEntity>): Completable

    @Delete
    fun delete(item: LocationDbEntity): Completable

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll(): Completable

}
