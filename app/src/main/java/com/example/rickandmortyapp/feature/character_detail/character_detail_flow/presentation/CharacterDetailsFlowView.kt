package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.core.base.FlowView
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharacterDetailsFlowView : FlowView {
    fun removeLastScreen()
}