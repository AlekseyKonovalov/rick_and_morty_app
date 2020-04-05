package com.example.rickandmortyapp.feature.tab_container.characters.presentation

import RxDecor
import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.mapper.ToCharacterEntityMapper
import com.example.rickandmortyapp.feature.domain.CharactersInteractor
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.mapper.FromCharacterEntityMapper
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.UpdateCharacterBus
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel
import com.example.rickandmortyapp.navigation.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor,
    private val fromCharacterEntityMapper: FromCharacterEntityMapper,
    private val appNavigator: AppNavigator,
    private val updateCharacterBus: UpdateCharacterBus,
    private val toCharacterEntityMapper: ToCharacterEntityMapper
) : BasePresenter<CharactersView>() {

    private var charactersList: MutableList<CharacterModel> = mutableListOf()
    private var currentPage = 0

    override fun onFirstViewAttach() {
        viewState.initListeners()
        viewState.initAdapter()
        viewState.updateCharacterSubscription(paginationSize = PAGINATION_SIZE)

        updateCharacterBus.getData()
            .compose(schedulersTransformerObservable())
            .subscribe({ character ->
                val index = charactersList.indexOf(charactersList.first { it.id == character.id })
                charactersList[index] = charactersList[index].copy(
                    isFavorite = character.isFavorite,
                    rating = character.rating
                )
                viewState.setItems(charactersList)
            }, {
                Timber.e(it)
            })
            .addToFullLifeCycle()
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

    companion object {
        private const val PAGINATION_SIZE = 20
    }
}