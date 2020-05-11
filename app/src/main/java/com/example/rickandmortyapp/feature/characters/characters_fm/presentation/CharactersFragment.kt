package com.example.rickandmortyapp.feature.characters.characters_fm.presentation

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.util.gone
import com.example.rickandmortyapp.core.pagination.PaginationUtil
import com.example.rickandmortyapp.core.util.show
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.CharactersPresenter.Companion.GENDER_FILTER
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.CharactersPresenter.Companion.SPECIES_FILTER
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.CharactersPresenter.Companion.STATUS_FILTER
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.adapter.CharactersAdapter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.FilterDialog
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.characters_fragment.*

class CharactersFragment : BaseFragment(), CharactersView {

    override val layoutRes: Int = R.layout.characters_fragment
    private var paginationSubscription: Disposable? = null

    @InjectPresenter
    lateinit var presenter: CharactersPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharactersPresenter::class.java)


    private val charactersAdapter by lazy {
        CharactersAdapter(
            presenter::onCharacterItemClick,
            presenter::onFavoriteCharacterItemClick
        )
    }

    override fun initListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter.onRefresh()
        }
        bottom_bar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_search -> {
                    presenter.onSearchClick()
                }
                R.id.action_filter -> {
                    presenter.onFilterDialogClick()
                }
            }
            true
        }
        filter_status.setOnClickListener {
            presenter.onChipsClick(STATUS_FILTER)
        }
        filter_species.setOnClickListener {
            presenter.onChipsClick(SPECIES_FILTER)
        }
        filter_gender.setOnClickListener {
            presenter.onChipsClick(GENDER_FILTER)
        }
        chip_group_filter.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.filter_status -> {
                    presenter.onChipsClick(STATUS_FILTER)
                }
                R.id.filter_species -> {
                    presenter.onChipsClick(SPECIES_FILTER)
                }
                R.id.filter_gender -> {
                    presenter.onChipsClick(GENDER_FILTER)
                }
            }
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

    override fun openFilterDialog() {
        if (childFragmentManager.findFragmentByTag(FilterDialog.TAG) == null) {
            FilterDialog.newInstance().show(childFragmentManager, FilterDialog.TAG)
        }
    }


    override fun setChipGroup(status: String?, species: String?, gender: String?) {
        if (status == null && species == null && gender == null) {
            chip_group_filter.gone()
            return
        }
        chip_group_filter.show()
        updateChipVisibility(filter_status, status, getString(R.string.filter_status_title))
        updateChipVisibility(filter_species, species, getString(R.string.filter_species_title))
        updateChipVisibility(filter_gender, gender, getString(R.string.filter_gender_title))
    }

    private fun updateChipVisibility(chip: Chip, text: String?, title: String) {
        text?.let {
            showFilterChip(
                chip,
                getString(R.string.filter_chip_title, title, it)
            )
        } ?: run { chip.gone() }
    }

    private fun showFilterChip(chip: Chip, text: String) {
        chip.text = text
        chip.show()
    }

    override fun showError(error: String) {
        Snackbar.make(container, error, Snackbar.LENGTH_LONG).show()
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