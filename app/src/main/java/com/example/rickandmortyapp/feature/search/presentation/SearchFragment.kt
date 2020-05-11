package com.example.rickandmortyapp.feature.search.presentation

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.util.gone
import com.example.rickandmortyapp.core.util.hideKeyboard
import com.example.rickandmortyapp.core.util.show
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.adapter.CharactersAdapter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.search_fragment.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment(), SearchView {
    override val layoutRes: Int = R.layout.search_fragment

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    private val disposables = CompositeDisposable()

    private val charactersAdapter by lazy {
        CharactersAdapter(
            presenter::onCharacterItemClick,
            presenter::onFavoriteCharacterItemClick
        )
    }

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(SearchPresenter::class.java)

    override fun initViews() {
        list_rv.layoutManager = GridLayoutManager(requireContext(), NUMBERS_OF_COLUMN)
        list_rv.adapter = charactersAdapter

        disposables += RxTextView.textChanges(search_key_edt)
            .skipInitialValue()
            .debounce(TIMEOUT_DEBOUNCE, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenter.dispatchSearchRequest(it.toString())
            }, {
                Timber.e(it)
            })

        list_search_clear_btn.setOnClickListener {
            search_key_edt.setText("")
        }
        navIcon.setOnClickListener {
            presenter.onBackPressed()
        }
    }

    override fun setItems(items: List<CharacterModel>) {
        charactersAdapter.updateDataSet(items)
    }

    override fun hidePlaceholder() {
        search_placeholder_layout.gone()
    }

    override fun showPlaceholder() {
        search_placeholder_layout.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    override fun showError(error: String) {
        Snackbar.make(container, error, Snackbar.LENGTH_LONG).show()
    }

    override fun closeView() {
        with(requireActivity()) {
            hideKeyboard()
            onBackPressed()
        }
    }

    companion object {
        private const val TIMEOUT_DEBOUNCE = 500L
        private const val NUMBERS_OF_COLUMN = 2
        fun getInstance(): SearchFragment {
            return SearchFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}