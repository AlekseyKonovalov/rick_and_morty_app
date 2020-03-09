package com.example.rickandmortyapp.feature.profile.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment

class ProfileFragment : BaseFragment(), ProfileView {
    override val layoutRes: Int = R.layout.profile_fragment

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(ProfilePresenter::class.java)

}