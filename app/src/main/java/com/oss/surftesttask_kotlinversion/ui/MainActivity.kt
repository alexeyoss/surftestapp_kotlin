package com.oss.surftesttask_kotlinversion.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.contract.Navigator
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.ui.movie_details.MovieDetailsFragment
import com.oss.surftesttask_kotlinversion.ui.movies_list.MoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.search.SearchFragment
import com.oss.surftesttask_kotlinversion.ui.states.EmptyMoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.states.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.utils.replaceFragmentDataContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var result: Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) with(supportFragmentManager) {
            beginTransaction()
                .add(R.id.searchContainer, SearchFragment.newInstance())
                .add(R.id.dataContainer, MoviesListFragment.newInstance())
                .commit()
            // TODO migrate to handling via Navigator
        } else {
            // TODO state recovery
        }
    }

    override fun <B, T> launch(screen: Class<B>?, args: T?) {
        when (screen) {
            MoviesListFragment::class -> {
                showSearchContainer(visible = true)
                replaceFragmentDataContainer(
                    MoviesListFragment.newInstance(), false
                )
            }
            MovieDetailsFragment::class -> {

            }
            ErrorScreenFragment::class -> {
                replaceFragmentDataContainer(
                    ErrorScreenFragment.newInstance()
                )
            }
            EmptyMoviesListFragment::class -> {
                replaceFragmentDataContainer(
                    EmptyMoviesListFragment.newInstance(args as String)
                )
            }
        }
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun showSearchContainer(visible: Boolean) = with(mBinding) {
        searchContainer.isVisible = visible
    }
}