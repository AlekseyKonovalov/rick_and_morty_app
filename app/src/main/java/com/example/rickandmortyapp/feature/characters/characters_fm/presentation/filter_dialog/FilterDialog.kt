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
import com.example.rickandmortyapp.core.util.module
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterGender
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterSpecies
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterStatus
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_filter.*
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
        inflater.inflate(R.layout.bottomsheet_filter, container, false)

    override fun initListeners() {
        apply_btn.setOnClickListener {
            presenter.onApplyClick()
            dismissAllowingStateLoss()
        }
        //todo refactor to recycler
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

    override fun setGender(gender: FilterGender) {
        when (gender) {
            FilterGender.MALE -> {
                filter_gender_man.isChecked = true
            }
            FilterGender.FEMALE -> {
                filter_gender_woman.isChecked = true
            }
            FilterGender.GENDERLESS -> {
                filter_gender_genderless.isChecked = true
            }
            FilterGender.UNKNOWN -> {
                presenter.onGenderClick(FilterGender.UNKNOWN)
                filter_gender_unknown.isChecked = true
            }
        }
    }

    override fun setSpecies(species: FilterSpecies) {
        when (species) {
            FilterSpecies.HUMAN -> {
                filter_species_human.isChecked = true
            }
            FilterSpecies.ALIEN -> {
                filter_species_alien.isChecked = true
            }
        }
    }

    override fun setStatus(status: FilterStatus) {
        when (status) {
            FilterStatus.ALIVE -> {
                filter_status_alive.isChecked = true
            }
            FilterStatus.DEAD -> {
                filter_status_dead.isChecked = true
            }
            FilterStatus.UNKNOWN -> {
                filter_status_unknown.isChecked = true
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