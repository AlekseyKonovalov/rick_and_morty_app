package com.example.rickandmortyapp.feature.characters.presentation

import RxDecor
import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.characters.domain.CharactersInteractor
import com.example.rickandmortyapp.feature.characters.presentation.mapper.FromCharacterEntityMapper
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
        charactersInteractor.getAllCharacters(page = 1)
            .map { fromCharacterEntityMapper.map(it) }
            .compose(RxDecor.loading(viewState))
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
        charactersList[indexItem] = item.copy(isFavorite = !item.isFavorite)
        viewState.setItems(charactersList)
    }
}