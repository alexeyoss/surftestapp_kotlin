package com.oss.surftesttask_kotlinversion.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.oss.surftesttask_kotlinversion.R
import com.oss.surftesttask_kotlinversion.contract.Navigator
import com.oss.surftesttask_kotlinversion.contract.ResultListener
import com.oss.surftesttask_kotlinversion.databinding.ActivityMainBinding
import com.oss.surftesttask_kotlinversion.ui.movie_details.MovieDetailsFragment
import com.oss.surftesttask_kotlinversion.ui.movies_list.MoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.search.SearchFragment
import com.oss.surftesttask_kotlinversion.ui.states.EmptyMoviesListFragment
import com.oss.surftesttask_kotlinversion.ui.states.ErrorScreenFragment
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
                .add(R.id.searchContainer, SearchFragment.newInstance())
                .add(R.id.dataContainer, MoviesListFragment.newInstance())
                .commit()
            // TODO migrate to handling via Navigator
        } else {
            // TODO state recovery
        }
    }

    override fun <T : Serializable> launch(screen: String, args: T?) {
        when (screen) {
            MoviesListFragment::javaClass.name -> {
                showSearchContainer(visible = true)
                replaceFragmentDataContainer(
                    MoviesListFragment.newInstance(), false
                )
            }
            MovieDetailsFragment::javaClass.name -> {
                supportFragmentManager.setFragmentResult(
                    screen::class.java.name, bundleOf(
                        KEY_SEARCH to args
                    )
                )
            }
            ErrorScreenFragment::javaClass.name -> {
                replaceFragmentDataContainer(
                    ErrorScreenFragment.newInstance()
                )
            }
            EmptyMoviesListFragment::javaClass.name -> {
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

    override fun <T : Serializable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
        supportFragmentManager.setFragmentResultListener(
            clazz.name,
            owner,
            FragmentResultListener { key, bundle ->
                listener.invoke(bundle.getSerializable(KEY_SEARCH) as T)
            }
        )
    }

    companion object {
        @JvmStatic
        private val KEY_SEARCH = "KEY_SEARCH"
    }
}