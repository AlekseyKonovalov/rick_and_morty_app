package com.example.rickandmortyapp.core.base

import com.example.rickandmortyapp.R
import java.util.*

abstract class BaseFlowFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_container_view
    abstract var fragments: LinkedList<String>
    abstract fun removeLastScreen()
}