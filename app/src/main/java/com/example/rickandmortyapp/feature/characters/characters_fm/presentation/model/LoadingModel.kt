package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model

data class LoadingModel(
    val isShowLoading: Boolean = false,
    val isShowError: Boolean = false,
    val error: String = ""
) {
    companion object {
        fun getError(error: String): LoadingModel {
            return LoadingModel(isShowLoading = false, isShowError = true, error = error)
        }
        fun getLoading(): LoadingModel{
            return LoadingModel(isShowLoading = true, isShowError = false, error = "")
        }
    }
}