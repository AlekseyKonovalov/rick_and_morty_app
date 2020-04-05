package com.example.rickandmortyapp.core.base

import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.navigation.Navigation
import com.example.rickandmortyapp.navigation.NavigatorData
import java.util.*

abstract class BaseFlowFragment : BaseFragment(), Navigation {
    override val layoutRes: Int = R.layout.fragment_container_view
    abstract fun getFragmentsList(): LinkedList<String>
}