package com.example.rickandmortyapp.feature.tab_container.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.characters.presentation.CharactersFragment
import kotlinx.android.synthetic.main.tab_container_fragment.*


class TabContainerFragment : BaseFragment(),
    TabContainerView {
    override val layoutRes: Int = R.layout.tab_container_fragment

    @InjectPresenter
    lateinit var presenter: TabContainerPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(TabContainerPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        navigation_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_characters -> {
                    presenter.onClickTab(CHARACTERS)
                    true
                }
                R.id.action_episodes -> {
                    true
                }
                R.id.action_location -> {
                    true
                }
                R.id.action_account -> {
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun setFragment(keyFragment: String) {
        openFragment(keyFragment)
    }

    private fun openFragment(keyFragment: String) {
        val transaction = fragmentManager?.beginTransaction() ?: return
        with(transaction) {
            replace(R.id.tab_fragments_container, SCREENS[keyFragment] as Fragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        const val CHARACTERS = "CHARACTERS"
        const val EPISODES = "EPISODES"
        const val LOCATION = "LOCATION"
        const val PROFILE = "PROFILE"
        private val SCREENS = mapOf(
            CHARACTERS to CharactersFragment()
        )
    }

}