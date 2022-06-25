package com.oss.surftesttask_kotlinversion.ui.movies_list_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.surftesttask_kotlinversion.contract.navigator
import com.oss.surftesttask_kotlinversion.databinding.FragmentMovieListBinding
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.models.States.DataState
import com.oss.surftesttask_kotlinversion.models.States.LikeState
import com.oss.surftesttask_kotlinversion.ui.Events
import com.oss.surftesttask_kotlinversion.ui.error_screen.ErrorScreenFragment
import com.oss.surftesttask_kotlinversion.ui.movie_details_screen.MovieDetailsFragment
import com.oss.surftesttask_kotlinversion.utils.refreshes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MoviesListFragment : Fragment(), AdapterOnClickListener {

    private lateinit var mBinding: FragmentMovieListBinding
    private var mAdapter = MoviesListAdapter(this)

    private val mViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieListBinding.inflate(inflater, container, false)

        initListeners()
        initRecycleView()
        subscribeObservers()

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
        // TODO flexible update cause it make the INITIALIZING request (without query parameter)
        swipeRefreshLayout
            .refreshes()
            .onEach {
                mViewModel.setStateEvent(Events.GetResultEvent)
                swipeRefreshLayout.isRefreshing = false
            }
            .launchIn(lifecycleScope)
    }


    private fun subscribeObservers() {
        mViewModel.dataState.observe(viewLifecycleOwner) { dateState ->
            when (dateState) {
                is DataState.Success<List<Results>> -> {
                    displayProgressBar(visible = false)

                    with(mBinding) {
                        dataSize = dateState.data.size
                        query = mViewModel.queryText.value
                    }

                    if (dateState.data.isNotEmpty()) {
                        mAdapter.setData(dateState.data)
                    }

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

        mViewModel.likeState.observe(viewLifecycleOwner) { likeState ->
            when (likeState) {
                is LikeState.Success -> {
                    displayProgressBar(visible = false)

                    if (likeState.data) navigator().addToFavorite()
                    else navigator().removeFromFavorite()
                }

                is LikeState.Loading -> {
                    displayProgressBar(0, visible = false)
                }

                is LikeState.Error -> {
                    navigator().showSnackBar() // TODO streamline the custom snackBar view, need to set a custom text
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

    override fun onViewClicked(result: Results) {
        navigator().publishResult(result)
        navigator().launchScreen(MovieDetailsFragment())
    }

    override fun onLikeClicked(movieId: Int, isLiked: Boolean) {
        mViewModel.setStateEvent(Events.LikeMovie(movieId, isLiked))
    }
}

