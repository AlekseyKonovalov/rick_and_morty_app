package com.example.rickandmortyapp.feature.search.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.core.util.ResourceProvider
import com.example.rickandmortyapp.domain.interactor.CharactersInteractor
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.mapper.ToCharacterEntityMapper
import com.example.rickandmortyapp.feature.characters.characters_flow.navigation.CharactersNavigator
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.mapper.FromCharacterEntityMapper
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.LoadingModel
import com.example.rickandmortyapp.navigation.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor,
    private val fromCharacterEntityMapper: FromCharacterEntityMapper,
    private val toCharacterEntityMapper: ToCharacterEntityMapper,
    private val charactersNavigator: CharactersNavigator,
    private val resourceProvider: ResourceProvider
) : BasePresenter<SearchView>() {

    private var charactersList: MutableList<CharacterModel> = mutableListOf()
    private var searchedList: MutableList<Any> = mutableListOf()

    override fun onFirstViewAttach() {
        viewState.initViews()
    }

    fun dispatchSearchRequest(request: String) {
        if (request.isBlank()) {
            updateItems(emptyList())
            return
        }

        updatedBottomItem(LoadingModel.getLoading())

        charactersInteractor.getCharactersBySearch(request)
            .map { fromCharacterEntityMapper.map(it) }
            .compose(RxDecor.loading(viewState))
            .compose(schedulersTransformerObservable())
            .subscribe(
                {
                    updateItems(it.list)
                },
                {
                    charactersList.clear()
                    updatedBottomItem(LoadingModel.getError(resourceProvider.getString(R.string.search_empty_error)))
                    changePlaceholder(true)
                    Timber.e(it.toString())
                }
            ).addToFullLifeCycle()
    }

    private fun updatedBottomItem(data: LoadingModel){
        searchedList.clear()
        searchedList.add(data)
        viewState.setItems(searchedList)
    }

    private fun updateItems(items: List<CharacterModel>){
        changePlaceholder(items.isNotEmpty())
        charactersList.clear()
        charactersList.addAll(items)
        viewState.setItems(charactersList)
    }

    private fun changePlaceholder(isHidePlaceholder: Boolean) {
        if (isHidePlaceholder) {
            viewState.hidePlaceholder()
        } else {
            viewState.showPlaceholder()
        }
    }

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
        saveFavoriteItem(charactersList[indexItem])
        viewState.setItems(charactersList)
    }

    private fun saveFavoriteItem(item: CharacterModel) {
        charactersInteractor.saveCharacter(toCharacterEntityMapper.mapToEntity(item))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { Timber.e(it) })
            .addToFullLifeCycle()
    }

    fun onBackPressed(){
        viewState.closeView()
    }
}