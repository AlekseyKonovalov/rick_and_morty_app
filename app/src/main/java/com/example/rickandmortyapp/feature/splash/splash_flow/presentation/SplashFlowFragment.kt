package com.example.rickandmortyapp.feature.splash.splash_flow.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.splash.splash_fm.presentation.SplashFragment

class SplashFlowFragment : BaseFragment(), SplashFlowView {

    override val layoutRes: Int = R.layout.fragment_container_view

    @InjectPresenter
    lateinit var presenter: SplashFlowPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(SplashFlowPresenter::class.java)

    override fun navigateToStartScreen(data: Any?) {
        fragmentManager?.let {
            it.beginTransaction()
                .add(
                    R.id.fragmentContainerView,
                    SplashFragment.getInstance()
                )
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