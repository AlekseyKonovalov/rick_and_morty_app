package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.di.NetModule
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation.CharacterDetailsFlowFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.feature.splash.splash_flow.presentation.SplashFlowFragment
import com.example.rickandmortyapp.feature.tab_container.tab_container_flow.presentation.TabContainerFlowFragment
import com.example.rickandmortyapp.navigation.AppNavigator
import com.example.rickandmortyapp.navigation.Command
import com.example.rickandmortyapp.navigation.Flows
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import toothpick.ktp.KTP
import javax.inject.Inject


class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    private val disposables = CompositeDisposable()

    private var currentFlow: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container_view)
        initNetScope()

        navigateToScreen(Command.Navigate, Flows.SPLASH.name)


        disposables.add(
            appNavigator.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    navigateToScreen(it.command, it.screenData.screenName, it.screenData.data)
                }
        )
    }

    fun navigateToScreen(command: Command, screen: String, data: Any? = null) {
        val flowFragment: BaseFragment
        when (screen) {
            Flows.SPLASH.name -> {
                currentFlow = Flows.SPLASH.name
                flowFragment = SplashFlowFragment.getInstance()
                //routeToScreen(SplashFlowFragment.getInstance())
            }
            Flows.TAB_CONTAINER.name -> {
                currentFlow = Flows.TAB_CONTAINER.name
                flowFragment = TabContainerFlowFragment()
                //routeToScreen(TabContainerFlowFragment())
            }
            Flows.CHARACTER_DETAIL.name -> {
                currentFlow = Flows.CHARACTER_DETAIL.name
                flowFragment = CharacterDetailsFlowFragment.getInstance(data as CharacterDetailsModel)
                //routeToScreen(CharacterDetailsFlowFragment.getInstance(data as CharacterDetailsModel))
            }
            else -> return
        }

        when (command) {
            Command.Navigate -> {
                routeToScreen(flowFragment)
            }
            Command.Replace -> {
                replaceScreen(flowFragment)
            }
        }
    }

    private fun routeToScreen(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragmentContainerView,
                fragment,
                currentFlow
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    private fun replaceScreen(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                fragment
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        when {
            currentFlow == Flows.TAB_CONTAINER.name || supportFragmentManager.backStackEntryCount == 0 -> {
                super.onBackPressed()
            }
            else -> {
                supportFragmentManager.popBackStack()
            }
        }
    }

    private fun initNetScope() {
        KTP.openScopes(Scopes.APPLICATION_SCOPE, Scopes.NET_SCOPE)
            .installModules(NetModule())
            .inject(this)
    }
}
