package com.example.rickandmortyapp.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.rickandmortyapp.Scopes
import toothpick.Scope
import toothpick.ktp.KTP

abstract class BaseFragment : MvpAppCompatFragment() {

    abstract val layoutRes: Int

    protected lateinit var scope: Scope
        private set

    private lateinit var scopeName: String
    private val parentScopeName: String by lazy {
        (parentFragment as? BaseFragment)?.scopeName ?: Scopes.NET_SCOPE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        openScope(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    private fun openScope(savedInstanceState: Bundle?) {
        scopeName = savedInstanceState?.getString(SCOPE_NAME_SAVE_KEY) ?: getBasicScopeName()
        if (KTP.isScopeOpen(scopeName)) {
            scope = KTP.openScopes(parentScopeName, scopeName)
        } else {
            scope = KTP.openScopes(parentScopeName, scopeName)
            installModules(scope)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    protected open fun installModules(scope: Scope) {}

    private fun getBasicScopeName() = javaClass.simpleName


    companion object {
        private const val SCOPE_NAME_SAVE_KEY = "SCOPE_NAME_SAVE_KEY"
    }
}