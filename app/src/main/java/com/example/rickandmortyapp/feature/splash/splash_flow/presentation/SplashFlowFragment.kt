package com.example.rickandmortyapp.feature.splash.splash_flow.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.splash.splash_fm.presentation.SplashFragment
import com.example.rickandmortyapp.navigation.Flows
import kotlinx.android.synthetic.main.character_details_fragment.*
import java.util.*

class SplashFlowFragment : BaseFlowFragment(), SplashFlowView {

    override var fragments: LinkedList<String> = LinkedList()

    @InjectPresenter
    lateinit var presenter: SplashFlowPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(SplashFlowPresenter::class.java)

    override fun navigateToStartScreen(data: Any?) {
        fragmentManager?.let {
            val tag = Flows.SPLASH.SPLASH
            fragments.add(tag)
            it.beginTransaction()
                .add(
                    R.id.fragmentContainerView,
                    SplashFragment.getInstance(),
                    tag
                )
                .commit()
        }
    }

    override fun removeLastScreen() {
        fragmentManager?.let { fm ->
            val fragment = fm.findFragmentByTag(fragments.last()) ?: return@let
            fragments.removeLast()
            fm.beginTransaction()
                .remove(fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit()
        }
    }

    companion object {
        fun getInstance(): SplashFlowFragment {
            return SplashFlowFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}