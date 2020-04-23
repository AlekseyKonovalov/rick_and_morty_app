package com.example.rickandmortyapp.feature.characters.characters_fm.presentation

import RxDecor
import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.mapper.ToCharacterEntityMapper
import com.example.rickandmortyapp.domain.CharactersInteractor
import com.example.rickandmortyapp.feature.characters.characters_flow.navigation.CharactersNavigator
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterDataBus
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.mapper.FromCharacterEntityMapper
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.UpdateCharacterBus
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.navigation.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter @Inject constructor(
    private val charactersInteractor: CharactersInteractor,
    private val fromCharacterEntityMapper: FromCharacterEntityMapper,
    private val updateCharacterBus: UpdateCharacterBus,
    private val toCharacterEntityMapper: ToCharacterEntityMapper,
    private val charactersNavigator: CharactersNavigator,
    private val filterDataBus: FilterDataBus
) : BasePresenter<CharactersView>() {

    private var charactersList: MutableList<CharacterModel> = mutableListOf()
    private var currentPage = 0
    private var filterModel: FilterModel? = null

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

        filterDataBus.getData()
            .compose(schedulersTransformerObservable())
            .subscribe({
                it?.let {
                    filterModel = it
                    dispatchFilterData()
                }
            }, {
                Timber.e(it)
            })
            .addToFullLifeCycle()
    }

    private fun dispatchFilterData() {
        val filterModel = filterModel
        if (filterModel == null ||
            filterModel.species == null && filterModel.status == null && filterModel.gender == null
        ) {
            this.filterModel = null
            viewState.setChipGroup(filterModel)
            return
        }

        charactersInteractor.getCharactersByFilter(
            status = filterModel.status?.title ?: "",
            species = filterModel.species?.title ?: "",
            gender = filterModel.gender?.title ?: ""
        )
            .map { fromCharacterEntityMapper.map(it) }
            .compose(RxDecor.loading(viewState))
            .compose(schedulersTransformerObservable())
            .subscribe(
                {
                    charactersList.clear()
                    charactersList.addAll(it)
                    viewState.setItems(charactersList)
                    viewState.setChipGroup(filterModel)
                },
                {
                    Timber.e(it.toString())
                }
            ).addToFullLifeCycle()
    }

    fun onPageScrolled() {
        if (filterModel != null) return
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
        filterModel = null
        viewState.setChipGroup(filterModel)
        onPageScrolled()
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

    fun onMenuClick() {
        viewState.openMenu()
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
        val SPECIES_FILTER = "SPECIES_FILTER"
        val STATUS_FILTER = "STATUS_FILTER"
        val GENDER_FILTER = "GENDER_FILTER"
    }
}