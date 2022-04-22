package com.oss.surftesttask_kotlinversion.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.fragments.EmptyMoviesListFragment
import com.oss.surftesttask_kotlinversion.fragments.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.fragments.MoviesListFragment
import com.oss.surftesttask_kotlinversion.support.UiHandler
import com.oss.surftesttask_kotlinversion.support.chooseCall
import com.oss.surftesttask_kotlinversion.support.replaceFragment

class MainActivity : AppCompatActivity(), UiHandler {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if (savedInstanceState == null) {
            replaceFragment(MoviesListFragment())
        }
        initListeners()
    }

    private fun initListeners() = with(mBinding) {
        swipeRefreshLayout.setOnRefreshListener {
            // INVOKE GET request
            swipeRefreshLayout.isRefreshing = false
        }

        etSearch.doAfterTextChanged { chooseCall(it) }
    }

    override fun showErrorFragment() {
        replaceFragment(ErrorScreenFragment())
    }

    override fun showEmptySearch() {
        replaceFragment(EmptyMoviesListFragment.newInstance(mBinding.etSearch.text.toString()))
    }

    override fun showRecycleViewFragment() {
        // INVOKE GET request depends on the TEXT in the Search Line
        replaceFragment(MoviesListFragment())
    }
}