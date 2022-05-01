package com.oss.surftesttask_kotlinversion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.surftesttask_kotlinversion.adapters.RecycleViewAdapter
import com.oss.surftesttask_kotlinversion.databinding.FragmentMovieListBinding
import com.oss.surftesttask_kotlinversion.support.AdapterOnClickListener
import com.oss.surftesttask_kotlinversion.support.handleUI
import com.oss.surftesttask_kotlinversion.viewmodels.MoviesListViewModel

class MoviesListFragment : Fragment(), AdapterOnClickListener {
    private lateinit var mBinding: FragmentMovieListBinding
    private var mAdapter = RecycleViewAdapter(this)

    private val mViewModel: MoviesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieListBinding.inflate(inflater, container, false)

        initRecycleView()
        initViewModels()

        return mBinding.root
    }

    private fun initRecycleView() = with(mBinding) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initViewModels() = with(mBinding) {
        mViewModel.getLastData().observe(viewLifecycleOwner) {
            mAdapter.setData(it)
            roundProgressBar.visibility = View.GONE
        }

        mViewModel.getErrorMessage().observe(viewLifecycleOwner) {
            handleUI().showErrorFragment()
        }

        mViewModel.getLoading().observe(viewLifecycleOwner) {
            if (it) roundProgressBar.visibility = View.VISIBLE else View.GONE
        }
    }

    override fun onItemClicked(position: Int) {
        // replace(MovieDetailsFragment()) need injection for looking on one ViewModel
        Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
    }
}