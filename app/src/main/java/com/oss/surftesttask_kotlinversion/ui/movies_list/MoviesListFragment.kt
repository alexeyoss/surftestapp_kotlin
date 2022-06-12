package com.oss.surftesttask_kotlinversion.ui.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.oss.surftesttask_kotlinversion.adapters.RecycleViewAdapter
import com.oss.surftesttask_kotlinversion.contract.navigator
import com.oss.surftesttask_kotlinversion.databinding.FragmentMovieListBinding
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.ui.Events
import com.oss.surftesttask_kotlinversion.ui.error_screen.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.ui.movie_details.MovieDetailsFragment
import com.oss.surftesttask_kotlinversion.utils.AdapterOnClickListener
import com.oss.surftesttask_kotlinversion.utils.DataState
import com.oss.surftesttask_kotlinversion.viewmodels.MoviesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment(), AdapterOnClickListener {

    private lateinit var mBinding: FragmentMovieListBinding
    private var mAdapter = RecycleViewAdapter(this)

    private val mViewModel: MoviesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieListBinding.inflate(inflater, container, false)

        initListeners()
        initRecycleView()
        subscribeObservers()

        if (savedInstanceState == null) mViewModel.setStateEvent(Events.GetResultEvent)

        return mBinding.root
    }

    private fun initRecycleView() = with(mBinding) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initListeners() = with(mBinding) {
        swipeRefreshLayout.setOnRefreshListener {
            mViewModel.setStateEvent(Events.GetResultEvent)
            swipeRefreshLayout.isRefreshing = false
        }
    }


    private fun subscribeObservers() {
        mViewModel.dataState.observe(viewLifecycleOwner) { dateState ->
            when (dateState) {
                is DataState.Success<List<Results>> -> {
                    displayProgressBar(visible = false)
                    mAdapter.setData(dateState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(visible = false)
                    navigator().showSnackBar()
                    navigator().launchScreen(ErrorScreenFragment())
                }
                is DataState.Loading<List<Results>> -> {
                    displayProgressBar(dateState.data.size, true)
                }
            }
        }
    }


    private fun displayProgressBar(dataSize: Int = -1, visible: Boolean = false) =
        with(mBinding) {
            when {
                dataSize == 0 -> {
                    roundProgressBar.isVisible = visible
                }
                dataSize > 0 -> {
                    lineProgressBar.isVisible = visible
                }
                else -> {
                    lineProgressBar.isVisible = visible
                    roundProgressBar.isVisible = visible
                }
            }
        }


    override fun onItemClicked(result: Results) {
        navigator().publishResult(result)
        navigator().launchScreen(MovieDetailsFragment())
    }
}

