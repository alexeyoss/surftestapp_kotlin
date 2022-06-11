package com.oss.surftesttask_kotlinversion.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.contract.Navigator
import com.oss.surftesttask_kotlinversion.contract.ResultListener
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.ui.movie_details.MovieDetailsFragment
import com.oss.surftesttask_kotlinversion.ui.movies_list.MoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.search.SearchFragment
import com.oss.surftesttask_kotlinversion.ui.empty_movies_list.EmptyMoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.error_screen.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.utils.replaceFragmentDataContainer
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) with(supportFragmentManager) {
            beginTransaction()
                .add(R.id.searchContainer, SearchFragment(), SearchFragment.toString())
                .add(R.id.dataContainer, MoviesListFragment())
                .commit()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun launchScreen(screen: Fragment) {
        when (screen) {
            is MoviesListFragment -> {
                replaceFragmentDataContainer(MoviesListFragment(), false)
            }
            is MovieDetailsFragment -> {
                replaceFragmentDataContainer(MovieDetailsFragment(), addStack = true)
            }
            is ErrorScreenFragment -> {
                replaceFragmentDataContainer(ErrorScreenFragment())
            }
            is EmptyMoviesListFragment -> {
                replaceFragmentDataContainer(EmptyMoviesListFragment())
            }
        }
    }

    override fun goBack(result: Any?) {
        onBackPressed()
    }

    override fun <T : Serializable> publishResult(result: T) {
        supportFragmentManager.setFragmentResult(
            result::class.java.name,
            bundleOf(KEY_RESULT to result)
        )
    }

    override fun <T : Serializable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
        supportFragmentManager.setFragmentResultListener(
            clazz.name,
            owner,
            FragmentResultListener { _, bundle ->
                listener.invoke(bundle.getSerializable(KEY_RESULT) as T)
            })
    }

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            super.onFragmentAttached(fm, f, context)
            when (fm.findFragmentByTag(SearchFragment.toString())) {
                is MovieDetailsFragment -> showSearchContainer(false)
            }
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            super.onFragmentDetached(fm, f)
            when (fm.findFragmentByTag(SearchFragment.toString())) {
                is MovieDetailsFragment -> showSearchContainer(isVisible = true)
            }
        }
    }

    private fun showSearchContainer(isVisible: Boolean) = with(mBinding) {
        searchContainer.isVisible = isVisible
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    companion object {
        @JvmStatic
        private val KEY_RESULT = "KEY_RESULT"
    }
}