package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation.CharacterDetailsFragment
import com.example.rickandmortyapp.feature.splash.splash_fm.presentation.SplashFragment
import com.example.rickandmortyapp.navigation.Flows
import kotlinx.android.synthetic.main.character_details_fragment.*
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind
import java.util.*


class CharacterDetailsFlowFragment : BaseFlowFragment(), CharacterDetailsFlowView {

    @InjectPresenter
    lateinit var presenter: CharacterDetailsFlowPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharacterDetailsFlowPresenter::class.java)

    override var fragments: LinkedList<String> = LinkedList()

    override fun navigateToStartScreen(data: Any?) {
        fragmentManager?.let {
            val arg =  arguments!!.getSerializable(key) as CharacterDetailsModel
            fragments.add(Flows.CHARACTER_DETAIL.SCREEN_CHARACTER_DETAIL)
            it.beginTransaction()
                .add(
                    R.id.fragmentContainerView,
                    CharacterDetailsFragment.getInstance(arg),
                    Flows.CHARACTER_DETAIL.SCREEN_CHARACTER_DETAIL
                )
                .commit()
        }
    }
    override fun removeLastScreen() {
        fragmentManager?.let { fm ->
            val fragment = fm.findFragmentByTag(fragments.last()) ?: return
            fragments.removeLast()
            fm.beginTransaction()
                .remove(fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit()
        }
        presenter.onBackPressed()
    }

    companion object {
        private const val key = "CharacterDetailsModel"

        fun getInstance(arg: CharacterDetailsModel): CharacterDetailsFlowFragment {
            return CharacterDetailsFlowFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(key, arg)
                }
            }
        }
    }

}