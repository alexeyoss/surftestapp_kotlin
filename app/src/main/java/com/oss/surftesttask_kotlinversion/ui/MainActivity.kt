package com.oss.surftesttask_kotlinversion.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.navigator.Navigator
import com.oss.surftesttask_kotlinversion.ui.base.BaseScreen
import com.oss.surftesttask_kotlinversion.ui.movies_list.MoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.search.SearchFragment
import com.oss.surftesttask_kotlinversion.ui.states.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.utils.replaceFragmentDataContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) with(supportFragmentManager) {
            beginTransaction()
                .replace(R.id.searchContainer, SearchFragment.newInstance())
                .replace(R.id.dataContainer, MoviesListFragment.newInstance())
                .commit()
            // TODO migrate to handling via Navigator
        } else {
            // TODO state recovery
        }
    }

    override fun launch(screen: BaseScreen, addStack: Boolean) {
    }


    override fun showErrorFragment() {
        replaceFragmentDataContainer(ErrorScreenFragment())
    }

    override fun showEmptySearch() {
    }

    override fun showRecycleViewFragment() {
        replaceFragmentDataContainer(MoviesListFragment())
    }

    override fun showSearchBar(visible: Boolean) = with(mBinding) {
        searchContainer.isVisible = visible
    }
}