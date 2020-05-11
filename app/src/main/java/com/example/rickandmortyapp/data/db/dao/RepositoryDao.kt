package com.example.rickandmortyapp.data.db.dao

/*
private const val TABLE_NAME = "RepositoryDbEntity"

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Observable<List<RepositoryDbEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id LIKE :id")
    fun getById(id: Int): Single<List<RepositoryDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: RepositoryDbEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<RepositoryDbEntity>): Completable

    @Delete
    fun delete(item: RepositoryDbEntity): Completable

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll(): Completable

    @Query("SELECT * FROM $TABLE_NAME WHERE accountEmail LIKE '%' || :accountEmail || '%'")
    fun getFavoriteRepositories(accountEmail: String): Single<List<RepositoryDbEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE accountEmail LIKE '%' || :accountEmail || '%' AND id LIKE '%' || :id || '%'")
    fun getFavoriteRepository(accountEmail: String, id: Int): Single<RepositoryDbEntity?>

}*/
