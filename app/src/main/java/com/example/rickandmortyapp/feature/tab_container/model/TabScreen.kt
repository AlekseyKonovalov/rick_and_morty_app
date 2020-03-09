package com.example.rickandmortyapp.feature.tab_container.model

import com.example.rickandmortyapp.core.base.BaseFragment

data class TabScreen(
    val resId: Int,
    val name: String,
    val fragment: BaseFragment
)