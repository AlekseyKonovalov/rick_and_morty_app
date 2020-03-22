package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.navigation.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharacterDetailsFlowPresenter @Inject constructor(
    private val appNavigator: AppNavigator,
    private val characterDetailNavigator: CharacterDetailNavigator
) : BasePresenter<CharacterDetailsFlowView>() {
    override fun onFirstViewAttach() {
        viewState.navigateToStartScreen()
        characterDetailNavigator.getData()
            .compose(schedulersTransformerObservable())
            .subscribe( {
                //todo refactoring
                if (Flows.CHARACTER_DETAIL.screens.contains(it.screenData.screenName)){
                    when(it.command ){
                            Command.Remove -> {
                                viewState.removeLastScreen()
                            }
                    }
                } else {
                    appNavigator.emmitData(it)
                }
            }, {
                Timber.e(it.toString())
            })
            .addToFullLifeCycle()
    }

    fun onBackPressed(){
        appNavigator.emmitData(NavigatorData(Command.Remove, ScreenData(Flows.CHARACTER_DETAIL.name)))
    }

}