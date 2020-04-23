package com.example.rickandmortyapp.feature.search.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.domain.CharactersInteractor
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.mapper.ToCharacterEntityMapper
import com.example.rickandmortyapp.feature.characters.characters_flow.navigation.CharactersNavigator
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.mapper.FromCharacterEntityMapper
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.feature.splash.splash_flow.navigation.SplashNavigator
import com.example.rickandmortyapp.navigation.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor,
    private val fromCharacterEntityMapper: FromCharacterEntityMapper,
    private val toCharacterEntityMapper: ToCharacterEntityMapper,
    private val charactersNavigator: CharactersNavigator
) : BasePresenter<SearchView>() {

    private var charactersList: MutableList<CharacterModel> = mutableListOf()

    override fun onFirstViewAttach() {
        viewState.initViews()
    }

    fun dispatchSearchRequest(request: String) {
        if (request.isBlank()) {
            charactersList.clear()
            viewState.setItems(charactersList)
            changePlaceholder(isHidePlaceholder = false)
            return
        }
        charactersInteractor.getCharactersBySearch(request)
            .map { fromCharacterEntityMapper.map(it) }
            .compose(RxDecor.loading(viewState))
            .compose(schedulersTransformerObservable())
            .subscribe(
                {
                    changePlaceholder(it.isNotEmpty())
                    charactersList.clear()
                    charactersList.addAll(it)
                    viewState.setItems(charactersList)
                },
                {
                    Timber.e(it.toString())
                }
            ).addToFullLifeCycle()
    }

    private fun changePlaceholder(isHidePlaceholder: Boolean) {
        if (isHidePlaceholder) {
            viewState.hidePlaceholder()
        } else {
            viewState.showPlaceholder()
        }
    }

    //todo to baseCharacterPresenter
    fun onCharacterItemClick(item: CharacterModel) {
        charactersNavigator.emmitData(
            NavigatorData(
                Command.Navigate,
                ScreenData(
                    Flows.CHARACTER_DETAIL.name,
                    item
                )
            )
        )
    }

    fun onFavoriteCharacterItemClick(item: CharacterModel) {
        val indexItem = charactersList.indexOf(item)
        if (indexItem == -1) return
        charactersList[indexItem] = item.copy(isFavorite = !item.isFavorite)
        charactersInteractor.saveCharacter(toCharacterEntityMapper.mapToEntity(charactersList[indexItem]))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { Timber.e(it) })
            .addToFullLifeCycle()
        viewState.setItems(charactersList)
    }
}