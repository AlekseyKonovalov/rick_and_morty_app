package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.db.AppDatabase
import com.example.rickandmortyapp.data.db.entity.CharacterDbEntity
import com.example.rickandmortyapp.data.mapper.CharacterEntityMapper
import com.example.rickandmortyapp.domain.entity.CharacterEntity
import com.example.rickandmortyapp.domain.entity.ListCharacterEntity
import com.example.rickandmortyapp.data.network.RickMortyApi
import com.example.rickandmortyapp.domain.gateway.CharactersRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val rickMortyApi: RickMortyApi,
    private val characterEntityMapper: CharacterEntityMapper,
    private val appDatabase: AppDatabase
): CharactersRepository {

    override fun getAllCharacters(page: Int): Observable<ListCharacterEntity> {
        return Observable.zip(rickMortyApi.getAllCharacters(page).map { characterEntityMapper.mapToEntity(it) },
            appDatabase.characterDao().getAll(),
            BiFunction { remoteList: ListCharacterEntity,
                         localList: List<CharacterDbEntity> ->
                return@BiFunction Pair(remoteList, localList)
            })
            .map {
                mergeRemoteAndFromDBCharacters(it.first, it.second)
            }

    }

    override  fun getCharactersBySearch(request: String): Observable<ListCharacterEntity> {
        return Observable.zip(rickMortyApi.getCharactersBySearch(request).map { characterEntityMapper.mapToEntity(it) },
            appDatabase.characterDao().getAll(),
            BiFunction { remoteList: ListCharacterEntity,
                         localList: List<CharacterDbEntity> ->
                return@BiFunction Pair(remoteList, localList)
            })
            .map {
                mergeRemoteAndFromDBCharacters(it.first, it.second)
            }
    }

    override   fun getCharactersByFilter(
        status: String,
        species: String,
        gender: String,
        page: Int?
    ): Observable<ListCharacterEntity> {
        return Observable.zip(rickMortyApi.getCharactersByFilter(
            status,
            species,
            gender,
            page
        ).map { characterEntityMapper.mapToEntity(it) },
            appDatabase.characterDao().getAll(),
            BiFunction { remoteList: ListCharacterEntity,
                         localList: List<CharacterDbEntity> ->
                return@BiFunction Pair(remoteList, localList)
            })
            .map {
                mergeRemoteAndFromDBCharacters(it.first, it.second)
            }
    }

    override fun saveCharacter(item: CharacterEntity): Completable {
        return appDatabase.characterDao().insert(characterEntityMapper.mapFromEntityToDBEntity(item))
    }

    private fun mergeRemoteAndFromDBCharacters(
        remote: ListCharacterEntity,
        local: List<CharacterDbEntity>
    ): ListCharacterEntity {
        val items = remote.list.toMutableList()
        local.forEach { dbEntity ->
            val item = items.find { it.id == dbEntity.id }
            if (item != null) {
                val index = items.indexOf(item)
                items[index] = items[index].copy(
                    isFavorite = dbEntity.isFavorite,
                    rating = characterEntityMapper.mapRating(dbEntity.rating)
                )
            }
        }
        return ListCharacterEntity(remote.count, items)
    }

}