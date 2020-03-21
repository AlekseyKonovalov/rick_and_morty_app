package com.example.rickandmortyapp.feature.tab_container.profile.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.tab_container.episodes.presentation.EpisodesFragment

class ProfileFragment : BaseFragment(), ProfileView {
    override val layoutRes: Int = R.layout.profile_fragment

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(ProfilePresenter::class.java)

    companion object {
        fun getInstance(): ProfileFragment {
            return ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}