package com.example.rickandmortyapp.feature.characters.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.characters.domain.CharactersInteractor
import com.example.rickandmortyapp.feature.characters.domain.entity.CharacterEntity
import com.example.rickandmortyapp.feature.characters.presentation.mapper.FromCharacterEntityMapper
import com.example.rickandmortyapp.feature.characters.presentation.model.CharacterFavoriteBus
import com.example.rickandmortyapp.feature.characters.presentation.model.CharacterModel
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor,
    private val fromCharacterEntityMapper: FromCharacterEntityMapper
) : BasePresenter<CharactersView>() {

    private var charactersList: MutableList<CharacterModel> = mutableListOf()

    override fun onFirstViewAttach() {
        initViews()
    }

    private fun initViews() {
        charactersInteractor.getAllCharacters()
            .map { fromCharacterEntityMapper.map(it) }
            .compose(schedulersTransformerObservable())
            .subscribe(
                {
                    charactersList.addAll(it)
                    viewState.setItems(it)
                },
                {
                    Timber.e(it.toString())
                }
            ).addToFullLifeCycle()
    }

    fun onCharacterItemClick(item: CharacterModel) {

    }

    fun onFavoriteCharacterItemClick(item: CharacterModel) {
        val indexItem = charactersList.indexOf(item)
        val updatedItem = item.copy(isFavorite = !item.isFavorite)
        charactersList[indexItem] = updatedItem
        viewState.setItems(charactersList)
    }
}