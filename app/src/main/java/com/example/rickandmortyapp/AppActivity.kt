package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.rickandmortyapp.core.base.BaseFlowFragment
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
import java.util.*
import javax.inject.Inject


class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    private val disposables = CompositeDisposable()

    var flowFragments: LinkedList<String> = LinkedList()

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

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
        flowFragments.clear()
    }

    fun navigateToScreen(command: Command, screen: String, data: Any? = null) {
        val flowFragment: BaseFlowFragment?
        val tag: String

        when (screen) {
            Flows.SPLASH.name -> {
                tag = Flows.SPLASH.name
                flowFragment = if (command != Command.Remove) SplashFlowFragment.getInstance() else
                    supportFragmentManager.findFragmentByTag(tag) as BaseFlowFragment?
            }
            Flows.TAB_CONTAINER.name -> {
                tag = Flows.TAB_CONTAINER.name
                flowFragment = if (command != Command.Remove) TabContainerFlowFragment() else
                    supportFragmentManager.findFragmentByTag(tag) as BaseFlowFragment?
            }
            Flows.CHARACTER_DETAIL.name -> {
                tag = Flows.CHARACTER_DETAIL.name
                flowFragment = if (command != Command.Remove) CharacterDetailsFlowFragment.getInstance(data as CharacterDetailsModel)
                else supportFragmentManager.findFragmentByTag(tag) as BaseFlowFragment?
            }
            else -> return
        }

        if (flowFragment == null) return

        when (command) {
            Command.Navigate -> {
                routeToScreen(flowFragment, tag)
            }
            Command.Replace -> {
                replaceScreen(flowFragment, tag)
            }
            Command.Remove -> {
                removeScreen(flowFragment)
            }
        }
    }

    private fun routeToScreen(fragment: BaseFragment, tag: String) {
        flowFragments.add(tag)
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragmentContainerView,
                fragment,
                tag
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    private fun replaceScreen(fragment: BaseFragment, tag: String) {
        flowFragments.removeLast()
        flowFragments.add(tag)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                fragment,
                tag
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun removeScreen(fragment: BaseFragment) {
        flowFragments.removeLast()
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .commit()
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        val currentFlowFragment = (supportFragmentManager.findFragmentByTag(flowFragments.last())
                as? BaseFlowFragment)
        val countChildFragments = currentFlowFragment?.fragments?.size ?: 0

        if (finishFlows.contains(flowFragments.last()) && countChildFragments < 2) {
            finish()
            return
        }

        if (countChildFragments < 2 || currentFlowFragment == null) {
            currentFlowFragment!!.removeLastScreen()
            removeScreen(supportFragmentManager.findFragmentByTag(flowFragments.last()) as BaseFlowFragment)
        } else {
            super.onBackPressed()
        }
    }

    private fun initNetScope() {
        KTP.openScopes(Scopes.APPLICATION_SCOPE, Scopes.NET_SCOPE)
            .installModules(NetModule())
            .inject(this)
    }

    companion object {
        private val finishFlows = listOf(Flows.TAB_CONTAINER.name, Flows.SPLASH.name)
    }
}
