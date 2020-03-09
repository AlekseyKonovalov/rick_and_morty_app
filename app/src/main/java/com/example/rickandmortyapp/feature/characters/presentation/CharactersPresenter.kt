package com.example.rickandmortyapp.feature.characters.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.characters.domain.CharactersInteractor
import com.example.rickandmortyapp.feature.characters.domain.entity.CharacterEntity
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor
) : BasePresenter<CharactersView>() {

    override fun onFirstViewAttach() {
        initViews()
    }

    private fun initViews() {
        charactersInteractor.getAllCharacters()
            .compose(schedulersTransformerObservable())
            .subscribe(
                {
                    viewState.setItems(it)
                },
                {
                    Timber.e(it.toString())
                }
            ).addToFullLifeCycle()
    }

    fun onClickCharacterItem(item: CharacterEntity) {

    }
}