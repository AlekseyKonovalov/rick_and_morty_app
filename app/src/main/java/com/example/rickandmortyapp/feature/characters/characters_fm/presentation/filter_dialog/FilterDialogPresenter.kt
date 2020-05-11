package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.adapter.ChipModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FilterDialogPresenter @Inject constructor(
    private val filterDataBus: FilterDataBus,
    private val filterUiMapper: FilterUiMapper
) : BasePresenter<FilterDialogView>() {

    private var status: FilterData.FilterStatus? = null
    private var species: FilterData.FilterSpecies? = null
    private var gender: FilterData.FilterGender? = null
    private val disposables = CompositeDisposable()


    override fun onFirstViewAttach() {
        viewState.initListeners()
        viewState.initViews(
            statuses = filterUiMapper.getFilterDataStatus().map { ChipModel(it, false) },
            genderList = filterUiMapper.getFilterDataGender().map { ChipModel(it, false) },
            species = filterUiMapper.getFilterDataSpecies().map { ChipModel(it, false) }
        )
        observeFilter()
    }

    override fun attachView(view: FilterDialogView?) {
        super.attachView(view)
        observeFilter()
    }

    override fun detachView(view: FilterDialogView?) {
        super.detachView(view)
        disposables.dispose()
    }

    fun onChipSelected(chipModel: ChipModel) {
        when (chipModel.data) {
            is FilterData.FilterStatus -> {
                this.status = chipModel.data
                viewState.updateStatuses(
                    filterUiMapper.getFilterDataStatus().map {
                        ChipModel(
                            it,
                            if (it.title == chipModel.data.title) chipModel.isChecked else false
                        )
                    }
                )
            }
            is FilterData.FilterSpecies -> {
                this.species = chipModel.data
                viewState.updateSpecies(
                    filterUiMapper.getFilterDataSpecies().map {
                        ChipModel(
                            it,
                            if (it.title == chipModel.data.title) chipModel.isChecked else false
                        )
                    }
                )
            }
            is FilterData.FilterGender -> {
                this.gender = chipModel.data
                viewState.updatedGenderList(
                    filterUiMapper.getFilterDataGender().map {
                        ChipModel(
                            it,
                            if (it.title == chipModel.data.title) chipModel.isChecked else false
                        )
                    }
                )
            }
        }
    }

    fun onApplyClick() {
        filterDataBus.emmitData(FilterModel(status, species, gender))
    }

    private fun observeFilter() {
        disposables += filterDataBus.getData()
            .compose(schedulersTransformerObservable())
            .subscribe({
                it?.let {
                    status = it.status
                    species = it.species
                    gender = it.gender

                    listOf(status, species, gender).forEach {
                        it?.let { onChipSelected(ChipModel(it, true)) }
                    }
                }
            }, {
                Timber.e(it)
            })

    }

}