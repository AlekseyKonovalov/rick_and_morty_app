package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.core.di.NetModule
import com.example.rickandmortyapp.feature.tab_container_flow.presentation.TabContainerFlowFragment
import toothpick.ktp.KTP

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container_view)
        initNetScope()
        navigateToTabContainerScreen()
    }

    private fun navigateToTabContainerScreen() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragmentContainerView,
                TabContainerFlowFragment()
            )
            .commit()
    }

    private fun initNetScope() {
        KTP.openScopes(Scopes.APPLICATION_SCOPE, Scopes.NET_SCOPE)
            .installModules(NetModule())
            .inject(this)
    }
}
