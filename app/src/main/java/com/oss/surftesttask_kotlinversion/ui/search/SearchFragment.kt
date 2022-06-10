package com.oss.surftesttask_kotlinversion.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.oss.surftesttask_kotlinversion.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false)

//        initListeners()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(mBinding) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) etSearch.text.clear() //TODO testing
    }

    private fun initListeners() = with(mBinding) {
        // TODO  when user change text
        etSearch.doAfterTextChanged {
            setFragmentResult(REQUEST_KEY, bundleOf(KEY_SEARCH to etSearch.text))
        }
    }

    companion object {
        @JvmStatic
        private val KEY_SEARCH = "KEY_SEARCH"

        @JvmStatic
        private val REQUEST_KEY = "REQUEST_KEY"
    }
}