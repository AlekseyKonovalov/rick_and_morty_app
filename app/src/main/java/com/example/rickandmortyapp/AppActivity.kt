package com.example.rickandmortyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.di.NetModule
import com.example.rickandmortyapp.core.di.Scopes
import com.example.rickandmortyapp.navigation.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import toothpick.ktp.KTP
import java.util.*
import javax.inject.Inject


class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    private val disposables = CompositeDisposable()

    private var flowFragments: LinkedList<String> = LinkedList()

    private val router: AppRouter by lazy {
        AppRouter(supportFragmentManager, R.id.fragmentContainerView, flowFragments)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container_view)
        initNetScope()

        router.chooseNavigation(NavigatorData(Command.Navigate, ScreenData(Flows.SPLASH.name)))

        disposables += appNavigator.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                router.chooseNavigation(
                    NavigatorData(
                        it.command,
                        ScreenData(it.screenData.screenName, it.screenData.data)
                    )
                )
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
        flowFragments.clear()
    }

    override fun onBackPressed() {
        val currentFlowFragment = (supportFragmentManager.findFragmentByTag(flowFragments.last())
                as? BaseFlowFragment)
        val countChildFragments = currentFlowFragment?.getFragmentsList()?.size ?: 0

        when {
            finishFlows.contains(flowFragments.last()) && countChildFragments < 2 -> {
                finish()
            }
            countChildFragments < 2 && currentFlowFragment != null -> {
                router.chooseNavigation(NavigatorData(Command.Remove, ScreenData(flowFragments.last())))
            }
            countChildFragments >= 2 && currentFlowFragment != null -> {
                currentFlowFragment.chooseNavigationAction(
                    NavigatorData(
                        Command.Remove,
                        ScreenData(currentFlowFragment.getFragmentsList().last())
                    )
                )
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    private fun initNetScope() {
        KTP.openScopes(Scopes.APPLICATION_SCOPE, Scopes.NET_SCOPE)
            .installModules(NetModule())
            .inject(this)
    }

    companion object {
        private val finishFlows = listOf(Flows.CHARACTERS.name, Flows.SPLASH.name)
    }
}
