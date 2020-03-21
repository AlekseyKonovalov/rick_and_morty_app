package com.example.rickandmortyapp.feature.splash.splash_fm.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.splash.splash_flow.presentation.SplashFlowFragment

class SplashFragment : BaseFragment(),  SplashView {
    override val layoutRes: Int = R.layout.splash_fragment

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(SplashPresenter::class.java)

    companion object {
        fun getInstance(): SplashFragment {
            return SplashFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}