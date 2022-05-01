package com.oss.surftesttask_kotlinversion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oss.surftesttask_kotlinversion.databinding.FragmentSearchBinding
import com.oss.surftesttask_kotlinversion.viewmodels.MoviesListViewModel

class SearchFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchBinding

//    private val mViewModel: MoviesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false)

        initListeners()
        return mBinding.root
    }


    private fun initListeners() = with(mBinding) {
//        if (!etSearch.hasOnClickListeners()) {
//            etSearch.doAfterTextChanged {
//                if (!it.toString().isEmpty()) {
//                    mViewModel.getSearchMovies(it.toString())
//                } else {
//                    mViewModel.getMovies()
//                }
//            }
//        }
    }
}