package com.example.rickandmortyapp.feature.tab_container.characters.presentation

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.pagination.PaginationUtil
import com.example.rickandmortyapp.feature.splash.splash_flow.presentation.SplashFlowFragment
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.adapter.CharactersAdapter
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.characters_fragment.*

class CharactersFragment : BaseFragment(), CharactersView {

    override val layoutRes: Int = R.layout.characters_fragment
    private var paginationSubscription: Disposable? = null

    @InjectPresenter
    lateinit var presenter: CharactersPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharactersPresenter::class.java)

    private val charactersAdapter by lazy { CharactersAdapter(presenter::onCharacterItemClick, presenter::onFavoriteCharacterItemClick) }

    override fun initListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    override fun initAdapter() {
        characters_list.layoutManager = GridLayoutManager(requireContext(), NUMBERS_OF_COLUMN)
        characters_list.adapter = charactersAdapter
    }

    override fun setItems(items: List<CharacterModel>) {
        swipeRefreshLayout.isRefreshing = false
        charactersAdapter.updateDataSet(items)
    }

    override fun updateCharacterSubscription(paginationSize: Int) {
        paginationSubscription?.dispose()
        paginationSubscription = PaginationUtil.getPaginationObservable(
            characters_list,
            paginationSize
        ).subscribe { presenter.onPageScrolled() }
    }

    companion object {
        private const val NUMBERS_OF_COLUMN = 2

        fun getInstance(): CharactersFragment {
            return CharactersFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }

    }

}