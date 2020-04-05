package com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.setTitle
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel
import kotlinx.android.synthetic.main.character_details_fragment.*


class CharacterDetailsFragment : BaseFragment(), CharacterDetailsView {

    override val layoutRes: Int = R.layout.character_details_fragment

    @InjectPresenter
    lateinit var presenter: CharacterDetailsPresenter

    private val characterDetailsModel: CharacterModel by lazy { arguments!!.getSerializable(key) as CharacterModel }

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharacterDetailsPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setData(characterDetailsModel)
    }

    override fun initViews(data: CharacterModel) {
        main_toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        main_toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_favorite) {
                presenter.onOnFavoriteIconClick()
            }
            true
        }

        main_appbar.setTitle(main_collapsing, data.name)
        Glide.with(requireContext())
            .load(data.imageUrl)
            .into(main_backdrop)
        ch_name.text = data.name
        ch_gender.text = data.gender
        ch_species.text = data.species
        ch_status.text = data.status
        ch_current_location.text = data.currentLocationName
        ch_origin_location.text = data.originName

        thumb_down.setOnClickListener {
            presenter.onThumbDownClick()
        }
        thumb_up.setOnClickListener {
            presenter.onThumbUpClick()
        }
    }

    override fun updateFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            main_toolbar.menu.getItem(0).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite)
        } else {
            main_toolbar.menu.getItem(0).icon = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_favorite_inactive
            )
        }
    }

    override fun setThumbDownActive() {
        setRateViewColors(
            thumbDownColor = R.color.colorRed,
            thumbUpColor = R.color.colorMiddleGray
        )
    }

    override fun setThumbUpActive() {
        setRateViewColors(
            thumbDownColor = R.color.colorMiddleGray,
            thumbUpColor = R.color.colorGreen
        )
    }

    override fun setThumbInactive() {
        setRateViewColors(
            thumbDownColor = R.color.colorMiddleGray,
            thumbUpColor = R.color.colorMiddleGray
        )
    }

    private fun setRateViewColors(thumbUpColor: Int, thumbDownColor: Int) {
        thumb_down.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                thumbDownColor
            ), android.graphics.PorterDuff.Mode.SRC_IN
        )
        thumb_up.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                thumbUpColor
            ), android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    companion object {
        private const val key = "CharacterDetailsModel"

        fun getInstance(arg: CharacterModel): CharacterDetailsFragment {
            return CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(key, arg)
                }
            }
        }
    }

}