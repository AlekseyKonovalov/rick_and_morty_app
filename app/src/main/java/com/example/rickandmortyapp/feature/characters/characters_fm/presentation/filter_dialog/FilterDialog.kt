package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.util.module
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.adapter.ChipAdapter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.adapter.ChipModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_filter.*
import toothpick.Toothpick


class FilterDialog : MvpAppCompatDialogFragment(), FilterDialogView {

    private val scopeName: String = javaClass.simpleName

    private val adapterGender by lazy {
        ChipAdapter(
            presenter::onChipSelected
        )
    }
    private val adapterStatus by lazy {
        ChipAdapter(
            presenter::onChipSelected
        )
    }
    private val adapterSpecies by lazy {
        ChipAdapter(
            presenter::onChipSelected
        )
    }

    @InjectPresenter
    lateinit var presenter: FilterDialogPresenter

    @ProvidePresenter
    fun providePresenter(): FilterDialogPresenter {
        return Toothpick.openScopes((parentFragment as BaseFragment).scopeName, scopeName).module {
        }.getInstance(FilterDialogPresenter::class.java).also {
            Toothpick.closeScope(scopeName)
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireActivity(), theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottomsheet_filter, container, false)

    override fun initListeners() {
        apply_btn.setOnClickListener {
            presenter.onApplyClick()
            dismissAllowingStateLoss()
        }
    }

    override fun initViews(statuses: List<ChipModel>, genderList: List<ChipModel>, species: List<ChipModel>) {
        chip_group_status.adapter = adapterStatus

        chip_group_status.layoutManager = getLayoutManager()
        adapterStatus.updateDataSet(statuses)
        chip_group_species.adapter = adapterSpecies

        chip_group_species.layoutManager = getLayoutManager()
        adapterSpecies.updateDataSet(species)
        chip_group_gender.adapter = adapterGender

        chip_group_gender.layoutManager = getLayoutManager()
        adapterGender.updateDataSet(genderList)
    }

    private fun getLayoutManager() = ChipsLayoutManager.newBuilder(requireContext())
        .setChildGravity(Gravity.TOP)
        .setScrollingEnabled(true)
        .setGravityResolver { Gravity.CENTER }
        .setOrientation(ChipsLayoutManager.HORIZONTAL)
        .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT )
        .withLastRow(true)
        .build()

    override fun updateSpecies(species: List<ChipModel>) {
        chip_group_species.post { adapterSpecies.updateDataSet(species) }
    }

    override fun updateStatuses(statuses: List<ChipModel>) {
        chip_group_status.post { adapterStatus.updateDataSet(statuses) }
    }

    override fun updatedGenderList(genderList: List<ChipModel>) {
        chip_group_gender.post { adapterGender.updateDataSet(genderList) }
    }

    companion object {
        const val TAG = "FilterDialogTag"
        fun newInstance() = FilterDialog().apply {
            arguments = Bundle().apply {
            }
        }
    }
}