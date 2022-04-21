package com.oss.surftesttask_kotlinversion.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.databinding.FragmentEmptySearchBinding
import com.oss.surftesttask_kotlinversion.support.Constants

class EmptySearchFragment : Fragment() {

    private lateinit var mBinding: FragmentEmptySearchBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentEmptySearchBinding.inflate(inflater, container, false)

        mBinding.tvEmptySearch.text =
            "${Constants.TV_EMPTY_SEARCH1} + ${getSearchText()} + ${Constants.TV_EMPTY_SEARCH2}"
        return mBinding.root
    }

    private fun getSearchText(): String = requireArguments().getString(ARG_SEARCH) as String

    companion object {

        @JvmStatic
        private val ARG_SEARCH = "ARG_SEARCH"

        @JvmStatic
        fun newInstance(searchText: String): EmptySearchFragment {
            val args = Bundle().apply {
                putString(ARG_SEARCH, searchText)
            }
            val fragment = EmptySearchFragment()
            fragment.arguments = args
            return fragment
        }
    }
}