package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FilterDialogPresenter @Inject constructor(
    private val filterDataBus: FilterDataBus
) : BasePresenter<FilterDialogView>() {

    private var status: FilterStatus? = null
    private var species: FilterSpecies? = null
    private var gender: FilterGender? = null
    private val disposables = CompositeDisposable()


    override fun onFirstViewAttach() {
        viewState.initListeners()
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


    fun onStatusClick(status: FilterStatus?) {
        this.status = status
    }

    fun onSpeciesClick(species: FilterSpecies?) {
        this.species = species
    }

    fun onGenderClick(gender: FilterGender?) {
        this.gender = gender
    }

    fun onApplyClick() {
        filterDataBus.emmitData(FilterModel(status, species, gender))
    }

    private fun updateView() {
        with(viewState) {
            gender?.let { setGender(it) }
            species?.let { setSpecies(it) }
            status?.let { setStatus(it) }
        }
    }

    private fun observeFilter() {
        disposables += filterDataBus.getData()
            .compose(schedulersTransformerObservable())
            .subscribe({
                it?.let {
                    status = it.status
                    species = it.species
                    gender = it.gender
                    updateView()
                }
            }, {
                Timber.e(it)
            })

    }

}