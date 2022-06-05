package com.oss.surftesttask_kotlinversion.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oss.surftesttask_kotlinversion.contract.navigator
import com.oss.surftesttask_kotlinversion.databinding.FragmentSearchBinding
import com.oss.surftesttask_kotlinversion.ui.states.EmptyMoviesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(mBinding) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) etSearch.text.clear() //TODO testing
    }

    fun initListeners() = with(mBinding) {
        // TODO  when user change text
        navigator().launch(EmptyMoviesListFragment::class.java, etSearch.text)
    }

    companion object {
        @JvmStatic
        fun newInstance(): SearchFragment = SearchFragment()
    }
}