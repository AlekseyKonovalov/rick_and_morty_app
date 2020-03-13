package com.example.rickandmortyapp.feature.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.gone
import com.example.rickandmortyapp.core.show
import com.example.rickandmortyapp.feature.characters.presentation.adapter.CharactersAdapter
import com.example.rickandmortyapp.feature.characters.presentation.model.CharacterModel
import kotlinx.android.synthetic.main.characters_fragment.*


class CharactersFragment : BaseFragment(), CharactersView {

    override val layoutRes: Int = R.layout.characters_fragment


    @InjectPresenter
    lateinit var presenter: CharactersPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharactersPresenter::class.java)

    private val charactersAdapter by lazy { CharactersAdapter(presenter::onCharacterItemClick, presenter::onFavoriteCharacterItemClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        characters_list.layoutManager = GridLayoutManager(requireContext(), NUMBERS_OF_COLUMN)
        characters_list.adapter = charactersAdapter
    }

    override fun hideProgress() {
        progressbar.gone()
    }

    override fun showProgress() {
        progressbar.show()
    }

    override fun setItems(items: List<CharacterModel>) {
        charactersAdapter.updateDataSet(items)
    }

    companion object {
        private const val NUMBERS_OF_COLUMN = 2
    }

}