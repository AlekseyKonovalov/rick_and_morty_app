package com.example.rickandmortyapp.feature.tab_container.characters.presentation

import RxDecor
import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.feature.tab_container.characters.domain.CharactersInteractor
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.mapper.FromCharacterEntityMapper
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel
import com.example.rickandmortyapp.navigation.*
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor,
    private val fromCharacterEntityMapper: FromCharacterEntityMapper,
    private val appNavigator: AppNavigator
) : BasePresenter<CharactersView>() {

    private var charactersList: MutableList<CharacterModel> = mutableListOf()
    private var currentPage = 0

    override fun onFirstViewAttach() {
        viewState.initListeners()
        viewState.initAdapter()
        viewState.updateCharacterSubscription(paginationSize = PAGINATION_SIZE)
    }

    fun onPageScrolled() {
        currentPage += 1
        charactersInteractor.getAllCharacters(page = currentPage)
            .map { fromCharacterEntityMapper.map(it) }
            .compose(RxDecor.loading(viewState))
            .compose(schedulersTransformerObservable())
            .subscribe(
                {
                    charactersList.addAll(it)
                    viewState.setItems(charactersList)
                },
                {
                    Timber.e(it.toString())
                }
            ).addToFullLifeCycle()
    }

    fun onRefresh() {
        currentPage = 0
        charactersList.clear()
        onPageScrolled()
    }

    fun onCharacterItemClick(item: CharacterModel) {
        appNavigator.emmitData(
            NavigatorData(
                Command.Navigate,
                ScreenData(
                    Flows.CHARACTER_DETAIL.name,
                    CharacterDetailsModel(item.name, item.imageUrl)
                )
            )
        )
    }

    fun onFavoriteCharacterItemClick(item: CharacterModel) {
        val indexItem = charactersList.indexOf(item)
        if (indexItem == -1) return
        charactersList[indexItem] = item.copy(isFavorite = !item.isFavorite)
        viewState.setItems(charactersList)
    }

    companion object {
        private const val PAGINATION_SIZE = 20
    }
}