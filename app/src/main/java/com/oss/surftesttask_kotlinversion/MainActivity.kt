package com.oss.surftesttask_kotlinversion

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.navigator.Navigator
import com.oss.surftesttask_kotlinversion.ui.movies_list.MoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.search.SearchFragment
import com.oss.surftesttask_kotlinversion.ui.states.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.utils.replaceFragmentDataContainer
import com.oss.surftesttask_kotlinversion.utils.replaceFragmentSearchContainer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) {
            replaceFragmentSearchContainer(SearchFragment())
            replaceFragmentDataContainer(MoviesListFragment())
        }

        initListeners()
    }


    private fun initListeners() = with(mBinding) {
        swipeRefreshLayout.setOnRefreshListener {
            replaceFragmentDataContainer(MoviesListFragment()) // Add save instance
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun showErrorFragment() {
        replaceFragmentDataContainer(ErrorScreenFragment())
    }

    override fun showEmptySearch() {
    }

    override fun showRecycleViewFragment() {
        replaceFragmentDataContainer(MoviesListFragment())
    }

    override fun hideSearchBar(status: Int) = with(mBinding) {
        when (status) {
            View.GONE -> searchContainer.visibility = View.GONE
            View.VISIBLE -> searchContainer.visibility = View.VISIBLE
        }
    }
}