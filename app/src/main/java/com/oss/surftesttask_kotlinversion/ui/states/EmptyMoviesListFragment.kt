package com.oss.surftesttask_kotlinversion.ui.states

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.databinding.FragmentEmptyMovieListBinding

class EmptyMoviesListFragment : Fragment() {

    private lateinit var mBinding: FragmentEmptyMovieListBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentEmptyMovieListBinding.inflate(inflater, container, false)

        mBinding.tvEmptySearch.text =
            "$TV_EMPTY_SEARCH1 + ${getSearchText()} + $TV_EMPTY_SEARCH2"
        return mBinding.root
    }

    private fun getSearchText(): String = requireArguments().getString(ARG_SEARCH) as String

    companion object {

        const val ARG_SEARCH = "ARG_SEARCH"
        const val TV_EMPTY_SEARCH1 = "По запросу "
        const val TV_EMPTY_SEARCH2 = "\n ничего не найдено"

        @JvmStatic
        fun newInstance(searchText: String): EmptyMoviesListFragment {
            val args = Bundle().apply {
                putString(ARG_SEARCH, searchText)
            }
            val fragment = EmptyMoviesListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}