package com.example.rickandmortyapp.feature.characters.presentation

import com.arellomobile.mvp.MvpView
import com.example.rickandmortyapp.feature.characters.domain.entity.CharacterEntity

interface CharactersView : MvpView {
    fun setItems(items: List<CharacterEntity>)
}