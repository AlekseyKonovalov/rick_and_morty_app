package com.example.rickandmortyapp.feature.tab_container.presentation


import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.characters.presentation.CharactersFragment

class TabContainerFragment : BaseFragment(),
    TabContainerView {
    override val layoutRes: Int = R.layout.tab_container_fragment

    @InjectPresenter
    lateinit var presenter: TabContainerPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(TabContainerPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setCharactersScreen() {
        fragmentManager?.beginTransaction()
            ?.add(
                R.id.tab_fragments_container,
                CharactersFragment()
            )
            ?.commit()
    }

}