package com.example.rickandmortyapp.feature.characters.characters_fm.presentation

import RxDecor
import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.core.util.ResourceProvider
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.mapper.ToCharacterEntityMapper
import com.example.rickandmortyapp.domain.interactor.CharactersInteractor
import com.example.rickandmortyapp.feature.characters.characters_flow.navigation.CharactersNavigator
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterDataBus
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.mapper.FromCharacterEntityMapper
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.UpdateCharacterBus
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.LoadingModel
import com.example.rickandmortyapp.navigation.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.characters_fragment.*
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor,
    private val fromCharacterEntityMapper: FromCharacterEntityMapper,
    private val updateCharacterBus: UpdateCharacterBus,
    private val toCharacterEntityMapper: ToCharacterEntityMapper,
    private val charactersNavigator: CharactersNavigator,
    private val filterDataBus: FilterDataBus,
    private val resourceProvider: ResourceProvider
) : BasePresenter<CharactersView>() {

    private var charactersList: MutableList<CharacterModel> = mutableListOf()
    private var searchedList: MutableList<Any> = mutableListOf()

    private var currentPage = 0
    private var nextPage: String? = null
    private var filterModel: FilterModel? = null

    override fun onFirstViewAttach() {
        viewState.initListeners()
        viewState.initAdapter()
        viewState.updateCharacterSubscription(paginationSize = PAGINATION_SIZE)
        observeFavorites()
        observeFilter()
    }

    private fun observeFavorites() {
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

    private fun observeFilter() {
        filterDataBus.getData()
            .compose(schedulersTransformerObservable())
            .subscribe({
                it?.let {
                    filterModel = it
                    dispatchFilterData(it)
                }
            }, {
                Timber.e(it)
            })
            .addToFullLifeCycle()
    }

    private fun dispatchFilterData(newFilterModel: FilterModel) {
        with(newFilterModel) {
            if (species == null && status == null && gender == null) {
                filterModel = null
            }
        }
        onRefresh()
    }

    fun onPageScrolled() {
        currentPage += 1

        if (nextPage == null && currentPage != 1) return

        val status = filterModel?.status?.title ?: ""
        val species = filterModel?.species?.title ?: ""
        val gender = filterModel?.gender?.title ?: ""

        updatedBottomItem(LoadingModel.getLoading(), charactersList)

        charactersInteractor.getCharactersByFilter(
            status = status,
            species = species,
            gender = gender,
            page = currentPage
        )
            .map { fromCharacterEntityMapper.map(it) }
            .compose(schedulersTransformerObservable())
            .subscribe(
                {
                    nextPage = it.nextPage
                    charactersList.addAll(it.list)
                    viewState.setItems(charactersList)
                    updateFilterChipGroup()
                },
                {
                    charactersList.clear()
                    updatedBottomItem(LoadingModel.getError(resourceProvider.getString(R.string.search_empty_error)))
                    Timber.e(it.toString())
                }
            ).addToFullLifeCycle()
    }

    private fun updatedBottomItem(data: LoadingModel, items: List<CharacterModel> = listOf()) {
        searchedList.clear()
        searchedList.addAll(items)
        searchedList.add(data)
        viewState.setItems(searchedList)
    }

    private fun updateFilterChipGroup() {
        viewState.setChipGroup(
            filterModel?.status?.localeName,
            filterModel?.species?.localeName,
            filterModel?.gender?.localeName
        )
    }

    fun onRefresh() {
        currentPage = 0
        charactersList.clear()
        updateFilterChipGroup()
        onPageScrolled()
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

    fun onFilterDialogClick() {
        viewState.openFilterDialog()
    }

    fun onSearchClick() {
        charactersNavigator.emmitData(
            NavigatorData(
                Command.Navigate,
                ScreenData(
                    Flows.CHARACTERS.SEARCH
                )
            )
        )
    }

    fun onChipsClick(filter: String) {
        filterModel?.let {
            when (filter) {
                STATUS_FILTER -> {
                    filterDataBus.emmitData(it.copy(status = null))
                }
                SPECIES_FILTER -> {
                    filterDataBus.emmitData(it.copy(species = null))
                }
                GENDER_FILTER -> {
                    filterDataBus.emmitData(it.copy(gender = null))
                }
            }
        }
    }

    companion object {
        private const val PAGINATION_SIZE = 20
        const val SPECIES_FILTER = "SPECIES_FILTER"
        const val STATUS_FILTER = "STATUS_FILTER"
        const val GENDER_FILTER = "GENDER_FILTER"
    }
}