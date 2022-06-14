package com.oss.surftesttask_kotlinversion.ui.search_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.oss.surftesttask_kotlinversion.databinding.FragmentSearchBinding
import com.oss.surftesttask_kotlinversion.ui.Events
import com.oss.surftesttask_kotlinversion.ui.movies_list_screen.SharedViewModel
import com.oss.surftesttask_kotlinversion.utils.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchBinding
    private val mViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false)

        initListeners()
        return mBinding.root
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun initListeners() = with(mBinding) {

        etSearch.textChanges()
            .drop(1)
            .map { it.toString() }
            .debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                mViewModel.setQueryText(query)
                if (query.isNotEmpty()) mViewModel.setStateEvent(Events.SearchResultEvent(query))
                else mViewModel.setStateEvent(Events.GetResultEvent)
            }
            .launchIn(lifecycleScope)

    }
}