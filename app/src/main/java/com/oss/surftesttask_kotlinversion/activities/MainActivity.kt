package com.oss.surftesttask_kotlinversion.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.fragments.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.fragments.MoviesListFragment
import com.oss.surftesttask_kotlinversion.fragments.SearchFragment
import com.oss.surftesttask_kotlinversion.support.ActivityUIhandler
import com.oss.surftesttask_kotlinversion.support.replaceFragmentDataContainer
import com.oss.surftesttask_kotlinversion.support.replaceFragmentSearchContainer

class MainActivity : AppCompatActivity(), ActivityUIhandler {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if (savedInstanceState == null) {
            replaceFragmentSearchContainer(SearchFragment())
            replaceFragmentDataContainer(MoviesListFragment())
        }

        initListeners()
    }

    private fun initListeners() = with(mBinding) {
        swipeRefreshLayout.setOnRefreshListener {
            replaceFragmentDataContainer(MoviesListFragment()) // Add save
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun showErrorFragment() {
        replaceFragmentDataContainer(ErrorScreenFragment())
    }

    override fun showEmptySearch() {
//        replaceFragment(EmptyMoviesListFragment.newInstance(mBinding.etSearch.text.toString()))
    }

    override fun showRecycleViewFragment() {
        replaceFragmentDataContainer(MoviesListFragment())
    }
}