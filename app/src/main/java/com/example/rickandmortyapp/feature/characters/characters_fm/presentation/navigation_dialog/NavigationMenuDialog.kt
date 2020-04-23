package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.navigation_dialog

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
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_navigation_menu.*
import toothpick.Toothpick


class NavigationMenuDialog : MvpAppCompatDialogFragment(), NavigationMenuView {

    private val scopeName: String = javaClass.simpleName

    @InjectPresenter
    lateinit var presenter: NavigationDialogPresenter

    @ProvidePresenter
    fun providePresenter(): NavigationDialogPresenter {
        return Toothpick.openScopes((parentFragment as BaseFragment).scopeName, scopeName).module {
        }.getInstance(NavigationDialogPresenter::class.java).also {
            Toothpick.closeScope(scopeName)
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireActivity(), theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_navigation_menu, container, false)

    override fun initListeners() {
        layout_profile.setOnClickListener {
            presenter.onProfileClick()
        }
    }

    companion object {
        const val TAG = "NavigationMenuDialog"
        fun newInstance() = NavigationMenuDialog().apply {
            arguments = Bundle().apply {
            }
        }
    }
}