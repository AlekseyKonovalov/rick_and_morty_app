package com.example.rickandmortyapp.feature.characters.presentation

import RxDecor
import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.Navigator
import com.example.rickandmortyapp.ScreenData
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.model.CharacterDetailsModel
import com.example.rickandmortyapp.feature.characters.domain.CharactersInteractor
import com.example.rickandmortyapp.feature.characters.presentation.mapper.FromCharacterEntityMapper
import com.example.rickandmortyapp.feature.characters.presentation.model.CharacterModel
import com.example.rickandmortyapp.feature.tab_container_flow.presentation.TabContainerFlowFragment
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor,
    private val fromCharacterEntityMapper: FromCharacterEntityMapper,
    private val navigator: Navigator
) : BasePresenter<CharactersView>() {

    private var charactersList: MutableList<CharacterModel> = mutableListOf()

    override fun onFirstViewAttach() {
        viewState.initAdapter()
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
        navigator.emmitData(
            ScreenData(
                TabContainerFlowFragment.SCREEN_CHARACTER_DETAIL,
                CharacterDetailsModel(item.imageUrl)
            )
        )
    }

    fun onFavoriteCharacterItemClick(item: CharacterModel) {
        val indexItem = charactersList.indexOf(item)
        if (indexItem == -1) return
        charactersList[indexItem] = item.copy(isFavorite = !item.isFavorite)
        viewState.setItems(charactersList)
    }
}