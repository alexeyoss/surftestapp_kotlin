package com.oss.surftesttask_kotlinversion.ui

import android.os.Bundle
import android.view.View
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
import com.oss.surftesttask_kotlinversion.ui.custom_snackbar.CustomSnackBar
import com.oss.surftesttask_kotlinversion.ui.empty_movies_list_screen.EmptyMoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.error_screen.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.ui.movie_details_screen.MovieDetailsFragment
import com.oss.surftesttask_kotlinversion.ui.movies_list_screen.MoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.search_screen.SearchFragment
import com.oss.surftesttask_kotlinversion.utils.Constants
import com.oss.surftesttask_kotlinversion.utils.replaceFragmentDataContainer
import com.oss.surftesttask_kotlinversion.utils.replaceFragmentSearchContainer
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var mBinding: ActivityMainBinding
    private val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.dataContainer)

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            when (currentFragment) {
                is MovieDetailsFragment -> showSearchContainer(false)
                is MoviesListFragment -> showSearchContainer(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) {
            replaceFragmentSearchContainer(SearchFragment())
            replaceFragmentDataContainer(MoviesListFragment())
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun launchScreen(screen: Fragment) {
        when (screen) {
            is MoviesListFragment -> {
                replaceFragmentDataContainer(MoviesListFragment())
            }
            is MovieDetailsFragment -> {
                replaceFragmentDataContainer(MovieDetailsFragment(), addStack = true)
                showSearchContainer(isVisible = false)
            }
            is ErrorScreenFragment -> {
                replaceFragmentDataContainer(ErrorScreenFragment())
            }
            is EmptyMoviesListFragment -> {
                replaceFragmentDataContainer(EmptyMoviesListFragment())
            }
        }
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun showSnackBar() {
        CustomSnackBar.make(
            this.findViewById(android.R.id.content),
            title = Constants.SNACKBAR_TEXT
        ).show()
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