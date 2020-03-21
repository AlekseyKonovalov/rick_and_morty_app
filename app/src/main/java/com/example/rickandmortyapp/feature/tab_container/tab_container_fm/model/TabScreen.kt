package com.example.rickandmortyapp.feature.tab_container.tab_container_fm.model

import com.example.rickandmortyapp.core.base.BaseFragment

data class TabScreen(
    val resId: Int,
    val name: String,
    val fragment: BaseFragment
)