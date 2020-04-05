package com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.setTitle
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.character_details_fragment.*


class CharacterDetailsFragment : BaseFragment(), CharacterDetailsView {

    override val layoutRes: Int = R.layout.character_details_fragment

    @InjectPresenter
    lateinit var presenter: CharacterDetailsPresenter

    private val characterDetailsModel: CharacterDetailsModel by lazy { arguments!!.getSerializable(key) as CharacterDetailsModel }

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharacterDetailsPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setData(characterDetailsModel)
    }

    override fun initViews(data: CharacterDetailsModel) {
        main_toolbar.setNavigationOnClickListener {
           requireActivity().onBackPressed()
        }
        main_appbar.setTitle(main_collapsing, data.name)
        Glide.with(requireContext())
            .load(data.imageUrl)
            .into(main_backdrop)
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