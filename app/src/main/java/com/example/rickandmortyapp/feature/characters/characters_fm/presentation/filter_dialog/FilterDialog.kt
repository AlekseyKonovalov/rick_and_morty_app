package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.core.module
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterGender
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterSpecies
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterStatus
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_filter.*
import kotlinx.android.synthetic.main.dialog_navigation_menu.*
import toothpick.Toothpick


class FilterDialog : MvpAppCompatDialogFragment(), FilterDialogView {

    private val scopeName: String = javaClass.simpleName

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
        inflater.inflate(R.layout.dialog_filter, container, false)

    override fun initListeners() {
        apply_btn.setOnClickListener {
            presenter.onApplyClick()
            dismissAllowingStateLoss()
        }
        chip_group_status.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.filter_status_alive -> {
                    presenter.onStatusClick(FilterStatus.ALIVE)
                }
                R.id.filter_status_dead -> {
                    presenter.onStatusClick(FilterStatus.DEAD)
                }
                R.id.filter_status_unknown -> {
                    presenter.onStatusClick(FilterStatus.UNKNOWN)
                }
                else -> {
                    presenter.onStatusClick(null)
                }
            }
        }
        chip_group_species.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.filter_species_human -> {
                    presenter.onSpeciesClick(FilterSpecies.HUMAN)
                }
                R.id.filter_species_alien -> {
                    presenter.onSpeciesClick(FilterSpecies.ALIEN)
                }
                else -> {
                    presenter.onSpeciesClick(null)
                }
            }
        }
        chip_group_gender.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.filter_gender_man -> {
                    presenter.onGenderClick(FilterGender.MALE)
                }
                R.id.filter_gender_woman -> {
                    presenter.onGenderClick(FilterGender.FEMALE)
                }
                R.id.filter_gender_genderless -> {
                    presenter.onGenderClick(FilterGender.GENDERLESS)
                }
                R.id.filter_gender_unknown -> {
                    presenter.onGenderClick(FilterGender.UNKNOWN)
                }
                else -> {
                    presenter.onGenderClick(null)
                }
            }
        }
    }

    companion object {
        const val TAG = "FilterDialogTag"
        fun newInstance() = FilterDialog().apply {
            arguments = Bundle().apply {
            }
        }
    }
}