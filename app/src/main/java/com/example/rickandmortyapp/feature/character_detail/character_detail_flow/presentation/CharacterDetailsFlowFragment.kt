package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation.CharacterDetailsFragment
import com.example.rickandmortyapp.feature.splash.splash_fm.presentation.SplashFragment
import kotlinx.android.synthetic.main.character_details_fragment.*
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind


class CharacterDetailsFlowFragment : BaseFragment(), CharacterDetailsFlowView {

    override val layoutRes: Int = R.layout.fragment_container_view

    @InjectPresenter
    lateinit var presenter: CharacterDetailsFlowPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharacterDetailsFlowPresenter::class.java)

    override fun installModules(scope: Scope) {
        super.installModules(scope)
        scope.installModules(object : Module() {
            init {
                bind<CharacterDetailsModel>().toInstance(arguments!!.getSerializable(key) as CharacterDetailsModel)
            }
        })
    }


    override fun navigateToStartScreen(data: Any?) {
        fragmentManager?.let {
            it.beginTransaction()
                .add(
                    R.id.fragmentContainerView,
                    CharacterDetailsFragment.getInstance(data as CharacterDetailsModel)
                )
                .commit()
        }
    }
    companion object {
        private const val key = "CharacterDetailsModel"

        fun getInstance(arg: CharacterDetailsModel): CharacterDetailsFlowFragment {
            return CharacterDetailsFlowFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(key, arg)
                }
            }
        }
    }

}