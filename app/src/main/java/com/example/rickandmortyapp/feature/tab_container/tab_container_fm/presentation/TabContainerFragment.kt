package com.example.rickandmortyapp.feature.tab_container.tab_container_fm.presentation


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.CharactersFragment
import com.example.rickandmortyapp.feature.tab_container.episodes.presentation.EpisodesFragment
import com.example.rickandmortyapp.feature.tab_container.location.presentation.LocationFragment
import com.example.rickandmortyapp.feature.tab_container.profile.presentation.ProfileFragment
import com.example.rickandmortyapp.feature.tab_container.tab_container_fm.model.TabScreen
import kotlinx.android.synthetic.main.tab_container_fragment.*


class TabContainerFragment : BaseFragment(), TabContainerView {

    override val layoutRes: Int = R.layout.tab_container_fragment

    @InjectPresenter
    lateinit var presenter: TabContainerPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(TabContainerPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        navigation_bar.setOnNavigationItemSelectedListener { menuItem ->
            SCREENS.find { it.resId == menuItem.itemId }?.name?.let {
                presenter.onClickTab(it)
            }
            true
        }
    }

    override fun setFragment(keyFragment: String) {
        openFragment(keyFragment)
    }

    private fun openFragment(keyFragment: String) {
        val transaction = fragmentManager?.beginTransaction() ?: return
        with(transaction) {
            replace(
                R.id.tab_fragments_container,
                SCREENS.find { it.name == keyFragment }?.fragment as Fragment
            )
            addToBackStack(null)
            commit()
        }
    }

    companion object {

        const val CHARACTERS = "CHARACTERS"
        private const val EPISODES = "EPISODES"
        private const val LOCATION = "LOCATION"
        private const val PROFILE = "PROFILE"

        private val SCREENS = listOf(
            TabScreen(R.id.action_characters, CHARACTERS, CharactersFragment.getInstance()),
            TabScreen(R.id.action_episodes, EPISODES, EpisodesFragment.getInstance()),
            TabScreen(R.id.action_location, LOCATION, LocationFragment.getInstance()),
            TabScreen(R.id.action_account, PROFILE, ProfileFragment.getInstance())
        )

        fun getInstance(): TabContainerFragment {
            return TabContainerFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}