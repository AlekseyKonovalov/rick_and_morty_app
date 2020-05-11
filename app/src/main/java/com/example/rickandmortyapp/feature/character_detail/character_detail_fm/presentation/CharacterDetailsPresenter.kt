package com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.util.ResourceProvider
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.navigation.CharacterDetailNavigator
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.mapper.ToCharacterEntityMapper
import com.example.rickandmortyapp.domain.interactor.CharactersInteractor
import com.example.rickandmortyapp.domain.entity.Rating
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.UpdateCharacterBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharacterDetailsPresenter @Inject constructor(
    private val characterDetailNavigator: CharacterDetailNavigator,
    private val resourceProvider: ResourceProvider,
    private val updateCharacterBus: UpdateCharacterBus,
    private val charactersInteractor: CharactersInteractor,
    private val toCharacterEntityMapper: ToCharacterEntityMapper
) : BasePresenter<CharacterDetailsView>() {

    private lateinit var characterDetailsModel: CharacterModel

    fun setData(characterDetailsModel: CharacterModel) {
        this.characterDetailsModel = characterDetailsModel
        viewState.initViews(characterDetailsModel)
        viewState.updateFavoriteIcon(characterDetailsModel.isFavorite)

        updateRating()
    }

    private fun updateRating() {
        when (characterDetailsModel.rating) {
            Rating.Neutral -> {
                viewState.setThumbInactive()
            }
            Rating.Positive -> {
                viewState.setThumbUpActive()
            }
            Rating.Negative -> {
                viewState.setThumbDownActive()
            }
        }
    }

    fun onThumbUpClick() {
        characterDetailsModel = characterDetailsModel.copy(rating = Rating.Positive)
        saveItem()

        updateRating()
    }

    fun onThumbDownClick() {
        characterDetailsModel = characterDetailsModel.copy(rating = Rating.Negative)
        saveItem()
        updateRating()
    }

    fun onOnFavoriteIconClick() {
        characterDetailsModel = characterDetailsModel.copy(isFavorite = !characterDetailsModel.isFavorite)
        saveItem()
        viewState.updateFavoriteIcon(characterDetailsModel.isFavorite)
    }

    private fun saveItem() {
        updateCharacterBus.emmitData(characterDetailsModel)
        charactersInteractor.saveCharacter(toCharacterEntityMapper.mapToEntity(characterDetailsModel))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { Timber.e(it) })
            .addToFullLifeCycle()
    }

}