package com.oss.surftesttask_kotlinversion.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oss.surftesttask_kotlinversion.databinding.FragmentSearchBinding
import com.oss.surftesttask_kotlinversion.viewmodels.MoviesListViewModel
import dagger.hilt.android.scopes.FragmentScoped


class SearchFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchBinding
    private val mViewModel: MoviesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false)

        initListeners()
        return mBinding.root
    }

    private fun initListeners() = with(mBinding) {
        // TODO changeListener based on coroutines
    }
}