package com.example.rickandmortyapp.feature.characters.characters_fm.presentation

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.gone
import com.example.rickandmortyapp.core.pagination.PaginationUtil
import com.example.rickandmortyapp.core.show
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.CharactersPresenter.Companion.GENDER_FILTER
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.CharactersPresenter.Companion.SPECIES_FILTER
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.CharactersPresenter.Companion.STATUS_FILTER
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.adapter.CharactersAdapter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.FilterDialog
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterStatus
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.navigation_dialog.NavigationMenuDialog
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
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
        bottom_bar.setNavigationOnClickListener {
            presenter.onMenuClick()
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

    override fun openMenu() {
        if (childFragmentManager.findFragmentByTag(NavigationMenuDialog.TAG) == null) {
            NavigationMenuDialog.newInstance().show(childFragmentManager, NavigationMenuDialog.TAG)
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

    override fun setChipGroup(filterData: FilterModel?) {
        filterData?.let {
            chip_group_filter.show()
            it.status?.let {
                filter_status.text = getString(R.string.filter_chip_title, getString(R.string.filter_status_title), it.localeName)
                filter_status.show()

            } ?: run { filter_status.gone() }
            it.species?.let {
                filter_species.text =getString(R.string.filter_chip_title, getString(R.string.filter_species_title), it.localeName)
                filter_species.show()
            } ?: run { filter_species.gone() }
            it.gender?.let {
                filter_gender.text = getString(R.string.filter_chip_title, getString(R.string.filter_gender_title), it.localeName)
                filter_gender.show()
            } ?: run { filter_gender.gone() }
        } ?: run { chip_group_filter.gone() }
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