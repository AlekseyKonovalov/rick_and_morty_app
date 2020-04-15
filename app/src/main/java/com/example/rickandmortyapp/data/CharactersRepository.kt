package com.example.rickandmortyapp.data

import com.example.rickandmortyapp.db.AppDatabase
import com.example.rickandmortyapp.db.entity.CharacterDbEntity
import com.example.rickandmortyapp.domain.entity.CharacterEntity
import com.example.rickandmortyapp.network.RickMortyApi
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val rickMortyApi: RickMortyApi,
    private val characterEntityMapper: CharacterEntityMapper,
    private val appDatabase: AppDatabase
) {
    fun getAllCharacters(page: Int): Observable<List<CharacterEntity>> {
        return Observable.zip(rickMortyApi.getAllCharacters(page).map { characterEntityMapper.mapToEntity(it) },
            appDatabase.characterDao().getAll(),
            BiFunction { remoteList: List<CharacterEntity>,
                         localList: List<CharacterDbEntity> ->
                return@BiFunction Pair(remoteList, localList)
            })
            .map {
                val items = it.first.toMutableList()
                it.second.forEach { dbEntity ->
                    val item = items.find { it.id == dbEntity.id }
                    if (item != null) {
                        val index = items.indexOf(item)
                        items[index] = items[index].copy(
                            isFavorite = dbEntity.isFavorite,
                            rating = characterEntityMapper.mapRating(dbEntity.rating)
                        )
                    }
                }
                items
            }

    }

    fun saveCharacter(item: CharacterEntity): Completable {
        return appDatabase.characterDao().insert(characterEntityMapper.mapFromEntityToDBEntity(item))
    }
}