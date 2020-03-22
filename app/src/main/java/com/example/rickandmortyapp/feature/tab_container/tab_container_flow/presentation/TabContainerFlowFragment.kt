package com.example.rickandmortyapp.feature.tab_container.tab_container_flow.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation.CharacterDetailsFragment
import com.example.rickandmortyapp.feature.tab_container.tab_container_fm.presentation.TabContainerFragment
import com.example.rickandmortyapp.navigation.Flows
import java.util.*

class TabContainerFlowFragment : BaseFlowFragment(), TabContainerFlowView {

    override var fragments: LinkedList<String> = LinkedList()

    @InjectPresenter
    lateinit var presenter: TabContainerFlowPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(TabContainerFlowPresenter::class.java)

    override fun navigateToStartScreen(data: Any?) {
        navigateToScreen(Flows.TAB_CONTAINER.TAB_CONTAINER, data)
    }

    override fun navigateToScreen(screen: String, data: Any?) {
        when (screen) {
            Flows.TAB_CONTAINER.TAB_CONTAINER -> {
                routeToScreen(TabContainerFragment.getInstance(), Flows.TAB_CONTAINER.TAB_CONTAINER)
            }
        }
    }

    private fun routeToScreen(fragment: BaseFragment, tag: String) {
        fragmentManager?.let {
            fragments.add(tag)
            it.beginTransaction()
                .add(
                    R.id.fragmentContainerView,
                    fragment,
                    tag
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun removeLastScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}