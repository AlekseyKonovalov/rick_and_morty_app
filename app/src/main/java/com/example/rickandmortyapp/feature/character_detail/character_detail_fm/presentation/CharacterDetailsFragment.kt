package com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import kotlinx.android.synthetic.main.character_details_fragment.*
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind


class CharacterDetailsFragment : BaseFragment(), CharacterDetailsView {

    override val layoutRes: Int = R.layout.character_details_fragment

    @InjectPresenter
    lateinit var presenter: CharacterDetailsPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharacterDetailsPresenter::class.java)

    override fun installModules(scope: Scope) {
        super.installModules(scope)
        scope.installModules(object : Module() {
            init {
                bind<CharacterDetailsModel>().toInstance(arguments!!.getSerializable(key) as CharacterDetailsModel)
            }
        })
    }

    override fun initViews(data: CharacterDetailsModel) {
        Glide.with(requireContext())
            .load(data.imageUrl)
            .into(main_backdrop)
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    companion object {
        private const val key = "CharacterDetailsModel"

        fun getInstance(arg: CharacterDetailsModel): CharacterDetailsFragment {
            return CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(key, arg)
                }
            }
        }
    }

}