package com.oss.surftesttask_kotlinversion.ui.empty_movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.databinding.FragmentEmptyMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyMoviesListFragment : Fragment() {

    private lateinit var mBinding: FragmentEmptyMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentEmptyMovieListBinding.inflate(inflater, container, false)
        mBinding.alias = getAlias()

        return mBinding.root
    }

    private fun getAlias(): String = requireArguments().getString(ARG_SEARCH) as String

    companion object {
        const val ARG_SEARCH = "ARG_SEARCH"

        @JvmStatic
        fun newInstance(searchText: String): EmptyMoviesListFragment {
            val fragment = EmptyMoviesListFragment()
            fragment.arguments = bundleOf(ARG_SEARCH to searchText)
            return fragment
        }
    }
}