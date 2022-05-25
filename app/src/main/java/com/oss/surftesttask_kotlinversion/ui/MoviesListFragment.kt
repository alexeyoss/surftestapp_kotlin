package com.oss.surftesttask_kotlinversion.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.surftesttask_kotlinversion.adapters.RecycleViewAdapter
import com.oss.surftesttask_kotlinversion.databinding.FragmentMovieListBinding
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.utils.AdapterOnClickListener
import com.oss.surftesttask_kotlinversion.utils.DataState
import com.oss.surftesttask_kotlinversion.utils.handleUI
import com.oss.surftesttask_kotlinversion.utils.replaceFragmentDataContainer
import com.oss.surftesttask_kotlinversion.viewmodels.MainStateEvent
import com.oss.surftesttask_kotlinversion.viewmodels.MoviesListViewModel
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class MoviesListFragment : Fragment(), AdapterOnClickListener {

    private lateinit var mBinding: FragmentMovieListBinding
    private var mAdapter = RecycleViewAdapter(this)

    val mViewModel by viewModels<MoviesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieListBinding.inflate(inflater, container, false)

        initRecycleView()

        subscribeObservers()
        mViewModel.setStateEvent(MainStateEvent.GetResultEvent)

        return mBinding.root
    }

    private fun initRecycleView() = with(mBinding) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun subscribeObservers() {
        mViewModel.dateState.observe(viewLifecycleOwner, Observer { dateState ->
            when (dateState) {
                is DataState.Success<List<Results>> -> {
                    displayRoundProgressBar(false)
                    mAdapter.setData(dateState.data)
                }
                is DataState.Error -> {
                    displayRoundProgressBar(false)
                    handleUI().showErrorFragment()
                }
                is DataState.Loading -> {
                    displayRoundProgressBar(true)
                }
            }
        })
    }

    private fun displayRoundProgressBar(isDisplayed: Boolean) = with(mBinding) {
        roundProgressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
//        mViewModel.getActualData.observe(viewLifecycleOwner) {
//            mAdapter.setData(it)
//            roundProgressBar.visibility = View.GONE
//        }
//
//        mViewModel.getErrorInfo.observe(viewLifecycleOwner) {
//            handleUI().showErrorFragment()
//        }
//
//        mViewModel.getLoadingStatus.observe(viewLifecycleOwner) {
//            if (it) roundProgressBar.visibility = View.VISIBLE else View.GONE
//        }

    override fun onItemClicked(position: Int) {
        replaceFragmentDataContainer(MovieDetailsFragment())
        handleUI().hideSearchBar(View.GONE)
    }
}